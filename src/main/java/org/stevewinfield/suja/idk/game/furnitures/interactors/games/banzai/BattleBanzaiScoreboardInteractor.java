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

import org.stevewinfield.suja.idk.game.furnitures.interactors.DefaultInteractor;
import org.stevewinfield.suja.idk.game.rooms.GameTeam;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.game.rooms.tasks.GameTask;

public class BattleBanzaiScoreboardInteractor extends DefaultInteractor {
    protected final int teamId;
    protected GameTask game;

    public BattleBanzaiScoreboardInteractor(final int teamId) {
        this.teamId = teamId;
    }

    @Override
    public void onLoaded(final RoomInstance room, final RoomItem item) {
        super.onLoaded(room, item);

        this.game = room.getRoomTask().getBanzaiTask();
        if (this.game.getTeam(teamId) == null) {
            this.game.addGameTeam(teamId, new GameTeam(teamId, null, item));
            return;
        }
        this.game.getTeam(teamId).setScoreBoard(item);
        item.setFlags(this.game.getTeam(teamId).getPoints() + "");
        item.update(false, true);
    }

    @Override
    public void onRemove(final RoomPlayer player, final RoomItem item) {
        super.onRemove(player, item);

        this.game.getTeam(teamId).setScoreBoard(null);
    }

}
