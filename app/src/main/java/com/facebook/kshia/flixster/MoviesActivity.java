package com.facebook.kshia.flixster;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    ArrayList<Movie> movies;
    ListView lvMovies;
    MoviesAdapter adapter;
    AsyncHttpClient client;
    String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchMoviesAsync();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // Initialize movies
        movies = new ArrayList<>();

        // 2. Get the ListView that we want to populate
        lvMovies = (ListView) findViewById(R.id.lvMovies);

        // 3. Create an ArrayAdapter
        //ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies);
        adapter = new MoviesAdapter(this, movies);

        // 4. Associate the adapter with the ListView
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
            lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(MoviesActivity.this, DetailsActivity.class);
                    i.putExtra("title", movies.get(position).getTitle());
                    i.putExtra("rating", movies.get(position).getRating());
                    i.putExtra("backdrop", movies.get(position).getBackdropUrl());
                    i.putExtra("synopsis", movies.get(position).getOverview());
                    i.putExtra("popularity", movies.get(position).getPopularity());
                    startActivity(i);
                }
            });
        }

        client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    adapter.notifyDataSetChanged();
                    Log.d("DEBUG", movies.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    public void fetchMoviesAsync() {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        client.get(url, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.clear();
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));

                    adapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                    Log.d("DEBUG", movies.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }
}