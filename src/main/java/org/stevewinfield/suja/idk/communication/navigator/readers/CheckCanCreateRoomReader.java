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
package org.stevewinfield.suja.idk.communication.navigator.readers;

import org.stevewinfield.suja.idk.IDK;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.navigator.writers.RoomCanCreateResultWriter;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class CheckCanCreateRoomReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        session.writeMessage(
                new RoomCanCreateResultWriter(
                        session.getPlayerInstance().getRooms().size() >= IDK.NAV_MAX_ROOMS_PER_PLAYER &&
                                !session.getPlayerInstance().hasRight("unlimited_rooms"),
                        IDK.NAV_MAX_ROOMS_PER_PLAYER
                )
        );
    }

}
