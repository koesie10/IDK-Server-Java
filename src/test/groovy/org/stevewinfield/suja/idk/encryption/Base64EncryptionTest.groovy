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

package org.stevewinfield.suja.idk.encryption

import org.junit.Test


class Base64EncryptionTest {
    @Test
    void decodeDoubleAtIs0() {
        assert 0 == Base64Encryption.decode("@@")
    }

    @Test
    void decodeAtAndAIs1() {
        assert 1 == Base64Encryption.decode("@A")
    }

    @Test
    void encode0IsDoubleAt() {
        assert "@@" == new String(Base64Encryption.encode(0))
    }

    @Test
    void encode1IsAtAndA() {
        assert "@A" == new String(Base64Encryption.encode(1))
    }
}
