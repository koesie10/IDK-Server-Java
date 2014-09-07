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
package org.stevewinfield.suja.idk.game.rooms.wired;

import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class WiredDelayEvent {
    public WiredAction getAction() {
        return action;
    }

    public RoomPlayer getPlayer() {
        return player;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getIncrementedCycle() {
        return ++counter;
    }

    private final WiredAction action;
    private final RoomPlayer player;
    private int counter;
    private boolean finished;

    public WiredDelayEvent(final WiredAction action, final RoomPlayer player) {
        this.counter = 0;
        this.action = action;
        this.player = player;
    }

    public void finish() {
        this.finished = true;
    }
}
