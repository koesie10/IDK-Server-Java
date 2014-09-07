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
package org.stevewinfield.suja.idk.communication.player.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.players.PlayerInformation;

public class PlayerObjectWriter extends MessageWriter {

    public PlayerObjectWriter(final PlayerInformation playerInfo) {
        super(OperationCodes.getOutgoingOpCode("PlayerObject"));
        super.push(playerInfo.getId() + "");
        super.push(playerInfo.getPlayerName());
        super.push(playerInfo.getAvatar());
        super.push(playerInfo.getGender() == PlayerInformation.FEMALE_GENDER ? "F" : "M");
        super.push(playerInfo.getMission());
        super.push(playerInfo.getPlayerName());
        super.push(false);
        super.push(playerInfo.getRespectPoints());
        super.push(playerInfo.getAvailableRespects());
        super.push(playerInfo.getAvailableScratches());
        super.push(playerInfo.isStreamEnabled());
    }

}
