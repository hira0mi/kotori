package dev.kotori.quiz;

/**
 * репозиторий для управления сессиями юзеров
 */
public interface SessionRepository {

    /**
     * получает сессию или создает новую если ее нет
     */
    UserSession getOrCreate(Long sessionId);
    void reset(Long sessionId);
    /**
     * true если сессий нет
     */
    boolean isEmpty();
}