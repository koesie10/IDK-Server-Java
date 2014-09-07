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
package org.stevewinfield.suja.idk.game.furnitures.interactors;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.player.writers.ActivityPointsWriter;
import org.stevewinfield.suja.idk.communication.player.writers.CreditsBalanceWriter;
import org.stevewinfield.suja.idk.game.furnitures.FurnitureExchange;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class ExchangeInteractor extends DefaultInteractor {

    @Override
    public void onTrigger(final RoomPlayer player, final RoomItem item, final int request, final boolean hasRights) {
        super.onTrigger(player, item, request, hasRights);

        if (player == null ||
                (player.getRoom().hasRights(player.getSession(), true) && item.getBase().hasRightCheck()) ||
                !Bootloader.getGame().getFurnitureManager().getFurnitureExchanges().containsKey(item.getBase().getId())) {
            return;
        }

        final FurnitureExchange exchange = Bootloader.getGame().getFurnitureManager().getFurnitureExchanges().get(item.getBase().getId());

        if (exchange.getChangeCoins() > 0) {
            player.getSession().getPlayerInstance().getInformation().setCredits(exchange.getChangeCoins());
            player.getSession().writeMessage(new CreditsBalanceWriter(player.getSession().getPlayerInstance().getInformation().getCreditsBalance()));
        }

        if (exchange.getChangePixels() > 0 || exchange.getChangeExtra() > 0) {
            if (exchange.getChangePixels() > 0) {
                player.getSession().getPlayerInstance().getInformation().setPixels(exchange.getChangePixels());
            } else {
                player.getSession().getPlayerInstance().getInformation().setShells(exchange.getChangeExtra());
            }
            player.getSession().writeMessage(
                    new ActivityPointsWriter(
                            player.getSession().getPlayerInstance().getInformation().getPixelsBalance(),
                            player.getSession().getPlayerInstance().getInformation().getShellsBalance()
                    )
            );
        }

        player.getSession().getPlayerInstance().getInformation().updateCurrencies();

        /**
         * Remove the shit
         */
        item.getRoom().removeItem(item, player.getSession());
        Bootloader.getStorage().executeQuery("DELETE FROM items WHERE id=" + item.getItemId());
    }

}
