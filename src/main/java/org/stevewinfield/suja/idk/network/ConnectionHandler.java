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
package org.stevewinfield.suja.idk.network;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.*;
import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.IDK;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class ConnectionHandler extends SimpleChannelHandler {
    private static final Logger logger = Logger.getLogger(ConnectionHandler.class);

    @Override
    public void channelOpen(final ChannelHandlerContext ctnx, final ChannelStateEvent e) {
        if (Bootloader.getSessionManager().makeSession(ctnx.getChannel())) {
            if (IDK.DEBUG) {
                logger.debug("Channel opened.");
            }
        } else {
            ctnx.getChannel().disconnect();
        }
    }

    @Override
    public void channelClosed(final ChannelHandlerContext ctx, final ChannelStateEvent e) {
        if (Bootloader.getSessionManager().removeSession(ctx.getChannel())) {
            ((Session) ctx.getChannel().getAttachment()).dispose();
            if (IDK.DEBUG) {
                logger.debug("Channel closed.");
            }
        }
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final ExceptionEvent e) {
        if (Bootloader.getSessionManager().hasSession(ctx.getChannel())) {
            ((Session) ctx.getChannel().getAttachment()).dispose();
        }
        logger.error("Execption caught.", e.getCause());
        ctx.getChannel().close();
    }

    @Override
    public void messageReceived(final ChannelHandlerContext ctx, final MessageEvent e) {
        if (Bootloader.getSessionManager().hasSession(ctx.getChannel())) {
            if (!(e.getMessage() instanceof Integer)) {
                ctx.getChannel().close();
            }
        }
    }

}
