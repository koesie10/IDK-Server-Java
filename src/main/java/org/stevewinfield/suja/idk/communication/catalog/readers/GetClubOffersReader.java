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
package org.stevewinfield.suja.idk.communication.catalog.readers;

import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.catalog.writers.CatalogClubOffersWriter;
import org.stevewinfield.suja.idk.game.catalog.CatalogClubOffer;
import org.stevewinfield.suja.idk.game.catalog.CatalogClubOfferType;
import org.stevewinfield.suja.idk.game.levels.ClubSubscriptionLevel;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class GetClubOffersReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        final GapList<CatalogClubOffer> correctedOffers = new GapList<>();

        for (final CatalogClubOffer offer : Bootloader.getGame().getCatalogManager().getCatalogClubOffers().values()) {
            if (session.getPlayerInstance().getSubscriptionManager().getBaseLevel() > ClubSubscriptionLevel.BASIC && offer.getType() == CatalogClubOfferType.BASIC) {
                continue;
            }
            correctedOffers.add(offer);
        }

        session.writeMessage(new CatalogClubOffersWriter(correctedOffers, Bootloader.getTimestamp()));
    }

}
