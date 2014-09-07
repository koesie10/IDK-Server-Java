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
package org.stevewinfield.suja.idk.communication.messenger.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.messenger.MessengerBuddy;

import java.util.List;

public class MessengerSearchResultWriter extends MessageWriter {

    public MessengerSearchResultWriter(final List<MessengerBuddy> friends, final List<MessengerBuddy> nonFriends) {
        super(OperationCodes.getOutgoingOpCode("MessengerSearchResult"));
        super.push(friends.size());

        for (final MessengerBuddy buddy : friends) {
            super.push(buddy.getPlayerId());
            super.push(buddy.getPlayerName());
            super.push(buddy.getMission());
            super.push(buddy.isOnline());
            super.push(buddy.isOnline());
            super.push("");
            super.push(true);
            super.push(buddy.isOnline() ? buddy.getAvatar() : "");
            super.push("");
        }

        super.push(nonFriends.size());

        for (final MessengerBuddy buddy : nonFriends) {
            super.push(buddy.getPlayerId());
            super.push(buddy.getPlayerName());
            super.push(buddy.getMission());
            super.push(buddy.isOnline());
            super.push(false);
            super.push("");
            super.push(false);
            super.push(buddy.isOnline() ? buddy.getAvatar() : "");
            super.push("");
        }
    }

}
