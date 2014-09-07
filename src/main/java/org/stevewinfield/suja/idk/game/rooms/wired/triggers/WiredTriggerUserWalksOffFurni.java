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
package org.stevewinfield.suja.idk.game.rooms.wired.triggers;

import org.apache.log4j.Logger;
import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.game.rooms.wired.WiredManager;
import org.stevewinfield.suja.idk.game.rooms.wired.WiredTrigger;

import java.util.List;

public class WiredTriggerUserWalksOffFurni extends WiredTrigger {
    private static final Logger logger = Logger.getLogger(WiredTriggerUserWalksOffFurni.class);

    public WiredTriggerUserWalksOffFurni(final RoomInstance room, final RoomItem item, final String[] data) {
        this.room = room;
        this.item = item;
        this.set(data);
    }

    @Override
    public List<Integer> getItems() {
        return this.items;
    }

    @Override
    public int getWiredType() {
        return 1;
    }

    @Override
    public RoomItem getItem() {
        return item;
    }

    @Override
    public void set(final String[] obj) {
        this.items = new GapList<>();
        try {
            if (obj.length > 0 && obj[0].length() > 0) {
                for (final String furni : obj[0].split(",")) {
                    items.add(Integer.valueOf(furni));
                }
            }
        } catch (final NumberFormatException e) {
            logger.error("NumberFormatException", e);
        }
    }

    @Override
    public String[] getObject(final MessageReader reader) {
        reader.readInteger();
        reader.readInteger();
        reader.readUTF();
        String furniString = "";
        final int furniAmount = reader.readInteger();
        for (int i = 0; i < furniAmount; i++) {
            final int furniId = reader.readInteger();
            if (!item.getRoom().getRoomItems().containsKey(furniId) || WiredManager.isWiredItem(item.getRoom().getRoomItems().get(furniId).getBase())) {
                continue;
            }
            furniString += "," + furniId;
        }
        return new String[]{furniString.length() > 0 ? furniString.substring(1) : furniString};
    }

    @Override
    public boolean onTrigger(final RoomPlayer player, final Object data) {
        return data != null && data instanceof RoomItem && items.contains(((RoomItem) data).getItemId());
    }

    // fields
    private final RoomInstance room;
    private GapList<Integer> items;
    private final RoomItem item;

}
