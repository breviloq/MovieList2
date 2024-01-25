package com.example.movielist2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddMovieActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText yearEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        titleEditText = findViewById(R.id.editTextTitle);
        yearEditText = findViewById(R.id.editTextYear);
        saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString().trim();
                String yearString = yearEditText.getText().toString().trim();

                if (title.isEmpty() || yearString.isEmpty()) {
                    Toast.makeText(AddMovieActivity.this, "Фильм не добавлен", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                int year;
                try {
                    year = Integer.parseInt(yearString);
                } catch (NumberFormatException e) {
                    Toast.makeText(AddMovieActivity.this, "Введите корректный год", Toast.LENGTH_SHORT).show();
                    return;
                }

                Movie newMovie = new Movie(title, year, false);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("new_movie", newMovie);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
