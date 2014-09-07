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

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.nio.charset.Charset;

public class QueuedMessageWriter {
    private int senderId;

    public QueuedMessageWriter() {
        this.initialize();
    }

    public int getSenderId() {
        return senderId;
    }

    public void initialize() {
        this.body = ChannelBuffers.dynamicBuffer();
        this.count = 0;
        this.senderId = 0;
    }

    public void push(final MessageWriter writer) {
        this.body.writeBytes(writer.getBytes());
        if (writer.getSenderId() != null) {
            this.senderId = writer.getSenderId();
        }
        ++count;
    }

    public int getSize() {
        return count;
    }

    public ChannelBuffer getBytes() {
        return this.body;
    }

    public String getDebugString() {
        final ChannelBuffer bodeh = body.duplicate();
        return bodeh.toString(Charset.defaultCharset());
    }

    private ChannelBuffer body;
    private int count;
}
