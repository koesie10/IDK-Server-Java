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
package org.stevewinfield.suja.idk.communication.catalog.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.catalog.CatalogClubGift;

import java.util.Collection;

public class MonthlyClubGiftsWriter extends MessageWriter {

    public MonthlyClubGiftsWriter(final int nextGiftSpan, final int availableGifts, final Collection<CatalogClubGift> gifts) {
        super(OperationCodes.getOutgoingOpCode("MonthlyClubGifts"));
        super.push(nextGiftSpan);
        super.push(availableGifts);
        super.push(gifts.size());

        for (final CatalogClubGift item : gifts) {
            super.push(item);
        }

        super.push(gifts.size());

        for (final CatalogClubGift item : gifts) {
            super.push(item.getId());
            super.push(item.onlyVIP());
            super.push(false);
            super.push(1);
        }
    }

}
