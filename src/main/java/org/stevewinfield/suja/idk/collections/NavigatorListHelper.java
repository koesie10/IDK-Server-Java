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
package org.stevewinfield.suja.idk.collections;

import org.stevewinfield.suja.idk.game.rooms.RoomInformation;

import java.util.Comparator;

public class NavigatorListHelper implements Comparator<RoomInformation> {
    String searchQuery;

    public NavigatorListHelper() {
    }

    public NavigatorListHelper(final String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public int compare(final RoomInformation o1, final RoomInformation o2) {
        if (searchQuery != null && o1.getTotalPlayers() == o2.getTotalPlayers()) {
            int points1 = 0;
            int points2 = 0;
            for (int i = 0; i < this.searchQuery.length(); i++) {
                if (o1.getName().length() >= (i + 1) && o1.getName().charAt(i) == this.searchQuery.charAt(i)) {
                    points1++;
                }
                if (o2.getName().length() >= (i + 1) && o2.getName().charAt(i) == this.searchQuery.charAt(i)) {
                    points2++;
                }
            }
            return ((Integer) points1).compareTo(points2) * (-1);
        }
        return ((Integer) o1.getTotalPlayers()).compareTo(o2.getTotalPlayers()) * (-1);
    }

}