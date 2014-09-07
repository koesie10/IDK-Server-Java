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
package org.stevewinfield.suja.idk.communication.friendstream.readers;

import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.friendstream.writers.FriendStreamEventWriter;
import org.stevewinfield.suja.idk.game.friendstream.FriendStreamEventData;
import org.stevewinfield.suja.idk.network.sessions.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendStreamEventRequestReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.getPlayerInstance().getInformation().isStreamEnabled()) {
            return;
        }

        if (!reader.readBoolean() && !session.getFriendStream().needsUpdate()) {
            return;
        }

        final List<FriendStreamEventData> events = new ArrayList<>(session.getFriendStream().getEvents());
        Collections.reverse(events);

        session.writeMessage(new FriendStreamEventWriter(events));
    }

}
