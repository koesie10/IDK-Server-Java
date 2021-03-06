/*
 * IDK Game Server by Steve Winfield
 * https://github.com/WinfieldSteve
 */
package org.stevewinfield.suja.idk.communication.room.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;

public class RoomItemPlacementErrorWriter extends MessageWriter {

    public RoomItemPlacementErrorWriter(final int errorCode) {
        super(OperationCodes.getOutgoingOpCode("RoomItemPlacementError"));
        super.push(errorCode);
    }

}
