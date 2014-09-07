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
package org.stevewinfield.suja.idk.game.furnitures.interactors;

import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.IDK;
import org.stevewinfield.suja.idk.game.furnitures.FurnitureInteractor;
import org.stevewinfield.suja.idk.game.furnitures.IFurnitureInteractor;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.game.rooms.coordination.Vector2;

public class DefaultInteractor implements IFurnitureInteractor {

    @Override
    public void onLoaded(final RoomInstance room, final RoomItem item) {
    }

    @Override
    public void onPlace(final RoomPlayer player, final RoomItem item) {
        for (final Vector2 posAct : item.getAffectedTiles()) {
            final GapList<RoomItem> list = item.getRoom().getRoomItemsForTile(posAct);
            int size;
            if ((size = list.size()) > 1) {
                final RoomItem w = list.get(size - 2);
                if (w.getInteractorId() == FurnitureInteractor.ROLLER) {
                    w.requestCycles(IDK.CATA_ROLLERS_ROLL_DELAY);
                    break;
                }
            }
        }
    }

    @Override
    public void onRemove(final RoomPlayer player, final RoomItem item) {
    }

    @Override
    public void onTrigger(final RoomPlayer player, final RoomItem item, final int request, final boolean hasRights) {
    }

    @Override
    public void onCycle(final RoomItem item) {
    }

    @Override
    public void onPlayerWalksOn(final RoomPlayer player, final RoomItem item) {
    }

    @Override
    public void onPlayerWalksOff(final RoomPlayer player, final RoomItem item) {
    }

}
