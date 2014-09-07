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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadedCommandReader extends Thread {
    private static final Logger logger = Logger.getLogger(ThreadedCommandReader.class);
    private DedicatedServerCommandHandler dedicatedServerCommandHandler;

    public ThreadedCommandReader(String name, DedicatedServerCommandHandler dedicatedServerCommandHandler) {
        super(name);
        this.dedicatedServerCommandHandler = dedicatedServerCommandHandler;
    }

    public void run() {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));

        String line;

        try {
            while ((line = bufferedreader.readLine()) != null) {
                dedicatedServerCommandHandler.handle(line);
            }
        } catch (IOException e) {
            logger.error("Exception handling console input", e);
        }
    }
}