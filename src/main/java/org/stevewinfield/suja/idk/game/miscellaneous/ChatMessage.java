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
package org.stevewinfield.suja.idk.game.miscellaneous;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class ChatMessage {

    public RoomPlayer getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public int getChatType() {
        return type;
    }

    public ChatMessage(final RoomPlayer sender, final String message, final int chatType) {
        this.sender = sender;
        this.message = message;
        this.type = chatType;
    }

    public void dispose() {
        try {
            this.finalize();
        } catch (final Throwable e) {
            Bootloader.getLogger().error("Failed to dipose ChatMessage", e);
        }
    }

    // fields
    private final RoomPlayer sender;
    private final String message;
    private final int type;
}
