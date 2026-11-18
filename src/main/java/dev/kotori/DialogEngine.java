
package dev.kotori;

import java.util.Objects;

public class DialogEngine {

    private final QuizService quiz;
    private boolean started;

    public DialogEngine(QuizService quiz) {
        this.quiz = Objects.requireNonNull(quiz);
    }

    public String handle(String message) {

        if (message == null || message.isBlank()) {
            return "Отправь перевод или /help.";
        }

        String input = message.strip();

        // Справка не должна менять состояние квиза
        if (input.equalsIgnoreCase("/help")
                || input.equalsIgnoreCase("\\help")) {

            return """
                    Я Kotori — бот для изучения японского!

                    Я показываю японское слово, а ты отправляешь
                    его перевод на русском языке.

                    После каждого ответа я сообщаю, правильный
                    ли он, и показываю следующее слово.

                    Команды:
                    /start — начать тренировку заново
                    /help — посмотреть инструкцию
                    """;
        }

        // Начинаем новую тренировку
        if (input.equalsIgnoreCase("/start")) {
            quiz.reset();
            started = quiz.hasQuestions();

            return """
                    Привет! Я Kotori — бот для изучения японского!

                    Переводи японские слова на русский.
                    После ответа я покажу следующий вопрос.

                    Для справки отправь /help.

                    """ + quiz.currentPrompt();
        }

        // Не разрешаем отвечать до начала тренировки
        if (!started) {
            return "Напиши /start, чтобы начать тренировку.";
        }

        // Проверяем ответ и переходим к следующему вопросу
        boolean correct = quiz.isCorrect(input);

        quiz.nextQuestion();

        return (correct
                ? "Верно!"
                : "Неверно. Попробуем другое слово.")
                + "\n" + quiz.currentPrompt();
    }
}
