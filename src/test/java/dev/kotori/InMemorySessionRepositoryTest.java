package dev.kotori;

import dev.kotori.quiz.InMemoryQuestionRepository;
import dev.kotori.quiz.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemorySessionRepositoryTest {
    private final SessionRepository sessions = new InMemorySessionRepository();

    @Test
    void returnsExistingSessionForTheSameId() {
        UserSession first = sessions.getOrCreate(101L, this::newSession);

        UserSession again = sessions.getOrCreate(101L, () -> {
            throw new AssertionError("Existing session should be reused");
        });

        assertSame(first, again);
    }

    @Test
    void createsDifferentSessionsForDifferentIds() {
        UserSession first = sessions.getOrCreate(101L, this::newSession);
        UserSession second = sessions.getOrCreate(202L, this::newSession);

        assertNotSame(first, second);
    }

    @Test
    void startResetsTheExistingSession() {
        UserSession session = sessions.getOrCreate(101L, this::newSession);
        session.handle("/start");
        assertTrue(session.handle("кошка").contains("水"));

        assertTrue(session.handle("/start").contains("猫"));
        assertSame(session, sessions.getOrCreate(101L, this::newSession));
    }

    private UserSession newSession() {
        var questions = new InMemoryQuestionRepository(List.of(
                new Question("猫", "кошка"),
                new Question("水", "вода")
        ));
        return new UserSession(new DialogEngine(new QuizService(questions)));
    }
}