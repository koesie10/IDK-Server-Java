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
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.stevewinfield.suja.idk.IDK;
import org.stevewinfield.suja.idk.communication.MessageHandler;
import org.stevewinfield.suja.idk.communication.MessageReaderFactory;
import org.stevewinfield.suja.idk.encryption.Base64Encryption;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class NetworkDecoder extends FrameDecoder {
    private static final Logger logger = Logger.getLogger(NetworkDecoder.class);

    @Override
    protected Object decode(final ChannelHandlerContext ctx, final Channel channel, final ChannelBuffer buffer) {

        if (buffer.readableBytes() < 5) {
            return null;
        }

        int handledObjects = 0;

        while (buffer.readableBytes() > 0) {
            try {
                final byte testXmlLength = buffer.readByte();
                if (testXmlLength != 64) {
                    buffer.discardReadBytes();
                    ctx.getChannel().write(IDK.XML_POLICY);
                    return null;
                }

                final int messageLength = Base64Encryption.decode(new String(new byte[]{testXmlLength, buffer.readByte(), buffer.readByte()}));
                final short messageId = (short) Base64Encryption.decode(new String(new byte[]{buffer.readByte(), buffer.readByte()}));
                final byte[] content = new byte[messageLength - 2];

                buffer.readBytes(content);

                MessageHandler.handleMessage((Session) ctx.getChannel().getAttachment(), MessageReaderFactory.getMessageReader(messageId, content));

                ++handledObjects;
            } catch (final Exception e) {
                logger.error("Failed to handle packet", e);
            }
        }

        return handledObjects;
    }
}
