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

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.InputFilter;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class PurchaseGiftItemReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        final int pageId = reader.readInteger();
        final int itemId = reader.readInteger();
        final String extra = reader.readUTF();
        String targetName = InputFilter.filterString(reader.readUTF(), true);
        String targetMessage = InputFilter.filterString(reader.readUTF(), true);

        if (targetName.length() > 20) {
            targetName = targetName.substring(0, 20);
        }

        if (targetMessage.length() > 140) {
            targetMessage = targetMessage.substring(0, 140);
        }

        if (Bootloader.getGame().getCatalogManager().getCatalogPages().containsKey(pageId)) {
            Bootloader.getGame().getCatalogManager().purchaseItem(session, Bootloader.getGame().getCatalogManager().getCatalogPages().get(pageId), itemId, extra, true, targetName, targetMessage, reader.readInteger(), reader.readInteger(), reader.readInteger());
        }
    }

}
