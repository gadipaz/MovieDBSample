package com.paz.gadi.moviedbsample.services;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;
import com.paz.gadi.moviedbsample.models.MovieResponse;

/**
 * Created by Gadi Paz on 14/05/2018.
 */
public interface MovieApiService {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
