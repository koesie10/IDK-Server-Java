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
package org.stevewinfield.suja.idk.game.miscellaneous;

public class ChatCommandArguments {
    private final String[] message;
    private int pointer;
    private final boolean shouted;

    public boolean isShouted() {
        return shouted;
    }

    public ChatCommandArguments(final String message, final boolean shouted) {
        this.message = message.split(" ");
        this.pointer = 0;
        this.shouted = shouted;
    }

    public String readWord() {
        final String msg = message[pointer];
        pointer++;
        return msg;
    }

    public int readInteger() {
        int val;
        try {
            val = Integer.valueOf(message[pointer]);
            pointer++;
        } catch (final NumberFormatException ex) {
            pointer++;
            return 0;
        }
        return val;
    }

    public boolean readBoolean() {
        return readInteger() > 0;
    }

    public String readMessage() {
        String result = "";
        for (; pointer < this.message.length; pointer++) {
            result += this.message[pointer];
        }
        return result;
    }
}
