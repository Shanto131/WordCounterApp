package com.example.wordcounterapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HistoryItemTest {

    @Test
    public void constructorAndGetters_returnProvidedValues() {
        long before = System.currentTimeMillis();
        CalculationResult result = new CalculationResult(1, 2, 3, 4, 5);

        HistoryItem item = new HistoryItem("Hello", result);

        long after = System.currentTimeMillis();
        assertEquals("Hello", item.GetInputText());
        assertSame(result, item.GetResult());
        assertTrue(item.GetTimestamp() >= before);
        assertTrue(item.GetTimestamp() <= after);
    }

    @Test
    public void formattedDisplay_includesInputAndFormattedMetrics() {
        CalculationResult result = new CalculationResult(1, 2, 3, 4, 5);
        HistoryItem item = new HistoryItem("Hello world", result);

        assertEquals(
                "\"Hello world\" => Sentences: 1, Words: 2, Punctuation: 3, Numbers: 4, Characters: 5",
                item.GetFormattedDisplay()
        );
    }

    @Test
    public void constructor_preservesEmptyInputText() {
        HistoryItem item = new HistoryItem("", new CalculationResult(0, 0, 0, 0, 0));

        assertNotNull(item);
        assertEquals("", item.GetInputText());
    }
}
