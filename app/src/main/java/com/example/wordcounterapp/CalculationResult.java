
package com.example.wordcounterapp;

public class CalculationResult {
    public final int sentenceCount;
    public final int wordCount;
    public final int punctuationCount;
    public final int numberCount;
    public final int characterCount;

    public CalculationResult(
            int sentenceCount,
            int wordCount,
            int punctuationCount,
            int numberCount,
            int characterCount) {
        this.sentenceCount = sentenceCount;
        this.wordCount = wordCount;
        this.punctuationCount = punctuationCount;
        this.numberCount = numberCount;
        this.characterCount = characterCount;
    }

    public String toFormattedSummary() {
        return "Sentences: " + sentenceCount
                + ", Words: " + wordCount
                + ", Punctuation: " + punctuationCount
                + ", Numbers: " + numberCount
                + ", Characters: " + characterCount;
    }

}
