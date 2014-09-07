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

import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.QueuedMessageWriter;
import org.stevewinfield.suja.idk.communication.messenger.writers.MessengerFriendListWriter;
import org.stevewinfield.suja.idk.communication.messenger.writers.MessengerRequestListWriter;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class InitializeMessengerReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        final QueuedMessageWriter queue = new QueuedMessageWriter();
        queue.push(new MessengerFriendListWriter(session.getPlayerMessenger().getBuddies().values()));
        queue.push(new MessengerRequestListWriter(session.getPlayerInstance().getInformation().getId(), session.getPlayerMessenger().getRequests().values()));
        session.writeMessage(queue);
    }

}
