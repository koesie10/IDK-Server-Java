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
package org.stevewinfield.suja.idk.network.codec;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.QueuedMessageWriter;

import java.nio.charset.Charset;

public class NetworkEncoder extends SimpleChannelHandler {
    private static final Logger logger = Logger.getLogger(NetworkDecoder.class);

    @Override
    public void writeRequested(final ChannelHandlerContext ctx, final MessageEvent e) {
        try {
            if (ctx.getChannel().isConnected()) {
                if (e.getMessage() instanceof String) {
                    Channels.write(ctx, e.getFuture(), ChannelBuffers.copiedBuffer((String) e.getMessage(), Charset.forName("UTF-8")));
                } else if (e.getMessage() instanceof MessageWriter) {
                    final MessageWriter msg = (MessageWriter) e.getMessage();
                    Channels.write(ctx, e.getFuture(), msg.getBytes());
                } else if (e.getMessage() instanceof QueuedMessageWriter) {
                    final QueuedMessageWriter msg = (QueuedMessageWriter) e.getMessage();
                    Channels.write(ctx, e.getFuture(), msg.getBytes());
                }
            }
        } catch (final Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
