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
package org.stevewinfield.suja.idk.game.bots;

import org.stevewinfield.suja.idk.game.miscellaneous.ChatMessage;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public interface IBotInteractor {
    void onLoaded(RoomInstance room, RoomPlayer bot);

    void onLeft(RoomInstance room, RoomPlayer bot);

    void onCycle(RoomPlayer bot);

    void onPlayerSays(RoomPlayer player, RoomPlayer bot, ChatMessage message);
}
