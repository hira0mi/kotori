package dev.kotori.quiz;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicDictionaryTest {

    @Test
    void dictionaryIsNotEmpty() {
        assertFalse(BasicDictionary.questions().isEmpty());
    }

    @Test
    void dictionaryIsImmutable() {
        List<Question> questions = BasicDictionary.questions();

        assertThrows(UnsupportedOperationException.class,
                () -> questions.add(new Question("犬", "собака")));
    }

    @Test
    void dictionaryContainsExampleWords() {
        var questions = BasicDictionary.questions();

        assertTrue(questions.contains(new Question("猫", "кошка")));
        assertTrue(questions.contains(new Question("水", "вода")));
    }

    @Test
    void japaneseWordsAreUnique() {
        var questions = BasicDictionary.questions();
        var seen = new HashSet<String>();

        for (Question question : questions) {
            assertTrue(seen.add(question.japanese()),
                    "Duplicate Japanese word: " + question.japanese());
        }
    }
}