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

import java.util.Collection;

public class CatalogGiftWrappingSettingsWriter extends MessageWriter {

    public CatalogGiftWrappingSettingsWriter(final boolean modernEnabled, final int modernPrice, final Collection<Integer> baseItems, final int boxCount, final int ribbonCount) {
        super(OperationCodes.getOutgoingOpCode("CatalogGiftWrappingSettings"));
        super.push(modernEnabled);
        super.push(modernPrice);
        super.push(baseItems.size());
        for (final Integer giftId : baseItems) {
            super.push(giftId);
        }
        super.push(boxCount);
        for (int i = 0; i < boxCount; i++) {
            super.push(i);
        }
        super.push(ribbonCount);
        for (int i = 0; i < ribbonCount; i++) {
            super.push(i);
        }
    }

}
