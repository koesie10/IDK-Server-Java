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

package org.stevewinfield.suja.idk.dedicated.logging;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.fusesource.jansi.AnsiConsole;

import java.io.PrintStream;

/**
 * Gotten from https://github.com/mihnita/java-color-loggers, licensed under Apache 2.0 license
 * @author mihnita
 */
public class JAnsiColorConsoleAppender extends BaseColorConsoleAppender {
    String gTarget = null;
    boolean usingStdErr;

    public JAnsiColorConsoleAppender() {
        super();
    }

    public JAnsiColorConsoleAppender(Layout layout) {
        super(layout);
    }

    public JAnsiColorConsoleAppender(Layout layout, String target) {
        super(layout, target);
    }

    public void setPassThrough(boolean value) {
        System.setProperty("jansi.passthrough", value ? "true" : "false");
    }

    public void setStrip(boolean value) {
        System.setProperty("jansi.strip", value ? "true" : "false");
    }

    @Override
    protected void subAppend(LoggingEvent event) {
        PrintStream currentOutput = usingStdErr ? AnsiConsole.err : AnsiConsole.out;

        hackPatternString();
        currentOutput.print(getColour(event.getLevel()));
        currentOutput.print(getLayout().format(event));
        if(layout.ignoresThrowable()) {
            String[] throwableStrRep = event.getThrowableStrRep();
            if (throwableStrRep != null) {
                for (String aThrowableStrRep : throwableStrRep) {
                    currentOutput.print(aThrowableStrRep);
                    currentOutput.print(Layout.LINE_SEP);
                }
            }
        }

        if (immediateFlush)
            currentOutput.flush();
    }

    @Override
    protected boolean hackPatternString() {
        String theTarget = getTarget();
        if (gTarget != theTarget) // I really want to have the same object, not just equal content
            usingStdErr = SYSTEM_ERR.equalsIgnoreCase(theTarget);

        return super.hackPatternString();
    }
}
