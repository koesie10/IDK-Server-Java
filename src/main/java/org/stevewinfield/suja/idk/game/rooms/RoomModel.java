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
package org.stevewinfield.suja.idk.game.rooms;

import org.apache.log4j.Logger;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.room.writers.RoomItemHeightmapWriter;
import org.stevewinfield.suja.idk.game.rooms.coordination.Heightmap;
import org.stevewinfield.suja.idk.game.rooms.coordination.Vector3;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomModel {
    private static final Logger logger = Logger.getLogger(RoomModel.class);

    public String getName() {
        return name;
    }

    public int getModelType() {
        return modelType;
    }

    public Heightmap getHeightMap() {
        return heightMap;
    }

    public Vector3 getDoorPosition() {
        return doorPosition;
    }

    public int getDoorRotation() {
        return doorRotation;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public MessageWriter getHeightmapWriter() {
        return cachedHeightmapWriter;
    }

    public RoomModel() {
        this.name = "";
        this.modelType = 0;
        this.heightMap = null;
        this.doorPosition = new Vector3(0, 0, 0);
        this.doorRotation = 0;
        this.maxPlayers = 0;
    }

    public void set(final ResultSet row) throws SQLException {
        this.name = row.getString("name");
        this.modelType = row.getInt("model_type");
        this.doorPosition = new Vector3(row.getInt("door_x"), row.getInt("door_y"), row.getDouble("door_altitude"));
        this.heightMap = new Heightmap(row.getString("heightmap"), doorPosition);
        this.doorRotation = row.getInt("door_rotation");
        this.maxPlayers = row.getInt("max_players");
        this.cachedHeightmapWriter = new RoomItemHeightmapWriter(this.heightMap.toString());
    }

    // fields
    private String name;
    private int modelType;
    private Heightmap heightMap;
    private Vector3 doorPosition;
    private MessageWriter cachedHeightmapWriter;
    private int doorRotation;
    private int maxPlayers;
}
