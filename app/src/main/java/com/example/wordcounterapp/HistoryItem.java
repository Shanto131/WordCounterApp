package com.example.wordcounterapp;

/**
 * HistoryItem
 *
 * Represents an entry in the calculation history during the application runtime.
 */
public class HistoryItem {

    private final String inputText;
    private final CalculationResult result;
    private final long timestamp;

    public HistoryItem(String inputText, CalculationResult result) {
        this.inputText = inputText;
        this.result = result;
        this.timestamp = System.currentTimeMillis();
    }

    public String GetInputText() {
        return inputText;
    }

    public CalculationResult GetResult() {
        return result;
    }

    public long GetTimestamp() {
        return timestamp;
    }

    /**
     * Formatted string representation for history list.
     * Example: "Good morning" => 1, 2, 11, 0
     */
    public String GetFormattedDisplay() {
        return String.format("\"%s\" => %s", inputText, result.toFormattedSummary());
    }
}
