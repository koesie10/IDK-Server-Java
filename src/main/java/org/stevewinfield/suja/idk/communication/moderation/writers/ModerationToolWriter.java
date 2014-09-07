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

import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.moderation.ModerationPresetAction;
import org.stevewinfield.suja.idk.game.moderation.ModerationPresetMessage;
import org.stevewinfield.suja.idk.game.moderation.ModerationPresetMessageType;
import org.stevewinfield.suja.idk.game.players.PlayerInstance;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ModerationToolWriter extends MessageWriter {

    public ModerationToolWriter(final PlayerInstance player, final Collection<ModerationPresetMessage> presetMessages, final ConcurrentHashMap<Integer, ModerationPresetAction> actions) {
        super(OperationCodes.getOutgoingOpCode("ModerationTool"));
        super.push(-1);

        final List<ModerationPresetMessage> playerPresetMessages = new GapList<>();
        final List<ModerationPresetMessage> roomPresetMessages = new GapList<>();

        for (final ModerationPresetMessage presetMessage : presetMessages) {
            if (presetMessage.getType() == ModerationPresetMessageType.ROOM_MESSAGE) {
                roomPresetMessages.add(presetMessage);
            } else {
                playerPresetMessages.add(presetMessage);
            }
        }

        super.push(playerPresetMessages.size());

        for (final ModerationPresetMessage presetMessage : playerPresetMessages) {
            super.push(presetMessage.getMessage());
        }

        super.push(actions.size());

        for (final ModerationPresetAction action : actions.values()) {
            super.push(action.getCaption());
            super.push(action.getItems().size());

            for (final ModerationPresetAction item : action.getItems().values()) {
                super.push(item.getCaption());
                super.push(item.getMessage());
            }
        }

        super.push(player.hasRight("moderation_tickets"));
        super.push(player.hasRight("moderation_chatlogs"));
        super.push(player.hasRight("moderation_actions"));
        super.push(player.hasRight("moderation_kick"));
        super.push(player.hasRight("moderation_ban"));
        super.push(player.hasRight("moderation_caution"));
        super.push(true);

        super.push(roomPresetMessages.size());

        for (final ModerationPresetMessage presetMessage : roomPresetMessages) {
            super.push(presetMessage.getMessage());
        }
    }

}
