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
package org.stevewinfield.suja.idk.encryption;

public class WireEncryption {
    public static byte[] encode(int i) {
        int pos = 0, numBytes = 1;
        final int negativeMask = i >= 0 ? 0 : 4;

        if (i < 0) {
            i *= -1;
        }

        final byte[] wf = new byte[]{(byte) (64 + (i & 3)), 0, 0, 0, 0, 0};

        for (i >>= 2; i > 0; i >>= 6, ++numBytes) {
            wf[++pos] = (byte) (64 + (i & 63));
        }

        wf[0] = (byte) (wf[0] | numBytes << 3 | negativeMask);

        final byte[] bzData = new byte[numBytes];
        System.arraycopy(wf, 0, bzData, 0, numBytes);

        return bzData;
    }

    public static int decode(final String data) {
        byte[] chars;
        chars = data.getBytes();
        return decode(chars)[1];
    }

    public static int[] decode(final byte[] raw) {
        try {
            int pos = 0, v;
            final boolean negative = (raw[pos] & 4) == 4;
            final int totalBytes = raw[pos] >> 3 & 7;
            v = raw[pos] & 3;
            pos++;
            int shiftAmount = 2;
            for (int b = 1; b < totalBytes; b++) {
                v |= (raw[pos] & 0x3f) << shiftAmount;
                shiftAmount = 2 + 6 * b;
                pos++;
            }

            if (negative) {
                v *= -1;
            }
            return new int[]{totalBytes, v};
        } catch (final Exception e) {
            return new int[]{0, 0};
        }
    }
}
