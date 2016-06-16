package com.facebook.kshia.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
    }


    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);

        ViewHolder viewHolder;

        /*if (convertView == null) {
            viewHolder = new ViewHolder();
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        if (isPortraitAndPopular(movie)) {
            if (viewHolder.ivBackdropPopular == null) {
                viewHolder = new ViewHolder();

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, parent, false);

                // Lookup view for data population
                viewHolder.ivBackdropPopular = (ImageView) convertView.findViewById(R.id.ivBackdropPopular);

                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
        }
        else {

        }*/

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();

            if (isPortraitPopularBackdropExists(movie)) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, parent, false);

                // Lookup view for data population
                viewHolder.ivBackdropPopular = (ImageView) convertView.findViewById(R.id.ivBackdropPopular);
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

            if (viewHolder.ivBackdropPopular == null || isPortraitPopularBackdropExists(movie)) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, parent, false);

                // Lookup view for data population
                viewHolder.ivBackdropPopular = (ImageView) convertView.findViewById(R.id.ivBackdropPopular);
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

        String imageUri;

        if(isPortraitPopularBackdropExists(movie)) {
            imageUri = movie.getBackdropUrl();

            Picasso.with(getContext()).load(imageUri)
                    .placeholder(R.drawable.backdropdefault)
                    .transform(new RoundedCornersTransformation(20, 20))
                    .into(viewHolder.ivBackdropPopular);
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
        }

        return convertView;
    }

    public boolean isPortraitPopularBackdropExists(Movie movie) {
        return movie.getRating() > 6.0 &&
                movie.getBackdropUrl() != null &&
                getContext().getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE;
    }

    /*public View getViewPopular(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Movie movie = getItem(position);

        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, parent, false);

            // Lookup view for data population
            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivBackdropPopular);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageUri = movie.getBackdropUrl();

        Picasso.with(getContext()).load(imageUri).placeholder(R.drawable.movie_icon).transform(new RoundedCornersTransformation(20, 20)).into(viewHolder.ivPoster);

        Log.d("MoviesAdapter", "Position: " + position);

        // Return the completed view to render on screen
        return convertView;

    }

    public View getViewUnpopular(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Movie movie = getItem(position);

        String imageUri;

        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUri = movie.getBackdropUrl();
        }
        else {
            imageUri = movie.getPosterUrl();

        }

        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());


        Picasso.with(getContext()).load(imageUri).placeholder(R.drawable.movie_icon).transform(new RoundedCornersTransformation(20, 20)).into(viewHolder.ivPoster);

        Log.d("MoviesAdapter", "Position: " + position);

        // Return the completed view to render on screen
        return convertView;

    }*/
}
