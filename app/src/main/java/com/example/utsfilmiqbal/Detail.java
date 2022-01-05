package com.example.utsfilmiqbal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ImageView imageView = findViewById(R.id.poster_image);
        TextView release_tv = findViewById(R.id.mRelease);
        TextView rating_tv = findViewById(R.id.mRating);
        TextView title_tv = findViewById(R.id.mTitle);
        TextView overview_tv = findViewById(R.id.movervie_tv);

        Bundle bundle = getIntent().getExtras();

        String mTitle = bundle.getString("title");
        String mRelease = bundle.getString("release_date");
        String mPoster = bundle.getString("backdrop_path");
        String mOverView = bundle.getString("overview");
        double mRating = bundle.getDouble("vote_average");

        Glide.with(this).load(mPoster).into(imageView);
        rating_tv.setText(Double.toString(mRating));
        release_tv.setText(mRelease);
        title_tv.setText(mTitle);
        overview_tv.setText(mOverView);

    }
}