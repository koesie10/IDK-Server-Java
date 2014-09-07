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

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class AchievementListWriter extends MessageWriter {

    public AchievementListWriter(final ConcurrentHashMap<Integer, PlayerAchievement> playerAchievements, final Collection<Achievement> achievements) {
        super(OperationCodes.getOutgoingOpCode("AchievementList"));
        super.push(achievements.size());

        for (final Achievement achievement : achievements) {
            PlayerAchievement playerAchievement = null;

            if (playerAchievements.containsKey(achievement.getId())) {
                playerAchievement = playerAchievements.get(achievement.getId());
            }

            int targetLevel = playerAchievement != null ? playerAchievement.getLevel() + 1 : 1;

            if (targetLevel > achievement.getLevelCount()) {
                targetLevel = achievement.getLevelCount();
            }

            final AchievementLevel levelData = achievement.getLevels().get(targetLevel);

            super.push(achievement.getId());
            super.push(targetLevel); // target level
            super.push(achievement.getGroupName() + targetLevel); // badge +
            super.push(levelData == null ? 0 : levelData.getRequirement());
            super.push(levelData == null ? 0 : levelData.getPixelsReward());
            super.push(levelData == null ? 0 : levelData.getPointsReward());
            super.push(playerAchievement != null ? playerAchievement.getProgress() : 0); // progress
            super.push(playerAchievement != null && playerAchievement.getLevel() >= achievement.getLevelCount()); // 100%?
            super.push(achievement.getCategory());
            super.push(achievement.getLevelCount());
        }

    }

}
