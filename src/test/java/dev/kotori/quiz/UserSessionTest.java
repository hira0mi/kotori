package dev.kotori.quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserSessionTest {

    @Test
    void managesSessionStateAndLifecycle() {
        // создание и начальное состояние
        var session = new UserSession(123L);
        assertEquals(123L, session.getSessionId());
        assertNull(session.getCurrentQuestion());

        // обновление вопроса
        var question = new Question("猫", "кошка");
        session.setCurrentQuestion(question);
        assertEquals(question, session.getCurrentQuestion());

        // сброс
        session.reset();
        assertNull(session.getCurrentQuestion());
    }

    @Test
    void rejectsNullId() {
        assertThrows(NullPointerException.class, () -> new UserSession(null));
    }
}