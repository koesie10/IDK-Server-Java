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
package org.stevewinfield.suja.idk.communication.handshake.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;

public class SessionParamsWriter extends MessageWriter {

    public SessionParamsWriter() {
        super(OperationCodes.getOutgoingOpCode("SessionParams"));
        super.push(9);
        super.push(false);
        super.push(false);
        super.push(true);
        super.push(true);
        super.push(3);
        super.push(false);
        super.push(2);
        super.push(false);
        super.push(4);
        super.push(1);
        super.push(5);
        super.push("dd-MM-yyyy");
        super.push(7);
        super.push(0);
        super.push(8);
        super.push("http://hotel-us");
        super.push(9);
    }

}
