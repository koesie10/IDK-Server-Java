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
package org.stevewinfield.suja.idk.communication.navigator.readers;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.collections.PopularTagsListHelper;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.navigator.writers.NavigatorPopularTagListWriter;
import org.stevewinfield.suja.idk.game.rooms.RoomInformation;
import org.stevewinfield.suja.idk.network.sessions.Session;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class GetPopularTagsReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        final List<RoomInformation> topRooms = Bootloader.getGame().getNavigatorListManager().getNavigatorLists().get(-1).getRooms();
        final ConcurrentHashMap<String, Integer> tags = new ConcurrentHashMap<>();

        for (final RoomInformation roomInfo : topRooms) {
            if (roomInfo == null) {
                continue;
            }

            for (String tag : roomInfo.getSearchableTags()) {
                tag = tag.toLowerCase();
                if (!tag.isEmpty() && tag.length() <= 30) {
                    if (tags.containsKey(tag)) {
                        tags.put(tag, tags.get(tag) + 1);
                    } else {
                        tags.put(tag, 1);
                    }
                }
            }
        }

        final TreeMap<String, Integer> sortedTags = new TreeMap<>(new PopularTagsListHelper(tags));
        sortedTags.putAll(tags);

        session.writeMessage(new NavigatorPopularTagListWriter(sortedTags));
    }

}
