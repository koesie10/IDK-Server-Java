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
import org.stevewinfield.suja.idk.game.catalog.CatalogItem;

public class CatalogPurchaseResultWriter extends MessageWriter {

    public CatalogPurchaseResultWriter(final int id, final String displayName, final int priceCredits, final int pricePixels, final int priceExtra) {
        super(OperationCodes.getOutgoingOpCode("CatalogPurchaseResult"));
        super.push(id);
        super.push(displayName);
        super.push(priceCredits);
        super.push(pricePixels);
        super.push(priceExtra);
        super.push(0);
        super.push(0);

    }

    public CatalogPurchaseResultWriter(final CatalogItem item, final boolean isGift) {
        super(OperationCodes.getOutgoingOpCode("CatalogPurchaseResult"));
        super.push(item.getBaseItem().getId());
        super.push(item.getDisplayName());
        super.push(item.getCostsCoins());
        super.push(item.getCostsPixels());
        super.push(item.getCostsExtra());
        super.push(!isGift);
        super.push(item.getBaseItem().getType());
        super.push(item.getBaseItem().getSpriteId());
        super.push("");
        super.push(1);
        super.push(0);
        super.push(0);
    }

}
