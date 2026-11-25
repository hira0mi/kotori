package dev.kotori.quiz;

import java.util.Objects;

/**
 * сессия пользователя, хранит независимое состояние
 * привязана к id чата
 */
public class UserSession {

    private final Long sessionId;
    private Question currentQuestion;

    public UserSession(Long sessionId) {
        this.sessionId = Objects.requireNonNull(sessionId, "sessionId must not be null");
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    /**
     * сбрасывает состояние
     */
    public void reset() {
        this.currentQuestion = null;
    }
}