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
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

import java.util.List;
import java.util.Map.Entry;

public class RoomPlayerStatusListWriter extends MessageWriter {

    public RoomPlayerStatusListWriter(final List<RoomPlayer> players) {
        super(OperationCodes.getOutgoingOpCode("RoomPlayerStatusList"));
        super.push(players.size());

        for (final RoomPlayer player : players) {
            super.push(player.getVirtualId());
            super.push(player.getPosition().getX());
            super.push(player.getPosition().getY());
            super.push(String.valueOf(player.getPosition().getAltitude()));
            super.push(player.getHeadRotation());
            super.push(player.getRotation());

            final StringBuilder statusList = new StringBuilder("/");

            for (final Entry<String, String> status : player.getStatusMap().entrySet()) {
                statusList.append(status.getKey()).append(' ').append(status.getValue()).append('/');
            }

            super.push(statusList.toString() + "/");
        }
    }

}
