package com.facebook.kshia.flixster;

import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kshia on 6/15/16.
 */
public class Movie {

    private String title;
    private String posterUrl;
    private String backdropUrl;
    private String overview;
    private double popularity;
    private double rating;
    private String id;
    private String videoKey;

    public Movie(JSONObject jsonObject) throws JSONException {
        posterUrl = jsonObject.getString("poster_path");
        title = jsonObject.getString("original_title");
        overview = jsonObject.getString("overview");
        backdropUrl = jsonObject.getString("backdrop_path");
        popularity = jsonObject.getDouble("popularity");
        rating = jsonObject.getDouble("vote_average");
        id = jsonObject.getString("id");

    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return String.format("https://image.tmdb.org/t/p/w342%s", posterUrl);
    }

    public String getBackdropUrl() {
        Log.d("BackdropUrl", "" + backdropUrl);

        if (backdropUrl.equals("null")) {
            Log.d("BackdropUrl", "null detected");
            return null;
        }

        return String.format("https://image.tmdb.org/t/p/w342%s", backdropUrl);
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    public String getVideoKey() {
        return videoKey;
    }

    /*    public Movie(String title, String posterUrl, int rating) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.rating = rating;
    }

    public static ArrayList<Movie> getFakeMovies() {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            movies.add(new Movie("The Social Network", "", 75));
            movies.add(new Movie("The Internship", "", 50));
            movies.add(new Movie("The Lion King", "", 100));
        }

        return movies;
    }

    @Override
    public String toString() {
        return title + " - " + rating;
    }*/
}
