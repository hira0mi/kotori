package dev.kotori.quiz;

import java.util.List;

/**
 * только чтение вопросов
 * какой вопрос задавать — решает слой диалога
 */
public interface QuestionRepository {

    /**
     * все доступные вопросы; пустой список, если вопросов нет
     */
    List<Question> findAll();

    /**
     * true, если вопросов нет
     */
    boolean isEmpty();
}