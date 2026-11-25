package dev.kotori;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public final class InMemorySessionRepository implements SessionRepository {
    private final ConcurrentMap<Long, UserSession> sessions = new ConcurrentHashMap<>();

    @Override
    public UserSession getOrCreate(long sessionId, Supplier<UserSession> createSession) {
        return sessions.computeIfAbsent(sessionId, ignored -> createSession.get());
    }
}