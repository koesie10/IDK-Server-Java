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
package org.stevewinfield.suja.idk;

public class InputFilter {

    public static String filterString(final String x, final boolean removeLinebreaks) {
        String f = x.replace((char) 1, ' ').replace((char) 2, ' ').replace((char) 3, ' ').replace((char) 9, ' ');
        if (removeLinebreaks) {
            f = f.replace((char) 13, ' ').replace((char) 10, ' ');
        }
        return f;
    }

    public static String filterString(final String x) {
        return filterString(x, false);
    }

}