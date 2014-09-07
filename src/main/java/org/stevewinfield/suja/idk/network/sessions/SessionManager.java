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
package org.stevewinfield.suja.idk.network.sessions;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.Channel;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SessionManager {
    private static final Logger logger = Logger.getLogger(SessionManager.class);

    public SessionManager(final int maxConnections) {
        this.maxConnections = maxConnections;
        this.activeSessions = 0;
        this.sessions = new ConcurrentHashMap<>();
        this.authenticatedSessions = new ConcurrentHashMap<>();
        logger.info("Session Manager ready (Max. " + maxConnections + " sessions).");
    }

    public boolean hasSession(final Channel channel) {
        return this.sessions.containsKey(channel.getId());
    }

    public Collection<Session> getSessions() {
        return this.sessions.values();
    }

    public boolean hasAuthenticatedSession(final int playerId) {
        return this.authenticatedSessions.containsKey(playerId);
    }

    public Session getAuthenticatedSession(final int playerId) {
        return this.authenticatedSessions.containsKey(playerId) ? this.authenticatedSessions.get(playerId) : null;
    }

    public boolean removeSession(final Channel channel) {
        if (this.sessions.containsKey(channel.getId())) {
            this.sessions.remove(channel.getId());
            if (((Session) channel.getAttachment()).isAuthenticated()) {
                activeSessions--;
                this.authenticatedSessions.remove(((Session) channel.getAttachment()).getPlayerInstance().getInformation().getId());
            }
            return true;
        }
        return false;
    }

    public void makeAuthenticatedSession(final int playerId, final Session session) {
        if (!this.authenticatedSessions.containsKey(playerId)) {
            activeSessions++;
            this.authenticatedSessions.put(playerId, session);
        }
    }

    public boolean makeSession(final Channel channel) {
        if (this.activeSessions >= this.maxConnections) {
            logger.warn("Reached Max-Connections!");
            return false;
        }

        final Session session = new Session(channel.getId(), channel);
        channel.setAttachment(session);

        return this.sessions.putIfAbsent(channel.getId(), session) == null;
    }

    public int getActiveSessionCount() {
        return activeSessions;
    }

    private final int maxConnections;
    private int activeSessions;
    private final ConcurrentMap<Integer, Session> sessions;
    private final ConcurrentMap<Integer, Session> authenticatedSessions;
}
