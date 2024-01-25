package com.example.movielist2;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_MOVIE_REQUEST = 1;
    private static final int EDIT_MOVIE_REQUEST = 2;

    public static ArrayList<Movie> movieList; // Сделаем список публичным для доступа из EditMovieActivity
    private ArrayAdapter<Movie> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditMovieActivity.class);
                intent.putExtra("edit_position", position);
                startActivityForResult(intent, EDIT_MOVIE_REQUEST);
            }
        });

        Button addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intent, ADD_MOVIE_REQUEST);
            }
        });
    }

    // Добавление фильма в список
    private void addMovie(Movie movie) {
        movieList.add(movie);
        adapter.notifyDataSetChanged();
    }

    // Редактирование фильма в списке
    private void editMovie(int position) { // Изменено для принятия позиции
        Movie movie = movieList.get(position);
        Intent intent = new Intent(this, EditMovieActivity.class);
        intent.putExtra("edit_movie", movie);
        intent.putExtra("edit_position", position); // Добавление позиции в intent
        startActivityForResult(intent, EDIT_MOVIE_REQUEST);
    }

    // Удаление фильма из списка
    private void deleteMovie(int position) {
        if (position >= 0 && position < movieList.size()) {
            movieList.remove(position);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ADD_MOVIE_REQUEST) {
                Movie newMovie = (Movie) data.getSerializableExtra("new_movie");
                addMovie(newMovie);
            } else if (requestCode == EDIT_MOVIE_REQUEST) {
                int position = data.getIntExtra("edit_position", -1);
                if (position >= 0) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void updateMovieList(Movie updatedMovie, int position) {
        movieList.set(position, updatedMovie);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if (item.getItemId() == R.id.action_delete) {
            deleteMovie(position); // Используйте метод deleteMovie для удаления
            return true;
        }

        return super.onContextItemSelected(item);
    }
}
