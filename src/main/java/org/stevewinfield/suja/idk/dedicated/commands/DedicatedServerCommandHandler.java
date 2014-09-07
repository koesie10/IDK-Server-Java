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

package org.stevewinfield.suja.idk.dedicated.commands;

import org.apache.log4j.Logger;
import org.stevewinfield.suja.idk.dedicated.commands.defaults.*;

import java.util.concurrent.ConcurrentHashMap;

public class DedicatedServerCommandHandler {
    private static final Logger logger = Logger.getLogger(DedicatedServerCommandHandler.class);
    private ConcurrentHashMap<String, IDedicatedServerCommand> commands;

    public DedicatedServerCommandHandler() {
        this.commands = new ConcurrentHashMap<>();
    }

    public void handle(String line) {
        String[] parts = line.split(" ");
        if (parts.length < 1 || parts[0].length() < 1) {
            logger.error("Invalid command");
            return;
        }
        parts[0] = parts[0].toLowerCase();
        if (!commands.containsKey(parts[0])) {
            logger.error("Command not found");
            return;
        }
        IDedicatedServerCommand cmd = commands.get(parts[0]);
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, parts.length - 1);
        cmd.execute(args, logger);
    }

    public void registerCommand(IDedicatedServerCommand cmd) {
        commands.put(cmd.getName().toLowerCase(), cmd);
    }

    public void registerDefaultCommands() {
        registerCommand(new StopCommand());
        registerCommand(new RefreshCatalogCommand());
        registerCommand(new RefreshFurnitureCommand());
        registerCommand(new KickAllCommand());
        registerCommand(new RefreshLocalPluginsCommand());
    }

    public IDedicatedServerCommand getCommand(String name) {
        return commands.get(name);
    }
}
