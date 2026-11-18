
package dev.kotori;

import java.util.List;
import java.util.Objects;

public class QuizService {

    private final List<Question> questions;
    private int index = 0;

    public QuizService(QuestionRepository repository) {
        Objects.requireNonNull(repository, "repository");
        this.questions = List.copyOf(repository.findAll());
    }

    public boolean hasQuestions() {
        return !questions.isEmpty();
    }

    public String currentPrompt() {
        if (!hasQuestions()) {
            return "Пока нет слов для тренировки.";
        }

        return "Как переводится «"
                + questions.get(index).japanese()
                + "»?";
    }

    public boolean isCorrect(String answer) {
        if (!hasQuestions() || answer == null) {
            return false;
        }

        return questions.get(index)
                .russian()
                .strip()
                .equalsIgnoreCase(answer.strip());
    }

    public void nextQuestion() {
        if (hasQuestions()) {
            index = (index + 1) % questions.size();
        }
    }

    public void reset() {
        index = 0;
    }
}
