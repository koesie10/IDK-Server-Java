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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OperationCodes {

    public static short getIncomingOpCode(final String key) {
        return Short.valueOf(incomingOpCodes.getProperty(key, "0"));
    }

    public static short getOutgoingOpCode(final String key) {
        return Short.valueOf(outgoingOpCodes.getProperty(key, "0"));
    }

    public static void loadCodes() throws IOException {
        incomingOpCodes = new Properties();
        outgoingOpCodes = new Properties();

        incomingOpCodes.load(new FileInputStream("opcodes-incoming.properties"));
        outgoingOpCodes.load(new FileInputStream("opcodes-outgoing.properties"));
    }

    private static Properties incomingOpCodes;
    private static Properties outgoingOpCodes;
}
