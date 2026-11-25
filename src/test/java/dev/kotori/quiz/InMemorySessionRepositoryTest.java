package dev.kotori.quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemorySessionRepositoryTest {

    @Test
    void managesSessionLifecycleAndReset() {
        var repository = new InMemorySessionRepository();
        assertTrue(repository.isEmpty());

        // создание сессии
        var session1 = repository.getOrCreate(1L);
        assertFalse(repository.isEmpty());

        // получение той же сессии
        var session2 = repository.getOrCreate(1L);
        assertSame(session1, session2);

        // сброс состояния
        session1.setCurrentQuestion(new Question("猫", "кошка"));
        repository.reset(1L);
        assertNull(repository.getOrCreate(1L).getCurrentQuestion());

        // сброс несуществующей сессии не должен падать
        repository.reset(999L);
    }

    @Test
    void rejectsNullId() {
        var repository = new InMemorySessionRepository();
        assertThrows(NullPointerException.class, () -> repository.getOrCreate(null));
        assertThrows(NullPointerException.class, () -> repository.reset(null));
    }

    @Test
    void sessionsAreIsolatedBetweenUsers() {
        var repository = new InMemorySessionRepository();
        var questionA = new Question("猫", "кошка");
        var questionB = new Question("山", "гора");

        //A
        var sessionA = repository.getOrCreate(1L);
        sessionA.setCurrentQuestion(questionA);

        //B
        var sessionB = repository.getOrCreate(2L);
        sessionB.setCurrentQuestion(questionB);

        // состояния не перемешались
        assertEquals(questionA, repository.getOrCreate(1L).getCurrentQuestion());
        assertEquals(questionB, repository.getOrCreate(2L).getCurrentQuestion());

        // сброс B не влияет на A
        repository.reset(2L);
        assertNull(repository.getOrCreate(2L).getCurrentQuestion());
        assertEquals(questionA, repository.getOrCreate(1L).getCurrentQuestion());
    }
}