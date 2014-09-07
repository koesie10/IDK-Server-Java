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
package org.stevewinfield.suja.idk.communication.room.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;

public class RoomGroupBadgesWriter extends MessageWriter {

    public RoomGroupBadgesWriter() {
        super(OperationCodes.getOutgoingOpCode("RoomGroupBadges"));
        // todo
        super.push(1); // count
        super.push(1); // loop -> id
        super.push("s58116s04078s04072s52074889902cf4440630470f222ad5c6489d7"); // badge
    }

}
