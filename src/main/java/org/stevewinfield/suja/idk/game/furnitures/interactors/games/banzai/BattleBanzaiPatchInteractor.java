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
package org.stevewinfield.suja.idk.game.furnitures.interactors.games.banzai;

import org.stevewinfield.suja.idk.game.furnitures.IFurnitureInteractor;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class BattleBanzaiPatchInteractor implements IFurnitureInteractor {

    @Override
    public void onLoaded(final RoomInstance room, final RoomItem item) {
        room.getRoomTask().getBanzaiTask().addGameItem(item);
    }

    @Override
    public void onPlace(final RoomPlayer player, final RoomItem item) {
    }

    @Override
    public void onRemove(final RoomPlayer player, final RoomItem item) {
        item.getRoom().getRoomTask().getBanzaiTask().removeGameItem(item);
    }

    @Override
    public void onTrigger(final RoomPlayer player, final RoomItem item, final int request, final boolean hasRights) {
    }

    @Override
    public void onCycle(final RoomItem item) {
        if (item.getRoom().getRoomTask().getBanzaiTask().isRunning()) {
            item.setCounter(0);
            return;
        }
        item.setFlags(item.getFlagsState() == 0 ? item.getRoom().getRoomTask().getBanzaiTask().getFlexInteger() + "" : "0");
        item.update(false, true);
        if (item.getIncementedCounter() < 5) {
            item.requestCycles(1);
        }
    }

    @Override
    public void onPlayerWalksOn(final RoomPlayer player, final RoomItem item) {
        if (!item.getRoom().getRoomTask().getBanzaiTask().isRunning()) {
            return;
        }

        item.getRoom().getRoomTask().getBanzaiTask().onHandle(player, item);
    }

    @Override
    public void onPlayerWalksOff(final RoomPlayer player, final RoomItem item) {
    }

}
