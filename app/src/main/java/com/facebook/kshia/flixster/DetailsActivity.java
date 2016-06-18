package com.facebook.kshia.flixster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailsActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String title = getIntent().getStringExtra("title");
        Double rating = getIntent().getDoubleExtra("rating", 0);
        String backdropUrl = getIntent().getStringExtra("backdrop");
        String synopsis = getIntent().getStringExtra("synopsis");
        Double popularity = getIntent().getDoubleExtra("popularity", 0);
        final String key = getIntent().getStringExtra("key");

        ImageView ivBackdrop = (ImageView) findViewById(R.id.ivBackdrop);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        RatingBar rbRatingBar = (RatingBar) findViewById(R.id.rbRating);
        TextView tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        TextView tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        TextView tvRating = (TextView) findViewById(R.id.tvRating);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        if(key.equals("null")) {
            Picasso.with(this).load(backdropUrl)
                    .placeholder(R.drawable.backdropdefault)
                    .into(ivBackdrop);
        }
        else {
            youTubePlayerView.initialize("AIzaSyA6CVQ5F5mr26WDcPMLbZeWREv4-tSdvI8",
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {

                            // do any work here to cue video, play video, etc.
                            youTubePlayer.cueVideo(key);
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
        }

        tvTitle.setText(title);
        rbRatingBar.setIsIndicator(true);
        rbRatingBar.setStepSize(0.5f);
        Double ratingOutOf5 = rating / 2.0;
        rbRatingBar.setRating(Math.round(ratingOutOf5.floatValue()));

        Log.d("RATING", "Rating: " + rating.floatValue() + "  RatingOf5: " + ratingOutOf5
                + "RatingRounded: " + Math.round(ratingOutOf5.floatValue()));



        tvSynopsis.setText(synopsis);
        tvPopularity.setText("Popularity: " + String.format("%.2f", popularity));
        tvRating.setText("" + ratingOutOf5);

    }
}
