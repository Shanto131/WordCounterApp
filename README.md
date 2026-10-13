# WordCounterApp

An Android application built in Java that analyzes input text and calculates various linguistic and structural metrics in real-time.

---

## Project Overview

`WordCounterApp` is a utility-focused Android application designed to parse text inputs and compute precise statistics. Whether you are checking a quick paragraph or reviewing a longer block of text, the application breaks down the content into key metrics such as words, sentences, numbers, punctuation marks, and non-whitespace characters. It also includes an integrated runtime history log to keep track of past calculations.

---

## Features

- **Comprehensive Metric Calculations:**
  - **Word Count:** Identifies and counts distinct word tokens.
  - **Sentence Count:** Computes the total number of sentences based on terminal punctuation.
  - **Number Count:** Detects complete integer and decimal number tokens while avoiding partial matches inside larger values.
  - **Punctuation Count:** Evaluates punctuation marks using dedicated pattern matching.
  - **Characters (Excluding Whitespace):** Counts total character length minus all whitespace characters.
- **Interactive UI Controls:**
  - **Calculate Button:** Triggers the metric computation for your inputted text.
  - **Generate Test Button:** Populates sample text quickly for immediate testing.
  - **Clear Button:** Resets input fields and metrics instantly.
- **Runtime History:** Maintains a session history log (`HistoryItem.java`) to track previous calculations.

---

## Technologies

- **Language:** Java
- **Platform:** Android
- **IDE:** Android Studio
- **Core Libraries & Components:** 
  - `java.util.regex.Pattern` & `Matcher` for robust token extraction
  - Standard Android UI components (`MainActivity.java`, XML layouts)

---

## Prerequisites

Before running or building the project, ensure you have the following installed:
- [Android Studio](https://developer.android.com/studio) (latest stable version recommended)
- Android SDK (matching your project's compile and target SDK configurations)
- A physical Android device with USB debugging enabled or a configured Android Virtual Device (AVD Emulator)

---

## Setup and Run Instructions

1. **Clone or Download the Repository:**
   ```bash
   git clone <repository-url>
   ```
2. **Open in Android Studio:**
   - Launch Android Studio.
   - Select **Open an Existing Project** and navigate to your `WordCounterApp` directory.
3. **Sync Project with Gradle Files:**
   - Allow Android Studio to download dependencies and sync the Gradle files automatically.
4. **Build and Run:**
   - Select your target device or emulator from the device dropdown menu.
   - Click the **Run** (`▶`) button in the toolbar to compile and install the application on your device or emulator.

---

## Usage Instructions

1. Launch **WordCounterApp** on your Android device or emulator.
2. Enter or paste your target text into the main text input field.
   - *Tip:* Alternatively, tap the **Generate Test** button to automatically load sample text.
3. Tap the **Calculate** button to process the text.
4. View the calculated metrics displayed on screen.
5. Review previous entries via the **Runtime History** view.
6. Tap the **Clear** button to wipe the text field and reset results when ready for a new entry.

---

## Testing Steps

- **Basic Text Test:** Enter a standard sentence containing a mix of letters, numbers, and punctuation (e.g., `"The temperature is 98.6 degrees!"`) and verify that word, number, and punctuation counts match expectations.
- **Edge Cases:** 
  - Input empty text or whitespace-only text to verify null safety and zero-state handling.
  - Test decimal numbers (e.g., `12.34`) to ensure they register correctly as single numeric tokens without triggering partial sub-matches.
- **History Log Verification:** Run multiple text calculations and check the **Runtime History** list to ensure past items are correctly recorded and displayed.

---

## Limitations

- Processing performance may vary with extremely large blocks of text due to regular expression matching overhead on the main thread.
- Regular expression pattern rules are optimized for standard Western character sets and punctuation styles.
