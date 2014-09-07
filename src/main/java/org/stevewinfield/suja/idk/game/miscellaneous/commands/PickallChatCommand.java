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
package org.stevewinfield.suja.idk.game.miscellaneous.commands;

import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.communication.inventory.writers.UpdatePlayerInventoryWriter;
import org.stevewinfield.suja.idk.game.furnitures.FurnitureInteractor;
import org.stevewinfield.suja.idk.game.miscellaneous.ChatCommandArguments;
import org.stevewinfield.suja.idk.game.miscellaneous.IChatCommand;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

import java.util.List;

public class PickallChatCommand implements IChatCommand {

    @Override
    public String getPermissionCode() {
        return "command_pickall";
    }

    @Override
    public boolean execute(final RoomPlayer player, final ChatCommandArguments arguments) {
        final RoomInstance room = player.getRoom();

        if (room == null || !room.hasRights(player.getSession(), true)) {
            return false;
        }

        final List<RoomItem> items = new GapList<>();

        for (final RoomItem item : room.getRoomItems().values()) {
            if (item.getInteractorId() == FurnitureInteractor.POST_IT) {
                continue;
            }
            room.removeItem(item, player.getSession());
            items.add(item);
        }

        for (final RoomItem item : items) {
            player.getSession().getPlayerInstance().getInventory().addItem(item, null, room.itemHasToUpdate(item.getItemId()));
        }

        player.getSession().writeMessage(new UpdatePlayerInventoryWriter());
        items.clear();
        return true;
    }

}
