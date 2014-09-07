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
package org.stevewinfield.suja.idk.communication.room.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.miscellaneous.ChatType;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RoomChatWriter extends MessageWriter {

    public RoomChatWriter(final int actorId, final String message, final int emotionId, final int type) {
        super(type == ChatType.SAY ? OperationCodes.getOutgoingOpCode("RoomChatTalk") : (type == ChatType.SHOUT ? OperationCodes.getOutgoingOpCode("RoomChatShout") : OperationCodes.getOutgoingOpCode("RoomChatWhisper")));

        // get message
        final StringBuilder builder = new StringBuilder();
        final Map<Integer, String> linkRefs = new HashMap<>();
        final String[] bits = message.split(" ");
        int j = 0, i = 0;

        for (final String bit : bits) {
            if (j > 0) {
                builder.append(' ');
            }
            if (bit.startsWith("http://") || bit.startsWith("https://") || bit.startsWith("www.")) {
                linkRefs.put(i, bit);
                builder.append("{").append(i++).append("}");
            } else {
                builder.append(bit);
            }
            j++;
        }

        super.push(actorId);
        super.push(builder.toString());
        super.push(emotionId);
        super.push(linkRefs.size());

        for (final Entry<Integer, String> entry : linkRefs.entrySet()) {
            String url = entry.getValue();

            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }

            super.push(url); // todo make secure
            super.push(entry.getValue());
            super.push(true); // is trusted?

        }

        super.push(0);
    }

}
