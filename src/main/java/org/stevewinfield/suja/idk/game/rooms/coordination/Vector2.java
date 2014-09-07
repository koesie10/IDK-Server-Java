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

public class Vector2 {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

    @Override
    public boolean equals(final Object x) {
        if (x instanceof Vector2) {
            final Vector2 l = (Vector2) x;
            return l.getX() == this.x && l.getY() == this.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (31 + this.x) * 31 + this.y;
    }

    public Vector3 getVector3() {
        return new Vector3(x, y, 0);
    }

    public static Vector2 createFromString(final String position) {
        final String[] bits = position.split(";");

        final int x = Integer.valueOf(bits[0]);
        int y = 0;

        if (bits.length > 1) {
            y = Integer.valueOf(bits[1]);
        }

        return new Vector2(x, y);
    }

    // fields
    private final int x;
    private final int y;
}
