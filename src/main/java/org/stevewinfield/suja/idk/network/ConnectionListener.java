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
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.stevewinfield.suja.idk.network.codec.NetworkDecoder;
import org.stevewinfield.suja.idk.network.codec.NetworkEncoder;
import org.stevewinfield.suja.idk.threadpools.WorkerTasks;

import java.net.InetSocketAddress;

public class ConnectionListener {
    private static final Logger logger = Logger.getLogger(ConnectionListener.class);

    private final NioServerSocketChannelFactory factory;
    private final ServerBootstrap bootstrap;

    private final String ip;
    private final int port;

    public ConnectionListener(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
        this.factory = new NioServerSocketChannelFactory(WorkerTasks.getNettyBossExecutor(), WorkerTasks.getNettyWorkerExecutor());
        this.bootstrap = new ServerBootstrap(this.factory);
    }

    public boolean tryListen() {
        try {
            this.bootstrap.getPipeline().addLast("encoder", new NetworkEncoder());
            this.bootstrap.getPipeline().addLast("decoder", new NetworkDecoder());
            this.bootstrap.getPipeline().addLast("handler", new ConnectionHandler());
            this.bootstrap.bind(new InetSocketAddress(this.ip, this.port));
        } catch (final ChannelException ex) {
            logger.error("Couldn't open connection to " + this.ip + ":" + this.port + ".", ex);
            return false;
        }
        logger.info("Connection to " + this.ip + ":" + this.port + " created.");
        return true;
    }
}
