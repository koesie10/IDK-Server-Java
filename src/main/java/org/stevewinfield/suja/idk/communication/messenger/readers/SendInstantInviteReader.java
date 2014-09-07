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

import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.InputFilter;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.network.sessions.Session;

import java.util.List;

public class SendInstantInviteReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        final int amount = reader.readInteger();
        final List<Integer> playerIds = new GapList<>();

        for (int i = 0; i < amount; i++) {
            playerIds.add(reader.readInteger());
        }

        String message = InputFilter.filterString(reader.readUTF(), false);

        if (message.length() > 121) {
            message = message.substring(0, 121);
        }

        for (final int playerId : playerIds) {
            if (session.getPlayerMessenger().getOnlineBuddies().containsKey(playerId)) {
                session.getPlayerMessenger().getOnlineBuddies().get(playerId).getSession().getPlayerMessenger().onInviteReceived(session.getPlayerInstance().getInformation().getId(), message);
            }
        }
    }

}
