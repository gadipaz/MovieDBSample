package com.paz.gadi.moviedbsample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.paz.gadi.moviedbsample.R;
import com.paz.gadi.moviedbsample.models.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Gadi Paz on 14/05/2018.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Movie item);
    }

    private List<Movie> movies;
    private final OnItemClickListener listener;
    private int rowLayout;
    private Context context;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context, OnItemClickListener listener) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.listener = listener;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView movieImage;

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = v.findViewById(R.id.movies_layout);
            movieImage = v.findViewById(R.id.movie_image);
            movieTitle = v.findViewById(R.id.title);
            data = v.findViewById(R.id.date);
            movieDescription = v.findViewById(R.id.description);
            rating = v.findViewById(R.id.rating);
        }
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.movieImage);
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(movies.get(position));
            }
        });
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
