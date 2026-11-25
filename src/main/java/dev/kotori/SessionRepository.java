package dev.kotori;

import java.util.function.Supplier;

public interface SessionRepository {
    UserSession getOrCreate(long sessionId, Supplier<UserSession> createSession);
}