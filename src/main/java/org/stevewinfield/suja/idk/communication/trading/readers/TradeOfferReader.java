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
import org.stevewinfield.suja.idk.communication.trading.writers.TradeOffersWriter;
import org.stevewinfield.suja.idk.game.inventory.PlayerItem;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.game.trading.Trade;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class TradeOfferReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.isInRoom() || !session.getPlayerInstance().getInformation().canTrade()) {
            return;
        }

        final RoomInstance room = Bootloader.getGame().getRoomManager().getLoadedRoomInstance(session.getRoomId());

        Trade trade;

        if (room == null || (trade = room.getTradeManager().getTrade(session.getPlayerInstance().getInformation().getId())) == null) {
            return;
        }

        final PlayerItem item = session.getPlayerInstance().getInventory().getItem(reader.readInteger());

        if (item == null || !item.getBase().isTradeable() || !trade.offerItem(session.getPlayerInstance().getInformation().getId(), item)) {
            return;
        }

        final MessageWriter writer = new TradeOffersWriter(trade);
        session.writeMessage(writer);

        Session targetSession = null;
        final int targetId = session.getPlayerInstance().getInformation().getId() == trade.getPlayerOne() ? trade.getPlayerTwo() : trade.getPlayerOne();

        for (final RoomPlayer player : room.getRoomPlayers().values()) {
            if (player.getSession() != null && player.getSession().getPlayerInstance().getInformation().getId() == targetId) {
                targetSession = player.getSession();
            }
        }

        if (targetSession != null) {
            targetSession.writeMessage(writer);
        }
    }

}
