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

package org.stevewinfield.suja.idk.game.plugins;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class PluginHelper {
    public static Session[] getActiveSessions() {
        return Bootloader.getSessionManager().getSessions().toArray(new Session[Bootloader.getSessionManager().getSessions().size()]);
    }

    public static RoomPlayer[] getRoomPlayers(final RoomInstance room) {
        return room.getRoomPlayers().values().toArray(new RoomPlayer[room.getRoomPlayers().values().size()]);
    }

    public static RoomItem[] getRoomItems(final RoomInstance room) {
        return room.getRoomItems().values().toArray(new RoomItem[room.getRoomItems().values().size()]);
    }
}
