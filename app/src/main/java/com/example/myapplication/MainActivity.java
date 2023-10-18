package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView questionText, resultText;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3, option4;
    private Button submitButton;

    private String[] questions = {
            "Question 1: What is the capital of India?",
            "Question 2: Which planet is known as the Red Planet?",
            "Question 3: The ratio of width of our National flag to its length is?",
            "Question 4: 'Dandia' is a popular dance of?",
            "Question 5: 'OS' computer abbreviation usually means?"

    };

    private String[] correctAnswers = {
            "New Delhi",
            "Mars",
            "2:3",
            "Gujarat",
            "Operating System"

    };

    private String[] options = {
            "Paris", "New Delhi", "Berlin", "Tokyo",
            "Venus", "Mars", "Jupiter", "Saturn",
            "3:5", "2:3", "2:5", "3:2",
            "Gujarat", "Bihar", "Kerala", "Punjab",
            "Open Software", "Order of Significance", "Optical Sensor", "Operating System"

    };

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        resultText = findViewById(R.id.resultText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submitButton = findViewById(R.id.submitButton);

        showQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void showQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionText.setText(questions[currentQuestionIndex]);
            option1.setText(options[currentQuestionIndex * 4]);
            option2.setText(options[currentQuestionIndex * 4 + 1]);
            option3.setText(options[currentQuestionIndex * 4 + 2]);
            option4.setText(options[currentQuestionIndex * 4 + 3]);
            optionsGroup.clearCheck();
            resultText.setText("");
        } else {
            questionText.setText("Quiz Finished!");
            optionsGroup.setVisibility(View.GONE);
            submitButton.setEnabled(false);
            resultText.setText("Your score: " + score + " out of " + questions.length);
        }
    }

    private void checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = correctAnswers[currentQuestionIndex];

            if (selectedAnswer.equals(correctAnswer)) {
                score++;
                resultText.setText("Correct!");
            } else {
                resultText.setText("Incorrect. Correct answer: " + correctAnswer);
            }

            currentQuestionIndex++;
            showQuestion();
        } else {
            resultText.setText("Please select an answer.");
        }
    }
}
