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

package org.stevewinfield.suja.idk

import org.junit.Test

class InputFilterTest {
    @Test
    void replaceTabBySpace() {
        assert InputFilter.filterString("Hello,${(char) 9}how are you?") == "Hello, how are you?"
    }

    @Test
    void replaceSOHBySpace() {
        assert InputFilter.filterString("Hello,${(char) 1}how are you?") == "Hello, how are you?"
    }

    @Test
    void replaceSTXBySpace() {
        assert InputFilter.filterString("Hello,${(char) 2}how are you?") == "Hello, how are you?"
    }

    @Test
    void replaceETXBySpace() {
        assert InputFilter.filterString("Hello,${(char) 3}how are you?") == "Hello, how are you?"
    }

    @Test
    void noLineBreakReplace() {
        assert InputFilter.filterString("Hello\nHello\r") == "Hello\nHello\r"
    }

    @Test
    void lineBreakReplace() {
        assert InputFilter.filterString("Hello\nHello\r", true) == "Hello Hello "
    }
}
