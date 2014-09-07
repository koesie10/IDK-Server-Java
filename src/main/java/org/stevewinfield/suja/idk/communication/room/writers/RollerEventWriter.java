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
import org.stevewinfield.suja.idk.game.rooms.coordination.Vector3;

public class RollerEventWriter extends MessageWriter {

    public RollerEventWriter(final Vector3 source, final Vector3 target, final int playerId, final int rollerId, final int itemId, final boolean itemMode) {
        super(OperationCodes.getOutgoingOpCode("RollerEvent"));
        super.push(source.getX());
        super.push(source.getY());
        super.push(target.getX());
        super.push(target.getY());
        super.push(itemMode); // TODO: roller or item
        if (itemMode) {
            super.push(itemId);
        } else {
            super.push(rollerId);
            super.push(2);
            super.push(playerId);
        }
        super.push(String.valueOf(source.getAltitude()));
        super.push(String.valueOf(target.getAltitude()));
        if (itemMode) {
            super.push(rollerId);
        }
    }

}
