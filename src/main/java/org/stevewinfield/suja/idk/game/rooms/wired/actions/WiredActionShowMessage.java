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
package org.stevewinfield.suja.idk.game.rooms.wired.actions;

import org.stevewinfield.suja.idk.InputFilter;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.game.rooms.wired.WiredAction;

public class WiredActionShowMessage extends WiredAction {

    public WiredActionShowMessage(final RoomInstance room, final RoomItem item, final String[] data) {
        this.item = item;
        this.set(data);
    }

    @Override
    public int getWiredType() {
        return 2;
    }

    @Override
    public RoomItem getItem() {
        return item;
    }

    @Override
    public void onHandle(final RoomPlayer player) {
        if (message.length() > 0) {
            player.whisper(player.getSession(), player.getVirtualId(), message);
        }
    }

    @Override
    public String[] getObject(final MessageReader reader) {
        reader.readBoolean(); // skip
        final String message = InputFilter.filterString(reader.readUTF(), true);
        return new String[]{message.length() > 100 ? message.substring(0, 100) : message};
    }

    @Override
    public void set(final String[] obj) {
        this.message = obj.length > 0 ? obj[0] : "";
    }

    // fields
    private final RoomItem item;
    private String message;
}
