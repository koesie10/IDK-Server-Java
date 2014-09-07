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

import org.stevewinfield.suja.idk.InputFilter;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.messenger.writers.MessengerErrorWriter;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class SendInstantMessageReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        final int friendId = reader.readInteger();
        final String message = InputFilter.filterString(reader.readUTF());

        if (friendId < 1 || message.length() < 1) {
            return;
        }

        if (!session.getPlayerMessenger().getBuddies().containsKey(friendId)) {
            session.writeMessage(new MessengerErrorWriter(6, friendId));
            return;
        }

        if (!session.getPlayerMessenger().getOnlineBuddies().containsKey(friendId)) {
            session.writeMessage(new MessengerErrorWriter(5, friendId));
            return;
        }

        session.getPlayerMessenger().getOnlineBuddies().get(friendId).getSession().getPlayerMessenger().onMessageReceived(session.getPlayerInstance().getInformation().getId(), message);
    }

}
