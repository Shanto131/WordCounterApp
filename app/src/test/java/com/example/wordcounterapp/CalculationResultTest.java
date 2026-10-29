package com.example.wordcounterapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculationResultTest {

    @Test
    public void constructorStoresAllMetricValues() {
        CalculationResult result = new CalculationResult(1, 2, 3, 4, 5);

        assertEquals(1, result.sentenceCount);
        assertEquals(2, result.wordCount);
        assertEquals(3, result.punctuationCount);
        assertEquals(4, result.numberCount);
        assertEquals(5, result.characterCount);
    }

    @Test
    public void toFormattedSummary_returnsExpectedText() {
        CalculationResult result = new CalculationResult(1, 2, 3, 4, 5);

        assertEquals(
                "Sentences: 1, Words: 2, Punctuation: 3, Numbers: 4, Characters: 5",
                result.toFormattedSummary()
        );
    }

    @Test
    public void toFormattedSummary_supportsZeroValues() {
        CalculationResult result = new CalculationResult(0, 0, 0, 0, 0);

        assertEquals(
                "Sentences: 0, Words: 0, Punctuation: 0, Numbers: 0, Characters: 0",
                result.toFormattedSummary()
        );
    }
}
