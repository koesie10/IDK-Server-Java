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

package org.stevewinfield.suja.idk.dedicated.commands.defaults;

import org.apache.log4j.Logger;
import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.dedicated.commands.IDedicatedServerCommand;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class StopCommand implements IDedicatedServerCommand {

    @Override
    public void execute(String[] args, Logger logger) {
        logger.info("Stopping server...");
        int i = 0;
        for (final Session session : Bootloader.getSessionManager().getSessions()) {
            session.disconnect();
            i++;
        }
        logger.info(i + " User(s) was/were kicked.");
        i = 0;
        for (final RoomInstance room : Bootloader.getGame().getRoomManager().getLoadedRoomInstances()) {
            room.save();
            i++;
        }
        logger.info(i + " Room(s) was/were saved.");
        Bootloader.exitServer(0);
    }

    @Override
    public String getName() {
        return "stop";
    }
}
