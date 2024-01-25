package com.example.movielist2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditMovieActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText yearEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        titleEditText = findViewById(R.id.editTextTitle);
        yearEditText = findViewById(R.id.editTextYear);
        saveButton = findViewById(R.id.buttonSave);

        final int position = getIntent().getIntExtra("edit_position", -1);
        if (position != -1) {
            Movie editedMovie = MainActivity.movieList.get(position);

            titleEditText.setText(editedMovie.getTitle());
            yearEditText.setText(String.valueOf(editedMovie.getReleaseYear()));

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = titleEditText.getText().toString().trim();
                    String yearString = yearEditText.getText().toString().trim();

                    if (title.isEmpty() || yearString.isEmpty()) {
                        Toast.makeText(EditMovieActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int year = Integer.parseInt(yearString);

                    editedMovie.setTitle(title);
                    editedMovie.setReleaseYear(year);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("edit_position", position);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            });

        }
    }
}
