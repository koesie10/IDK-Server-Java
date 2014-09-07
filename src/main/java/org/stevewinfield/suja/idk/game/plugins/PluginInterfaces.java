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

package org.stevewinfield.suja.idk.game.plugins;

import org.stevewinfield.suja.idk.game.miscellaneous.ChatCommandArguments;
import org.stevewinfield.suja.idk.game.miscellaneous.ChatMessage;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class PluginInterfaces {
    public static interface OnLoadedInterface {
        public void onLoaded(final RoomInstance room, final RoomPlayer bot);
    }

    public static interface OnLeftInterface {
        public void onLeft(final RoomInstance room, final RoomPlayer bot);
    }

    public static interface OnCycleInterface {
        public void onCycle(final RoomPlayer bot);
    }

    public static interface OnPlayerSaysInterface {
        public void onPlayerSays(final RoomPlayer player, final RoomPlayer bot, final ChatMessage message);
    }

    public static interface ChatCommandExecutor {
        public boolean execute(RoomPlayer player, ChatCommandArguments arguments);
    }

    public static interface ScriptPlugin {
        public void initializePlugin();
    }
}
