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
package org.stevewinfield.suja.idk.communication.moderation.writers;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.players.PlayerInformation;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class ModerationPlayerInfoWriter extends MessageWriter {

    public ModerationPlayerInfoWriter(final PlayerInformation playerInfo, final Session session) {
        super(OperationCodes.getOutgoingOpCode("ModerationPlayerInfo"));
        super.push(playerInfo.getId());
        super.push(playerInfo.getPlayerName());
        super.push(((int) (Bootloader.getTimestamp() - playerInfo.getRegisteredTimestamp())) / 60); // time
        // registered
        super.push(((int) (Bootloader.getTimestamp() - playerInfo.getLastLoginTimestamp())) / 60); // time
        // last
        // login
        super.push(session != null);
        super.push(playerInfo.getTotalCFHS()); // created tickets
        super.push(playerInfo.getAbusiveCFHS()); // tickets abusive
        super.push(playerInfo.getCautions()); // cautions
        super.push(playerInfo.getBans()); // bans
    }

}
