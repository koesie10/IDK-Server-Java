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
package org.stevewinfield.suja.idk.game.navigator;

import org.stevewinfield.suja.idk.collections.NavigatorListHelper;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.navigator.writers.NavigatorListRoomsWriter;
import org.stevewinfield.suja.idk.game.rooms.RoomInformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NavigatorList {

    public int getId() {
        return id;
    }

    public List<RoomInformation> getRooms() {
        return rooms;
    }

    public RoomInformation getInformationEntry(final int id) {
        for (final RoomInformation room : this.rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }

    public MessageWriter getListWriter() {
        if (writerUpdateNeeded) {
            cachedWriter = new NavigatorListRoomsWriter(this.id, 1, "", this.rooms);
        }
        return cachedWriter;
    }

    public NavigatorList(final int id) {
        this.id = id;
        this.rooms = new ArrayList<>();
        this.size = 0;
        this.lowestPlayers = 0;
        this.updates = 0;
        this.writerUpdateNeeded = true;
    }

    public void removeRoom(final RoomInformation room) {
        this.rooms.remove(room);
    }

    public void setRoom(final RoomInformation room, final int playersTotal, final boolean update) {
        if (!this.rooms.contains(room) && room.getId() != -1) {
            if (this.size == 50 && lowestPlayers < playersTotal) {
                this.lowestPlayers = playersTotal;
                this.lowestRoom = room;
                this.rooms.remove(this.lowestRoom);
                writerUpdateNeeded = true;
                this.rooms.add(room);
            } else if (this.size < 50) {
                if (playersTotal < lowestPlayers || lowestPlayers == 0) {
                    lowestPlayers = playersTotal;
                    lowestRoom = room;
                }
                writerUpdateNeeded = true;
                rooms.add(room);
            }

        }
        if (update || updates == 4 || updates == 0) {
            Collections.sort(rooms, new NavigatorListHelper());
            writerUpdateNeeded = true;
            updates = 0;
        }
        updates++;
    }

    // fields
    private final int id;
    private final List<RoomInformation> rooms;
    private final int size;
    private int lowestPlayers;
    private int updates;
    private boolean writerUpdateNeeded;
    private RoomInformation lowestRoom;
    private MessageWriter cachedWriter;
}
