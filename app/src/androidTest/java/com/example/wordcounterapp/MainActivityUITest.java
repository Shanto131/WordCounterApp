package com.example.wordcounterapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

import android.widget.TextView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Scenario 1: Enter text, calculate metrics, and verify results.
    @Test
    public void calculateButtonDisplaysCorrectResults() {
        String input = "Hello world! I have 2 cats.";

        onView(withId(R.id.inputText))
                .perform(replaceText(input), closeSoftKeyboard());

        onView(withId(R.id.calculateButton))
                .perform(click());

        onView(withId(R.id.resultText))
                .check(matches(withText(containsString("Sentences: 2"))));

        onView(withId(R.id.resultText))
                .check(matches(withText(containsString("Words: 6"))));

        onView(withId(R.id.resultText))
                .check(matches(withText(containsString("Punctuation: 2"))));

        onView(withId(R.id.resultText))
                .check(matches(withText(containsString("Numbers: 1"))));

        onView(withId(R.id.resultText))
                .check(matches(withText(
                        containsString("Characters (without spaces): 22")
                )));

        onView(withId(R.id.historyText))
                .check(matches(withText(containsString(input))));
    }

    // Scenario 2: Empty input must not create a history entry.
    @Test
    public void calculateButtonWithEmptyInputDoesNotAddHistory() {
        onView(withId(R.id.calculateButton))
                .perform(click());

        onView(withId(R.id.resultText))
                .check(matches(withText("Results will appear here")));

        onView(withId(R.id.historyText))
                .check(matches(withText("History will appear here")));
    }

    // Scenario 3: Generate Test inserts the predefined text and calculates it.
    @Test
    public void generateTestDisplaysExpectedResults() {
        String generatedInput =
                "Good morning! Today is Octobor 9. I have 2 books.";

        onView(withId(R.id.testButton))
                .perform(click());

        onView(withId(R.id.inputText))
                .check(matches(withText(generatedInput)));

        onView(withId(R.id.resultText))
                .check(matches(withText(containsString("Sentences: 3"))));

        onView(withId(R.id.resultText))
                .check(matches(withText(containsString("Words: 10"))));

        onView(withId(R.id.resultText))
                .check(matches(withText(containsString("Punctuation: 3"))));

        onView(withId(R.id.resultText))
                .check(matches(withText(containsString("Numbers: 1"))));

        onView(withId(R.id.resultText))
                .check(matches(withText(
                        containsString("Characters (without spaces): 40")
                )));

        onView(withId(R.id.historyText))
                .check(matches(withText(containsString(generatedInput))));
    }

    // Scenario 4: Clear removes input and results but preserves history.
    @Test
    public void clearButtonClearsInputAndResultsButPreservesHistory() {
        String input = "First input.";

        onView(withId(R.id.inputText))
                .perform(replaceText(input), closeSoftKeyboard());

        onView(withId(R.id.calculateButton))
                .perform(click());

        onView(withId(R.id.historyText))
                .check(matches(withText(containsString(input))));

        onView(withId(R.id.clearButton))
                .perform(click());

        onView(withId(R.id.inputText))
                .check(matches(withText("")));

        onView(withId(R.id.resultText))
                .check(matches(withText("")));

        onView(withId(R.id.historyText))
                .check(matches(withText(containsString(input))));
    }

    // Scenario 5: Multiple calculations appear in history, newest first.
    @Test
    public void runtimeHistoryShowsNewestCalculationFirst() {
        String firstInput = "First input.";
        String secondInput = "Second input!";

        onView(withId(R.id.inputText))
                .perform(replaceText(firstInput), closeSoftKeyboard());

        onView(withId(R.id.calculateButton))
                .perform(click());

        onView(withId(R.id.inputText))
                .perform(replaceText(secondInput), closeSoftKeyboard());

        onView(withId(R.id.calculateButton))
                .perform(click());

        onView(withId(R.id.historyText))
                .check((view, noViewFoundException) -> {
                    TextView historyView = (TextView) view;
                    String history = historyView.getText().toString();

                    int firstPosition = history.indexOf(firstInput);
                    int secondPosition = history.indexOf(secondInput);

                    assertTrue("First calculation should appear in history",
                            firstPosition >= 0);

                    assertTrue("Second calculation should appear in history",
                            secondPosition >= 0);

                    assertTrue("Newest calculation should appear first",
                            secondPosition < firstPosition);
                });
    }
}