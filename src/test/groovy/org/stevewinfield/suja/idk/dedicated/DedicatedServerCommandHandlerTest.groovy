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

package org.stevewinfield.suja.idk.dedicated

import org.junit.Before
import org.junit.Test
import org.stevewinfield.suja.idk.dedicated.commands.DedicatedServerCommandHandler
import org.stevewinfield.suja.idk.dedicated.commands.IDedicatedServerCommand

class DedicatedServerCommandHandlerTest {
    DedicatedServerCommandHandler handler;

    @Before
    void setUp() {
        handler = new DedicatedServerCommandHandler()
    }

    @Test
    void testRegisterCommand() {
        handler.registerCommand([
                'getName': { 'test' },
                'execute': { args, logger -> }
        ] as IDedicatedServerCommand)
        assert handler.getCommand('test') != null
    }

    @Test
    void testHandleCommandWithoutArguments() {
        def executed = false
        handler.registerCommand([
                'getName': { 'test' },
                'execute': { args, logger ->
                    executed = true
                }
        ] as IDedicatedServerCommand)
        handler.handle('test')
        assert executed
    }

    @Test
    void testHandleCommandWithArguments() {
        def executed = false
        handler.registerCommand([
                'getName': { 'test' },
                'execute': { args, logger ->
                    executed = true
                    assert args.length == 2
                    assert args[0] == 'me'
                    assert args[1] == 'not'
                }
        ] as IDedicatedServerCommand)
        handler.handle('test me not')
        assert executed
    }

    @Test
    void testHandleCommandNotFound() {
        def executed = false
        handler.registerCommand([
                'getName': { 'test' },
                'execute': { string, logger ->
                    executed = true
                }
        ] as IDedicatedServerCommand)
        handler.handle('nonexistingcommand')
        assert !executed
    }
}

