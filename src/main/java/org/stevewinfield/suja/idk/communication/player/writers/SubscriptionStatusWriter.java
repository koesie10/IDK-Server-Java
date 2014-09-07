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
package org.stevewinfield.suja.idk.communication.player.writers;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.levels.ClubSubscription;
import org.stevewinfield.suja.idk.game.levels.ClubSubscriptionLevel;

public class SubscriptionStatusWriter extends MessageWriter {

    public SubscriptionStatusWriter(final ClubSubscription subscriptionManager, final boolean boughtFromCata) {
        super(OperationCodes.getOutgoingOpCode("SubscriptionStatus"));

        final long timestamp = Bootloader.getTimestamp();
        final long diff[] = new long[]{0, 0, 0, 0, 0};

        if (subscriptionManager.getExpireTime() >= timestamp) {
            long diffInSeconds = (subscriptionManager.getExpireTime() - timestamp);
            diff[4] = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
            diff[3] = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
            diff[2] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
            diff[1] = (diffInSeconds = (diffInSeconds / 24));
            diff[0] = (diffInSeconds = (diffInSeconds / 30)) >= 12 ? diffInSeconds % 12 : diffInSeconds;
            diff[0] = (long) Math.floor(diff[0] / 31.0);
        }

        super.push("habbo_club");
        super.push((int) (diff[1] - (31 * diff[0])));
        super.push(subscriptionManager.isActive());
        super.push((int) diff[0]);
        super.push(boughtFromCata ? 2 : 1);
        super.push(1);
        super.push(subscriptionManager.isActive() && subscriptionManager.getBaseLevel() == ClubSubscriptionLevel.VIP);
        super.push((int) (subscriptionManager.getPastClubTime() / 86400));
        super.push((int) (subscriptionManager.getPastVipTime() / 86400));
        super.push(0);
        super.push(30);
        super.push(25);

    }

}
