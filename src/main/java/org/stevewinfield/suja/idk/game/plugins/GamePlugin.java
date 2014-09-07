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
package org.stevewinfield.suja.idk.game.plugins;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.game.bots.IBotInteractor;
import org.stevewinfield.suja.idk.game.miscellaneous.IChatCommand;

import javax.script.ScriptEngine;

public class GamePlugin {
    public ScriptEngine getScript() {
        return script;
    }

    public boolean isLoadedExternally() {
        return loadedExternally;
    }

    public GamePlugin(final String name, final ScriptEngine script, final boolean loadedExternally) {
        this.name = name;
        this.script = script;
        this.loadedExternally = loadedExternally;
    }

    public void addChatCommand(final String name, final String permission, final String obj) {
        Bootloader.getGame().getChatCommandHandler().addChatCommand(this, name, permission, obj);
    }

    public void addChatCommand(final String name, final IChatCommand chatCommand) {
        Bootloader.getGame().getChatCommandHandler().addChatCommand(this, name, chatCommand);
    }

    public void addBotInteractor(final int interactorId, final String obj) {
        Bootloader.getGame().getBotManager().addBotInteractor(this, interactorId, obj);
    }

    public void addBotInteractor(final int interactorId, final IBotInteractor botInteractor) {
        Bootloader.getGame().getBotManager().addBotInteractor(this, interactorId, botInteractor);
    }

    public String getName() {
        return name;
    }

    private final String name;
    private final ScriptEngine script;
    private final boolean loadedExternally;
}
