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

import java.util.Collection;

public class AchievementDataListWriter extends MessageWriter {

    public AchievementDataListWriter(final Collection<Achievement> achievements) {
        super(OperationCodes.getOutgoingOpCode("AchievementDataList"));
        super.push(achievements.size());

        for (final Achievement achievement : achievements) {
            super.push(achievement.getGroupName().startsWith("ACH_") ? achievement.getGroupName().substring(4) : achievement.getGroupName());

            final Collection<AchievementLevel> levels = achievement.getLevels().values();
            super.push(levels.size());

            for (final AchievementLevel level : levels) {
                super.push(level.getLevel());
                super.push(level.getRequirement());
            }
        }
    }

}
