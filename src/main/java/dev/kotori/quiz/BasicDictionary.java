package dev.kotori.quiz;

import java.util.List;

/**
 * базовый словарь для первой практики
 */
public final class BasicDictionary {

    private BasicDictionary() {
    }

    /**
     * вопросы по умолчанию
     */
    public static List<Question> questions() {
        return List.of(
                new Question("猫", "кошка"),
                new Question("水", "вода"),
                new Question("犬", "собака"),
                new Question("本", "книга"),
                new Question("家", "дом"),
                new Question("友達", "друг"),
                new Question("学校", "школа"),
                new Question("先生", "учитель"),
                new Question("日本", "Япония"),
                new Question("花", "цветок")
        );
    }
}