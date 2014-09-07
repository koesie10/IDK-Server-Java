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

import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class VendingInteractor extends DefaultInteractor {

    @Override
    public void onTrigger(final RoomPlayer player, final RoomItem item, final int request, final boolean hasRights) {
        super.onTrigger(player, item, request, hasRights);

        if (player != null && !item.isTouching(player.getPosition(), player.getRotation())) {
            player.moveTo(item.getFrontPosition(), item.getFrontRotation(), item);
            return;
        }

        item.setFlags(1);
        item.update(false, player == null ? null : player.getSession());
        if (player != null) {
            player.handleVending(2, item.getBase().getRandomVendingId());
            item.getRoom().getWiredHandler().onPlayerChangedState(player, item);
        }
        item.requestCycles(2);
    }

    @Override
    public void onCycle(final RoomItem item) {
        super.onCycle(item);
        item.setFlags(0);
        item.update(false);
    }

}
