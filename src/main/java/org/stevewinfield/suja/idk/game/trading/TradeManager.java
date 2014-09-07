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
package org.stevewinfield.suja.idk.game.trading;

import java.util.concurrent.ConcurrentHashMap;

public class TradeManager {
    public Trade getTrade(final int playerId) {
        return this.tradeSessions.containsKey(playerId) ? this.tradeSessions.get(playerId) : null;
    }

    public TradeManager() {
        this.tradeSessions = new ConcurrentHashMap<>();
    }

    public boolean initiateTrade(final int a, final int b) {
        if (this.tradeSessions.containsKey(a) || this.tradeSessions.containsKey(b)) {
            return false;
        }

        final Trade trade = new Trade(a, b);

        this.tradeSessions.put(a, trade);
        this.tradeSessions.put(b, trade);
        return true;
    }

    public void stopTrade(final int playerId) {
        this.tradeSessions.remove(playerId);
    }

    private final ConcurrentHashMap<Integer, Trade> tradeSessions;
}
