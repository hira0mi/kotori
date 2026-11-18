package dev.kotori.quiz;

import java.util.List;
import java.util.Objects;

/**
 * in-memory репозиторий на неизменяемой копии списка
 */
public final class InMemoryQuestionRepository implements QuestionRepository {

    private final List<Question> questions;

    public InMemoryQuestionRepository(List<Question> questions) {
        Objects.requireNonNull(questions, "questions must not be null");
        this.questions = List.copyOf(questions);
    }

    @Override
    public List<Question> findAll() {
        return questions;
    }

    @Override
    public boolean isEmpty() {
        return questions.isEmpty();
    }
}