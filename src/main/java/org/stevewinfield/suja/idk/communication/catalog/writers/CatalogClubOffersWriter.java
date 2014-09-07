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

import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.catalog.CatalogClubOffer;
import org.stevewinfield.suja.idk.game.catalog.CatalogClubOfferType;

import java.util.Calendar;
import java.util.Date;

public class CatalogClubOffersWriter extends MessageWriter {

    public CatalogClubOffersWriter(final GapList<CatalogClubOffer> offers, final long baseTimestamp) {
        super(OperationCodes.getOutgoingOpCode("CatalogClubOffers"));
        super.push(offers.size());

        for (final CatalogClubOffer offer : offers) {
            final Date date = Bootloader.getDateFromTimestamp(baseTimestamp + offer.getLengthSeconds());
            final Calendar expire = Calendar.getInstance();
            expire.setTime(date);

            super.push(offer.getId());
            super.push(offer.getName());
            super.push(offer.getPrice());
            super.push(false); // upgrade? nop
            super.push(offer.getType() == CatalogClubOfferType.VIP);
            super.push(offer.getLengthMonths());
            super.push(offer.getLengthDays());
            super.push(expire.get(Calendar.YEAR));
            super.push(expire.get(Calendar.MONTH));
            super.push(expire.get(Calendar.DAY_OF_MONTH));
        }
    }

}
