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

import org.stevewinfield.suja.idk.IDK;
import org.stevewinfield.suja.idk.Translations;
import org.stevewinfield.suja.idk.game.miscellaneous.ChatCommandArguments;
import org.stevewinfield.suja.idk.game.miscellaneous.IChatCommand;
import org.stevewinfield.suja.idk.game.miscellaneous.NotifyType;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class InfoChatCommand implements IChatCommand {

    @Override
    public String getPermissionCode() {
        return "command_info";
    }

    @Override
    public boolean execute(final RoomPlayer player, final ChatCommandArguments arguments) {
        player.getSession().sendNotification(NotifyType.STAFF_ALERT, Translations.getTranslation("info_chat_command", IDK.VERSION, IDK.BUILD_NUMBER, "Steve Winfield", "Rhinnodanny."));
        return true;
    }

}
