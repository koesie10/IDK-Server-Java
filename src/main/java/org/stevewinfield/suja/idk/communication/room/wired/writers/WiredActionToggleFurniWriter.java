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
package org.stevewinfield.suja.idk.communication.room.wired.writers;

import org.apache.log4j.Logger;
import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;

public class WiredActionToggleFurniWriter extends RoomWiredEffectWriter {
    private static final Logger logger = Logger.getLogger(WiredActionToggleFurniWriter.class);

    public WiredActionToggleFurniWriter(final RoomItem item, final String furnis, final int delay) {
        super.push(false);
        super.push(5);

        final GapList<Integer> items = new GapList<>();

        if (furnis.length() > 0) {
            try {
                for (final String furni : furnis.split(",")) {
                    items.add(Integer.valueOf(furni));
                }
            } catch (final NumberFormatException e) {
                logger.error("NumberFormatException", e);
            }
        }

        super.push(items.size());

        for (final int furni : items) {
            super.push(furni);
        }

        super.push(item.getBase().getSpriteId());
        super.push(item.getItemId());
        super.push("");
        super.push(0);
        super.push(0);
        super.push(0);
        super.push(delay);
        super.push(0);
        super.push("");
    }

}
