package dev.kotori.quiz;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * inmemory репозиторий сессий
 * ConcurrentHashMap для сообщений от разных юзеров параллельно
 */
public final class InMemorySessionRepository implements SessionRepository {

    private final ConcurrentMap<Long, UserSession> sessions = new ConcurrentHashMap<>();

    @Override
    public UserSession getOrCreate(Long sessionId) {
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        return sessions.computeIfAbsent(sessionId, UserSession::new);
    }

    @Override
    public void reset(Long sessionId) {
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        UserSession session = sessions.get(sessionId);
        if (session != null) {
            session.reset();
        }
    }

    @Override
    public boolean isEmpty() {
        return sessions.isEmpty();
    }
}