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

package org.stevewinfield.suja.idk.communication.inventory.readers;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.inventory.writers.AvatarEffectActivatedWriter;
import org.stevewinfield.suja.idk.game.furnitures.FurnitureType;
import org.stevewinfield.suja.idk.game.inventory.PlayerItem;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class ApplyPlayerEffectReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.isInRoom()) {
            return;
        }

        final RoomInstance room = Bootloader.getGame().getRoomManager().getLoadedRoomInstance(session.getRoomId());

        if (room == null) {
            return;
        }

        int effectId = reader.readInteger();

        if (effectId > -1) {
            final PlayerItem item = session.getPlayerInstance().getInventory().getAvatarEffect(effectId);
            if (item == null || !item.getBase().getType().equals(FurnitureType.AVATAR_EFFECT) || Integer.valueOf(item.getFlags().split("" + (char) 10)[1]) < 1) {
                return;
            }
        } else {
            effectId = session.getRoomPlayer().getEffectCache() > 0 ? session.getRoomPlayer().getEffectCache() : 0;
        }

        session.getRoomPlayer().applyEffect(effectId);
    }

}