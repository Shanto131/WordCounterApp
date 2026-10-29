package com.example.wordcounterapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextMetricCalculatorTest {

    @Test
    public void countNumber_returnsZeroForNullAndBlankInput() {
        assertEquals(0, TextMetricCalculator.CountNumber(null));
        assertEquals(0, TextMetricCalculator.CountNumber(""));
        assertEquals(0, TextMetricCalculator.CountNumber("   "));
    }

    @Test
    public void countNumber_countsIntegersAndDecimals() {
        assertEquals(3, TextMetricCalculator.CountNumber("I have 42 apples, 3.14 pies, and 1000 coins."));
    }

    @Test
    public void calculateMetrics_returnsZeroMetricsForNullAndBlankInput() {
        assertMetrics(new CalculationResult(0, 0, 0, 0, 0),
                TextMetricCalculator.CalculateMetrics(null));
        assertMetrics(new CalculationResult(0, 0, 0, 0, 0),
                TextMetricCalculator.CalculateMetrics("   \n\t"));
    }

    @Test
    public void calculateMetrics_calculatesAllMetrics() {
        CalculationResult result = TextMetricCalculator.CalculateMetrics("Hello world! I have 2 books.");

        assertEquals(2, result.sentenceCount);
        assertEquals(6, result.wordCount);
        assertEquals(2, result.punctuationCount);
        assertEquals(1, result.numberCount);
        assertEquals("Helloworld!Ihave2books.".length(), result.characterCount);
    }

    @Test
    public void countSentences_handlesNullBlankRepeatedTerminatorsAndUnterminatedText() {
        assertEquals(0, TextMetricCalculator.CountSentences(null));
        assertEquals(0, TextMetricCalculator.CountSentences("  "));
        assertEquals(2, TextMetricCalculator.CountSentences("Really?! Yes..."));
        assertEquals(1, TextMetricCalculator.CountSentences("Hello there"));
        assertEquals(1, TextMetricCalculator.CountSentences("..."));
    }

    @Test
    public void countSentences_supportsClosingQuotesAndBrackets() {
        assertEquals(2, TextMetricCalculator.CountSentences("He said \"Go!\" (now.)"));
    }

    @Test
    public void countWords_handlesNullBlankAndCountsWordTokens() {
        assertEquals(0, TextMetricCalculator.CountWords(null));
        assertEquals(0, TextMetricCalculator.CountWords(" \t "));
        assertEquals(5, TextMetricCalculator.CountWords("Hello don't re-enter 42"));
    }

    @Test
    public void countPunctuation_handlesNullEmptyAndCountsPunctuation() {
        assertEquals(0, TextMetricCalculator.CountPunctuation(null));
        assertEquals(0, TextMetricCalculator.CountPunctuation(""));
        assertEquals(5, TextMetricCalculator.CountPunctuation("Hi, (there)! OK?"));
    }

    @Test
    public void countNumbers_handlesNullBlankAndNumberBoundaries() {
        assertEquals(0, TextMetricCalculator.CountNumbers(null));
        assertEquals(0, TextMetricCalculator.CountNumbers("  "));
        assertEquals(3, TextMetricCalculator.CountNumbers("42, 3.14 and 1000"));
        assertEquals(0, TextMetricCalculator.CountNumbers("abc42def 3.14.15"));
    }

    @Test
    public void countCharactersExcludingSpaces_handlesNullAndRemovesWhitespace() {
        assertEquals(0, TextMetricCalculator.CountCharactersExcludingSpaces(null));
        assertEquals(0, TextMetricCalculator.CountCharactersExcludingSpaces(""));
        assertEquals(4, TextMetricCalculator.CountCharactersExcludingSpaces("a b\nc\t d"));
    }

    private void assertMetrics(CalculationResult expected, CalculationResult actual) {
        assertEquals(expected.sentenceCount, actual.sentenceCount);
        assertEquals(expected.wordCount, actual.wordCount);
        assertEquals(expected.punctuationCount, actual.punctuationCount);
        assertEquals(expected.numberCount, actual.numberCount);
        assertEquals(expected.characterCount, actual.characterCount);
    }
}
