package com.example.number;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;
    int attempts = 3;

    EditText value;
    TextView tResult;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = findViewById(R.id.value);
        tResult = findViewById(R.id.tResult);
        btn = findViewById(R.id.btn);

        resetGame();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void checkGuess() {
        String userInput = value.getText().toString();

        if (userInput.isEmpty()) {
            showToast("Пожалуйста, введите число");
            return;
        }

        try {
            int guessedNumber = Integer.parseInt(userInput);

            if (guessedNumber < 1 || guessedNumber > 20) {
                showToast("Введите число от 1 до 20");
                return;
            }

            if (guessedNumber < randomNumber) {
                tResult.setText("Число больше!");
            }
            else if (guessedNumber > randomNumber) {
                tResult.setText("Число меньше!");
            }
            else   {
                tResult.setText("Поздравляем, вы угадали!\nПопыток осталось: " + attempts);
                tResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark)); // Задаём зелёный цвет текста
                resetGame(); // Перезапуск игры
                return;
            }

            attempts--;

            if (attempts == 0) {
                tResult.setText("Вы проиграли! Правильный ответ был: " + randomNumber);
                resetGame();
            } else {
                tResult.append("\nОсталось попыток: " + attempts);
            }
        } catch (NumberFormatException e) {
            showToast("Пожалуйста, введите допустимое число");
        }
    }
    private void resetGame() {
        randomNumber = new Random().nextInt(20) + 1;
        attempts = 3;

    }
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
