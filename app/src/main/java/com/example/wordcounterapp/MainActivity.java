
package com.example.wordcounterapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView resultText;
    private TextView historyText;

    private final List<HistoryItem> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        resultText = findViewById(R.id.resultText);
        historyText = findViewById(R.id.historyText);

        Button calculateButton = findViewById(R.id.calculateButton);
        Button testButton = findViewById(R.id.testButton);
        Button clearButton = findViewById(R.id.clearButton);

        calculateButton.setOnClickListener(v -> calculateText());

        clearButton.setOnClickListener(v -> {
            inputText.setText("");
            resultText.setText("");
        });

        testButton.setOnClickListener(v -> {
            inputText.setText(
                    "Good morning! Today is Octobor 9. I have 2 books."
            );
            calculateText();
        });
    }

    private void calculateText() {
        String text = inputText.getText().toString();

        if (text.trim().isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter some text first.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        CalculationResult result =
                TextMetricCalculator.CalculateMetrics(text);

        resultText.setText(
                "Sentences: " + result.sentenceCount +
                        "\nWords: " + result.wordCount +
                        "\nPunctuation: " + result.punctuationCount +
                        "\nNumbers: " + result.numberCount +
                        "\nCharacters (without spaces): " + result.characterCount
        );

        HistoryItem item = new HistoryItem(text, result);
        history.add(0, item);
        updateHistory();
    }

    private void updateHistory() {
        StringBuilder builder = new StringBuilder();

        for (HistoryItem item : history) {
            builder.append(item.GetFormattedDisplay())
                    .append("\n\n");
        }

        historyText.setText(builder.toString());
    }
}
