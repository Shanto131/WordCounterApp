package com.example.wordcounterapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TextMetricCalculator
 *
 * Reusable utility class responsible for calculating text metrics.
 * Follows separation of concerns - contains zero Android UI dependencies.
 *
 * Requirements:
 * 1. Number of sentences
 * 2. Number of words
 * 3. Number of punctuation marks
 * 4. Number of numbers
 */
public class TextMetricCalculator {

    // Regex pattern for finding words (alphanumeric sequences with optional apostrophes or hyphens)
    private static final Pattern WORD_PATTERN = Pattern.compile("[a-zA-Z0-9]+(?:['’][a-zA-Z0-9]+)?");

    // Regex pattern for identifying numbers (integer or decimal)
    private static final Pattern NUMBER_PATTERN = Pattern.compile(
            "(?<![\\p{L}\\p{N}_.])\\d+(?:\\.\\d+)?(?![\\p{L}\\p{N}_.])"
    );

    /**
     * Counts complete integer and decimal number tokens.
     * Examples: 42, 3.14, 1000
     */
    public static int CountNumber(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        Matcher matcher = NUMBER_PATTERN.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        return count;
    }

    // Regex pattern for punctuation marks
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[\\p{Punct}&&[^\\s]]");

    /**
     * Calculates all metrics for the given input text.
     *
     * @param inputText The string to be analyzed
     * @return CalculationResult object containing all calculated metrics
     */
    public static CalculationResult CalculateMetrics(String inputText) {
        if (inputText == null || inputText.trim().isEmpty()) {
            return new CalculationResult(0, 0, 0, 0, 0);
        }

        int sentenceCount = CountSentences(inputText);
        int wordCount = CountWords(inputText);
        int punctuationCount = CountPunctuation(inputText);
        int numberCount = CountNumbers(inputText);
        int characterCount = CountCharactersExcludingSpaces(inputText);

        return new CalculationResult(
                sentenceCount,
                wordCount,
                punctuationCount,
                numberCount,
                characterCount
        );
    }

    /**
     * Calculates the number of sentences.
     * Sentences typically terminate with '.', '!', or '?'.
     * If text contains words but no terminal punctuation, it is counted as 1 sentence.
     */

    /**
     * Counts sentences using terminal punctuation.
     *
     * Repeated terminators such as "..." and "?!" count as one delimiter.
     * Closing quotes and brackets after terminal punctuation are supported.
     * Text containing words but no recognized terminator counts as one sentence.
     */
    public static int CountSentences(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        String trimmed = text.trim();

        Pattern sentenceTerminatorPattern = Pattern.compile(
                "[.!?]+[\\\"'”’»)\\]}]*(?=\\s+|$)"
        );

        Matcher matcher = sentenceTerminatorPattern.matcher(trimmed);

        int count = 0;

        while (matcher.find()) {
            count++;
        }

        // Preserve the existing behavior for text without terminal punctuation.
        if (count == 0 && CountWords(trimmed) > 0) {
            count = 1;
        }

        return count;
    }

    /**
     * Calculates the number of words.
     * Splits by whitespace and counts valid word tokens.
     */
    public static int CountWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        Matcher matcher = WORD_PATTERN.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Calculates the number of punctuation marks.
     * e.g., '.', ',', '!', '?', ';', ':', '-', '(', ')', '"', etc.
     */
    public static int CountPunctuation(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        Matcher matcher = PUNCTUATION_PATTERN.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Calculates the number of numerical values (e.g. 1, 42, 3.14).
     */
    public static int CountNumbers(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        Matcher matcher = NUMBER_PATTERN.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Calculates total characters excluding whitespace.
     */
    public static int CountCharactersExcludingSpaces(String text) {
        if (text == null) {
            return 0;
        }
        return text.replaceAll("\\s+", "").length();
    }
}

