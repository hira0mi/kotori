package dev.kotori.quiz;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryQuestionRepositoryTest {

    private static final Question NEKO = new Question("猫", "кошка");
    private static final Question MIZU = new Question("水", "вода");

    @Test
    void findAllReturnsAllQuestionsInOrder() {
        var repository = new InMemoryQuestionRepository(List.of(NEKO, MIZU));

        assertEquals(List.of(NEKO, MIZU), repository.findAll());
    }

    @Test
    void findAllReturnsImmutableList() {
        var repository = new InMemoryQuestionRepository(List.of(NEKO));

        assertThrows(UnsupportedOperationException.class,
                () -> repository.findAll().add(MIZU));
    }

    @Test
    void constructorCopiesInputList() {
        var original = new ArrayList<>(List.of(NEKO));
        var repository = new InMemoryQuestionRepository(original);

        original.add(MIZU);

        assertEquals(List.of(NEKO), repository.findAll());
    }

    @Test
    void rejectsNullQuestionList() {
        assertThrows(NullPointerException.class, () -> new InMemoryQuestionRepository(null));
    }

    @Test
    void rejectsListWithNullElement() {
        var list = new ArrayList<Question>();
        list.add(NEKO);
        list.add(null);

        assertThrows(NullPointerException.class, () -> new InMemoryQuestionRepository(list));
    }

    @Test
    void emptyRepositoryReportsEmpty() {
        var repository = new InMemoryQuestionRepository(List.of());

        assertTrue(repository.isEmpty());
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void nonEmptyRepositoryReportsNotEmpty() {
        var repository = new InMemoryQuestionRepository(List.of(NEKO, MIZU));

        assertFalse(repository.isEmpty());
        assertEquals(2, repository.findAll().size());
    }
}