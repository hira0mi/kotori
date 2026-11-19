package dev.kotori.quiz;

import java.util.Objects;

/**
 * вопрос квиза: японское слово и ожидаемый русский перевод
 * валидируется при создании
 * нормализация и сравнение в слое квиза
 */
public record Question(String japanese, String russian) {

    public Question {
        Objects.requireNonNull(japanese, "japanese must not be null");
        Objects.requireNonNull(russian, "russian must not be null");

        japanese = japanese.trim();
        russian = russian.trim();

        if (japanese.isEmpty()) {
            throw new IllegalArgumentException("japanese must not be blank");
        }
        if (russian.isEmpty()) {
            throw new IllegalArgumentException("russian must not be blank");
        }
    }
}