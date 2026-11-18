package dev.kotori.quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionTest {

    @Test
    void createsQuestionWithValidData() {
        var question = new Question("猫", "кошка");

        assertEquals("猫", question.japanese());
        assertEquals("кошка", question.russian());
    }

    @Test
    void trimsLeadingAndTrailingWhitespace() {
        var question = new Question("  猫  ", "  кошка  ");

        assertEquals("猫", question.japanese());
        assertEquals("кошка", question.russian());
    }

    @Test
    void rejectsNullJapanese() {
        assertThrows(NullPointerException.class, () -> new Question(null, "кошка"));
    }

    @Test
    void rejectsNullRussian() {
        assertThrows(NullPointerException.class, () -> new Question("猫", null));
    }

    @Test
    void rejectsBlankJapanese() {
        assertThrows(IllegalArgumentException.class, () -> new Question("   ", "кошка"));
    }

    @Test
    void rejectsBlankRussian() {
        assertThrows(IllegalArgumentException.class, () -> new Question("猫", "   "));
    }

    @Test
    void equalityIsBasedOnComponents() {
        var a = new Question("猫", "кошка");
        var b = new Question("猫", "кошка");
        var c = new Question("犬", "собака");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }
}