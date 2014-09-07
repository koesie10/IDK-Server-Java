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
package org.stevewinfield.suja.idk.game.rooms.coordination;

public class Rotation {
    public static int calculate(final int X1, final int Y1, final int X2, final int Y2) {
        int rotation = 0;

        if (X1 > X2 && Y1 > Y2) {
            rotation = 7;
        } else if (X1 < X2 && Y1 < Y2) {
            rotation = 3;
        } else if (X1 > X2 && Y1 < Y2) {
            rotation = 5;
        } else if (X1 < X2 && Y1 > Y2) {
            rotation = 1;
        } else if (X1 > X2) {
            rotation = 6;
        } else if (X1 < X2) {
            rotation = 2;
        } else if (Y1 < Y2) {
            rotation = 4;
        } else if (Y1 > Y2) {
            rotation = 0;
        }

        return rotation;
    }
}
