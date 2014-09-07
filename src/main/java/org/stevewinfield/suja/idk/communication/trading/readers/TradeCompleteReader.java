/*
 * Copyright 2014 The IDK Project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.stevewinfield.suja.idk.communication.trading.readers;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.trading.writers.TradeFinalizedWriter;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.game.trading.Trade;
import org.stevewinfield.suja.idk.game.trading.TradeStage;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class TradeCompleteReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.isInRoom()) {
            return;
        }

        final RoomInstance room = Bootloader.getGame().getRoomManager().getLoadedRoomInstance(session.getRoomId());

        Trade trade;

        if (room == null
                || (trade = room.getTradeManager().getTrade(session.getPlayerInstance().getInformation().getId())) == null
                || !trade.acceptTrade(session.getPlayerInstance().getInformation().getId())) {
            return;
        }

        if (trade.getStage() == TradeStage.FINALIZED) { // deliver items
            room.getTradeManager().stopTrade(session.getPlayerInstance().getInformation().getId());
            session.getRoomPlayer().removeStatus("trd");
            session.getRoomPlayer().update();

            final MessageWriter finalized = new TradeFinalizedWriter();
            session.writeMessage(finalized);

            Session targetSession = null;
            final int targetId = session.getPlayerInstance().getInformation().getId() == trade.getPlayerOne() ? trade.getPlayerTwo() : trade.getPlayerOne();

            for (final RoomPlayer player : room.getRoomPlayers().values()) {
                if (player.getSession() != null && player.getSession().getPlayerInstance().getInformation().getId() == targetId) {
                    targetSession = player.getSession();
                }
            }

            if (targetSession != null) {
                room.getTradeManager().stopTrade(targetSession.getPlayerInstance().getInformation().getId());
                targetSession.getRoomPlayer().removeStatus("trd");
                targetSession.getRoomPlayer().update();
                targetSession.writeMessage(finalized);
                trade.deliverItems(targetId == trade.getPlayerOne() ? targetSession : session, targetId == trade.getPlayerTwo() ? targetSession : session);
            }
        }
    }

}
