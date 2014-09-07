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
import org.stevewinfield.suja.idk.game.catalog.CatalogPage;

public class CatalogPageWriter extends MessageWriter {

    public CatalogPageWriter(final CatalogPage page) {
        super(OperationCodes.getOutgoingOpCode("CatalogPage"));
        super.push(page.getId());
        super.push(page.getLayout());
        super.push(page.getLayoutStrings().length);

        for (final String layoutString : page.getLayoutStrings()) {
            super.push(layoutString);
        }

        super.push(page.getContentStrings().length);

        for (final String contentString : page.getContentStrings()) {
            super.push(contentString);
        }

        super.push(page.getItems().size()); // items

        for (final CatalogItem item : page.getItems().values()) {
            super.push(item);
        }

        super.push(-1);
    }

}
