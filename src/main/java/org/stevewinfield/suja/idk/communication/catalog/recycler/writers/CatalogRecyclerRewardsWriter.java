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
package org.stevewinfield.suja.idk.communication.catalog.recycler.writers;

import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.furnitures.Furniture;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class CatalogRecyclerRewardsWriter extends MessageWriter {

    public CatalogRecyclerRewardsWriter(final LinkedHashMap<Integer, GapList<Integer>> rewards) {
        super(OperationCodes.getOutgoingOpCode("CatalogRecyclerRewards"));
        super.push(rewards.size());
        for (final Entry<Integer, GapList<Integer>> rewardEntry : rewards.entrySet()) {
            int chanceToDisplay = 0;
            switch (rewardEntry.getKey()) {
                case 5:
                    chanceToDisplay = 40;
                    break;
                case 4:
                    chanceToDisplay = 25;
                    break;
                case 3:
                    chanceToDisplay = 15;
                    break;
                case 2:
                    chanceToDisplay = 5;
                    break;
            }
            super.push(rewardEntry.getKey());
            super.push(chanceToDisplay);
            super.push(rewardEntry.getValue().size());
            for (final int itemId : rewardEntry.getValue()) {
                final Furniture furni = Bootloader.getGame().getFurnitureManager().getFurniture(itemId);
                if (furni == null) {
                    continue;
                }
                super.push(furni.getType());
                super.push(furni.getSpriteId());
            }
        }
    }

}
