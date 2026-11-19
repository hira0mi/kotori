
package dev.kotori;

import org.junit.jupiter.api.Test;
import java.util.List;
import dev.kotori.quiz.Question;
import dev.kotori.quiz.QuestionRepository;
import dev.kotori.quiz.InMemoryQuestionRepository;
import static org.junit.jupiter.api.Assertions.*;

class DialogEngineTest {


    private DialogEngine engine() {
        QuestionRepository repository =
                new InMemoryQuestionRepository(
                        List.of(
                                new Question("猫", "кошка"),
                                new Question("水", "вода")
                        )
                );

        return new DialogEngine(
                new QuizService(repository)
        );
    }


    @Test
    void startsAndShowsFirstQuestion() {
        assertTrue(engine().handle("/start").contains("猫"));
    }

    @Test
    void correctAnswerMovesToNextQuestion() {
        DialogEngine d = engine();
        d.handle("/start");

        assertEquals(
                "Верно!\nКак переводится «水»?",
                d.handle("  КОШКА ")
        );
    }

    @Test
    void incorrectAnswerMovesToNextQuestion() {
        DialogEngine d = engine();
        d.handle("/start");

        assertTrue(d.handle("собака").contains("水"));
    }

    @Test
    void helpIsAvailableBeforeStart() {
        assertTrue(engine().handle("/help").contains("/start"));
    }

    @Test
    void asksToStartBeforeAnswer() {
        assertTrue(engine().handle("кошка").contains("/start"));
    }

    @Test
    void resetsOnStart() {
        DialogEngine d = engine();

        d.handle("/start");
        d.handle("кошка");

        assertTrue(d.handle("/start").contains("猫"));
    }

    @Test
    void emptyRepositoryDoesNotCrash() {

        DialogEngine d = new DialogEngine(
                new QuizService(
                        new InMemoryQuestionRepository(List.of())
                )
        );


        assertTrue(d.handle("/start").contains("нет слов"));
    }

    @Test
    void helpDoesNotResetQuestion() {
        DialogEngine d = engine();

        d.handle("/start");
        d.handle("кошка");
        d.handle("/help");

        assertEquals(
                "Верно!\nКак переводится «猫»?",
                d.handle("вода")
        );
    }

    @Test
    void supportsBackslashHelp() {
        DialogEngine d = engine();

        assertTrue(
                d.handle("\\help").contains("/start")
        );
    }

    @Test
    void cyclesThroughQuestions() {
        DialogEngine d = engine();

        d.handle("/start");
        d.handle("кошка");
        d.handle("вода");

        assertEquals(
                "Верно!\nКак переводится «水»?",
                d.handle("кошка")
        );
    }

    @Test
    void reportsIncorrectAnswer() {
        DialogEngine d = engine();
        d.handle("/start");

        String response = d.handle("собака");

        assertTrue(response.contains("Неверно"));
        assertTrue(response.contains("水"));
    }

    @Test
    void handlesBlankMessage() {
        DialogEngine d = engine();

        assertDoesNotThrow(() -> d.handle(""));
        assertDoesNotThrow(() -> d.handle(null));
    }

}
