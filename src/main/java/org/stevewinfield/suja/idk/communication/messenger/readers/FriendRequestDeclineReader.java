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
package org.stevewinfield.suja.idk.communication.messenger.readers;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class FriendRequestDeclineReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        reader.readBoolean();
        int amount = reader.readInteger();

        if (amount > 50) {
            amount = 50;
        }

        final String updateString = "DELETE FROM player_friends WHERE player_acc_id=" + session.getPlayerInstance().getInformation().getId();
        String whereString = "";

        for (int i = 0; i < amount; i++) {
            final int playerId = reader.readInteger();
            if (session.getPlayerMessenger().getRequests().containsKey(playerId)) {
                whereString += " OR player_req_id=" + playerId;
                session.getPlayerMessenger().getRequests().remove(playerId);
            }
        }

        if (!whereString.equals("")) {
            Bootloader.getStorage().executeQuery(updateString + " AND (" + whereString.substring(4) + ")");
        }
    }

}
