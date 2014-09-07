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
package org.stevewinfield.suja.idk.communication;

import java.util.LinkedList;
import java.util.Queue;

public class MessageReaderFactory {
    private static final Queue<MessageReader> freeObjects = new LinkedList<>();

    public static MessageReader getMessageReader(final short messageId, final byte[] body) {
        if (freeObjects.size() > 0) {
            MessageReader reader;

            synchronized (freeObjects) {
                reader = freeObjects.poll();
            }

            if (reader == null) {
                return new MessageReader(messageId, body);
            }

            reader.initialize(messageId, body);
            return reader;
        }
        return new MessageReader(messageId, body);
    }

    public static void objectCallback(final MessageReader reader) {
        synchronized (freeObjects) {
            freeObjects.offer(reader);
        }
    }
}
