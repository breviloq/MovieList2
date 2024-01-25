package com.example.movielist2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MoviesListAdapter extends ArrayAdapter<Movie> {

    private ArrayList<Movie> movies;
    private Context context;

    public MoviesListAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.movie_item, movies); // Используйте R.layout.movie_item здесь
        this.movies = movies;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie currentMovie = this.movies.get(position);

        // Используйте convertView для повторного использования существующих представлений
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.movie_item, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle); // Измените на R.id.tvTitle
        // TextView tvYear = convertView.findViewById(R.id.tvYear); // Раскомментируйте, если нужно использовать год
        // tvYear.setText(String.valueOf(currentMovie.getReleaseYear())); // Установите год выпуска

        tvTitle.setText(currentMovie.getTitle()); // Установите название фильма

        return convertView;
    }
}
