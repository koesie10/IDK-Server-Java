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

import java.util.Properties;

public class Settings {
    // getters
    public String getProperty(final String key) {
        return this.properties.getProperty(key);
    }

    public String getProperty(final String key, final String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    public Settings(final Properties properties) {
        this.properties = properties;
    }

    // fields
    private final Properties properties;
}
