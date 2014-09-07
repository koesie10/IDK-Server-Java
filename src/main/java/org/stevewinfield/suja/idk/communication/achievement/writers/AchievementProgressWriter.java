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
package org.stevewinfield.suja.idk.communication.achievement.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.achievements.Achievement;
import org.stevewinfield.suja.idk.game.achievements.AchievementLevel;
import org.stevewinfield.suja.idk.game.players.PlayerAchievement;

public class AchievementProgressWriter extends MessageWriter {

    public AchievementProgressWriter(final Achievement achievement, final int targetLevel, final AchievementLevel targetLevelData, final int totalLevels, final PlayerAchievement playerData) {
        super(OperationCodes.getOutgoingOpCode("AchievementProgress"));
        super.push(achievement.getId());
        super.push(targetLevel);
        super.push(achievement.getGroupName() + targetLevel);
        super.push(targetLevelData == null ? 0 : targetLevelData.getRequirement());
        super.push(targetLevelData == null ? 0 : targetLevelData.getPixelsReward());
        super.push(targetLevelData == null ? 0 : targetLevelData.getPointsReward());
        super.push(playerData != null ? playerData.getProgress() : 0);
        super.push(playerData != null && playerData.getLevel() >= totalLevels);
        super.push(achievement.getCategory());
        super.push(totalLevels);

    }

}
