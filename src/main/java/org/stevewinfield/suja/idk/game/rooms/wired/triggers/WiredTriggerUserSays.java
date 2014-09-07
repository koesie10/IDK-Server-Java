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

import org.stevewinfield.suja.idk.InputFilter;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.game.rooms.wired.WiredTrigger;

public class WiredTriggerUserSays extends WiredTrigger {

    public WiredTriggerUserSays(final RoomInstance room, final RoomItem item, final String[] data) {
        this.room = room;
        this.item = item;
        this.set(data);
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
    public boolean onTrigger(final RoomPlayer player, final Object data) {
        return ((String) data).toLowerCase().contains(this.message) && this.message.length() > 0 && (!this.onlyRoomOwner || (room.hasRights(player.getSession(), true)));
    }

    @Override
    public String[] getObject(final MessageReader reader) {
        reader.readBoolean();
        final boolean onlyOwner_ = reader.readBoolean();
        String message_ = InputFilter.filterString(reader.readUTF(), true);
        message_ = message_.length() > 100 ? message_.substring(0, 100) : message_;
        return new String[]{message_, onlyOwner_ ? "1" : "0"};
    }

    @Override
    public void set(final String[] obj) {
        this.message = obj.length > 0 ? obj[0] : "";
        this.onlyRoomOwner = obj.length > 1 && obj[1].equals("1");
    }

    // fields
    private final RoomInstance room;
    private final RoomItem item;
    private String message;
    private boolean onlyRoomOwner;
}
