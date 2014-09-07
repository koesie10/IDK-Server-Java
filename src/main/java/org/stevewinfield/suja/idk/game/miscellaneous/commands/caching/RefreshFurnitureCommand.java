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
package org.stevewinfield.suja.idk.game.miscellaneous.commands.caching;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.game.miscellaneous.ChatCommandArguments;
import org.stevewinfield.suja.idk.game.miscellaneous.IChatCommand;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class RefreshFurnitureCommand implements IChatCommand {

    @Override
    public String getPermissionCode() {
        return "command_refresh_furniture";
    }

    @Override
    public boolean execute(final RoomPlayer player, final ChatCommandArguments arguments) {
        final RoomInstance room = player.getRoom();

        if (room == null) {
            return false;
        }

        Bootloader.getGame().getFurnitureManager().loadCache();
        return true;
    }

}
