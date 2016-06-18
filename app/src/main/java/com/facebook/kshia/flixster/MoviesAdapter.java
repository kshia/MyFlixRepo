package com.facebook.kshia.flixster;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by kshia on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    // View lookup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ImageView ivBackdropPopular;
        String movieId;
        YouTubePlayerView player;
        String videoKey;

        public String getMovieId() {
            return movieId;
        }
    }


    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    // Return an integer representing the type by fetching the enum type ordinal
    @Override
    public int getItemViewType(int position) {
        if (isPortraitPopularBackdropExists(getItem(position))) {
            return 1;
        }
        else {
            return 0;
        }
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type) {
        if (type == 1) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, null);
        } else {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
        }
    }

    // Total number of types is the number of enum values
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Movie movie = getItem(position);
        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            viewHolder.movieId = movie.getId();

            // Get the data item type for this position
            int type = getItemViewType(position);
            // Inflate XML layout based on the type
            convertView = getInflatedLayoutForType(type);

            if (isPortraitPopularBackdropExists(movie)) {
                // Lookup view for data population
                viewHolder.ivBackdropPopular = (ImageView) convertView.findViewById(R.id.ivBackdropPopular);
                viewHolder.player = (YouTubePlayerView) convertView.findViewById(R.id.player);
            }
            else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);

                // Lookup view for data population
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
                viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

            }

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageUri;

        if(isPortraitPopularBackdropExists(movie)) {
            imageUri = movie.getBackdropUrl();

            Picasso.with(getContext()).load(imageUri)
                    .placeholder(R.drawable.backdropdefault)
                    .transform(new RoundedCornersTransformation(20, 20))
                    .into(viewHolder.ivBackdropPopular);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Youtube", "play video clicked");

                    AsyncHttpClient client;

                    client = new AsyncHttpClient();
                    String youtubeUrl = "http://api.themoviedb.org/3/movie/" + movie.getId() + "/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

                    Log.d("Youtube", "Movie id: " + movie.getId());

                    client.get(youtubeUrl, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            JSONArray movieJsonResults = null;

                            try {
                                movieJsonResults = response.getJSONArray("results");

                                String key = movieJsonResults.getJSONObject(0).getString("key");
                                Intent i = new Intent(getContext(), QuickplayActivity.class);
                                i.putExtra("key", key);

                                if (!key.equals("null")) {
                                    getContext().startActivity(i);
                                }

                                //Log.d("Youtube", "Key: " + myView.videoKey);

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
            });
        }
        else {

            if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUri = movie.getBackdropUrl();
            }
            else {
                imageUri = movie.getPosterUrl();
            }

            if (viewHolder.tvTitle == null) {
                Log.d("Views", ":(");
            }

            // Populate the data into the template view using the data object
            viewHolder.tvTitle.setText(movie.getTitle());
            viewHolder.tvOverview.setText(movie.getOverview());

            Picasso.with(getContext()).load(imageUri).placeholder(R.drawable.movie_icon).transform(new RoundedCornersTransformation(20, 20)).into(viewHolder.ivPoster);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Youtube", "details clicked");

                    AsyncHttpClient client = new AsyncHttpClient();
                    //ViewHolder myView = (ViewHolder) v.getTag();
                    String youtubeUrl = "http://api.themoviedb.org/3/movie/" + movie.getId() + "/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

                    Log.d("Youtube", "Movie id: " + movie.getId());

                    client.get(youtubeUrl, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            JSONArray movieJsonResults = null;

                            try {
                                movieJsonResults = response.getJSONArray("results");

                                //ArrayList<Video> videoIds = new ArrayList<>();

                                String key = movieJsonResults.getJSONObject(0).getString("key");

                                Intent i = new Intent(getContext(), DetailsActivity.class);
                                i.putExtra("key", key);
                                i.putExtra("title", movie.getTitle());
                                i.putExtra("rating", movie.getRating());
                                i.putExtra("backdrop", movie.getBackdropUrl());
                                i.putExtra("synopsis", movie.getOverview());
                                i.putExtra("popularity", movie.getPopularity());

                                getContext().startActivity(i);

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
            });

        }

        return convertView;
    }

    public boolean isPortraitPopularBackdropExists(Movie movie) {
        return movie.getRating() > 6.0 &&
                movie.getBackdropUrl() != null &&
                getContext().getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE;
    }
}
