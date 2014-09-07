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
package org.stevewinfield.suja.idk.game.furnitures;

import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public interface IFurnitureInteractor {
    void onLoaded(RoomInstance room, RoomItem item);

    void onPlace(RoomPlayer player, RoomItem item);

    void onRemove(RoomPlayer player, RoomItem item);

    void onTrigger(RoomPlayer player, RoomItem item, int request, boolean hasRights);

    void onCycle(RoomItem item);

    void onPlayerWalksOn(RoomPlayer player, RoomItem item);

    void onPlayerWalksOff(RoomPlayer player, RoomItem item);
}
