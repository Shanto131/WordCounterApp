
package com.example.wordcounterapp;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35)
public class MainActivityTest {

    @Test
    public void onCreate_initializesViewsAndButtons() {
        MainActivity activity =
                Robolectric.buildActivity(MainActivity.class)
                        .setup()
                        .get();

        assertTrue(activity.findViewById(R.id.inputText) instanceof EditText);
        assertTrue(activity.findViewById(R.id.resultText) instanceof TextView);
        assertTrue(activity.findViewById(R.id.historyText) instanceof TextView);
        assertTrue(activity.findViewById(R.id.calculateButton) instanceof Button);
        assertTrue(activity.findViewById(R.id.testButton) instanceof Button);
        assertTrue(activity.findViewById(R.id.clearButton) instanceof Button);
    }

    @Test
    public void calculateButton_withBlankInputDoesNotAddHistory() {
        MainActivity activity =
                Robolectric.buildActivity(MainActivity.class)
                        .setup()
                        .get();

        Button calculateButton =
                activity.findViewById(R.id.calculateButton);

        TextView history =
                activity.findViewById(R.id.historyText);

        calculateButton.performClick();

        assertEquals(
                "History will appear here",
                history.getText().toString()
        );
    }

    @Test
    public void calculateButton_withValidInputDisplaysResultsAndHistory() {
        MainActivity activity =
                Robolectric.buildActivity(MainActivity.class)
                        .setup()
                        .get();

        EditText input = activity.findViewById(R.id.inputText);
        TextView result = activity.findViewById(R.id.resultText);
        TextView history = activity.findViewById(R.id.historyText);
        Button calculateButton =
                activity.findViewById(R.id.calculateButton);

        input.setText("Hello world!");
        calculateButton.performClick();

        String resultText = result.getText().toString();

        assertTrue(resultText.contains("Sentences: 1"));
        assertTrue(resultText.contains("Words: 2"));
        assertTrue(resultText.contains("Punctuation: 1"));
        assertTrue(resultText.contains("Numbers: 0"));
        assertTrue(resultText.contains("Characters (without spaces): 11"));

        assertTrue(history.getText().toString().contains("Hello world!"));
    }

    @Test
    public void testButton_populatesSampleAndCalculatesIt() {
        MainActivity activity =
                Robolectric.buildActivity(MainActivity.class)
                        .setup()
                        .get();

        EditText input = activity.findViewById(R.id.inputText);
        TextView result = activity.findViewById(R.id.resultText);
        TextView history = activity.findViewById(R.id.historyText);
        Button testButton = activity.findViewById(R.id.testButton);

        testButton.performClick();

        assertEquals(
                "Good morning! Today is Octobor 9. I have 2 books.",
                input.getText().toString()
        );

        assertFalse(result.getText().toString().isEmpty());
        assertTrue(history.getText().toString().contains("Good morning!"));
    }

    @Test
    public void clearButton_clearsInputAndResultButPreservesHistory() {
        MainActivity activity =
                Robolectric.buildActivity(MainActivity.class)
                        .setup()
                        .get();

        EditText input = activity.findViewById(R.id.inputText);
        TextView result = activity.findViewById(R.id.resultText);
        TextView history = activity.findViewById(R.id.historyText);

        Button calculateButton =
                activity.findViewById(R.id.calculateButton);
        Button clearButton =
                activity.findViewById(R.id.clearButton);

        input.setText("Keep this in history.");
        calculateButton.performClick();

        String historyBeforeClear = history.getText().toString();

        clearButton.performClick();

        assertEquals("", input.getText().toString());
        assertEquals("", result.getText().toString());
        assertEquals(historyBeforeClear, history.getText().toString());
    }

    @Test
    public void calculateButton_multipleInputsShowsNewestHistoryFirst() {
        MainActivity activity =
                Robolectric.buildActivity(MainActivity.class)
                        .setup()
                        .get();

        EditText input = activity.findViewById(R.id.inputText);
        TextView history = activity.findViewById(R.id.historyText);
        Button calculateButton =
                activity.findViewById(R.id.calculateButton);

        input.setText("First entry.");
        calculateButton.performClick();

        input.setText("Second entry!");
        calculateButton.performClick();

        String historyText = history.getText().toString();

        int secondPosition = historyText.indexOf("Second entry!");
        int firstPosition = historyText.indexOf("First entry.");

        assertTrue(secondPosition >= 0);
        assertTrue(firstPosition >= 0);
        assertTrue(secondPosition < firstPosition);
    }
}
