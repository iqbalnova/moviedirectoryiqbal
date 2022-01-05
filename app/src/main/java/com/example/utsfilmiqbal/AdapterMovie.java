package com.example.utsfilmiqbal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MovieHolder> {

    private Context context;
    private List<Movie> movieList;

    public AdapterMovie(Context context , List<Movie> movies){
        this.context = context;
        movieList = movies;
    }
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_movie_list , parent , false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        Movie movie = movieList.get(position);
        holder.rating.setText(movie.getRating().toString());
        holder.title.setText(movie.getTitle());
        holder.release.setText(movie.getRelease());
        Glide.with(context).load(movie.getPoster()).into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , Detail.class);

                Bundle bundle = new Bundle();
                bundle.putString("title" , movie.getTitle());
                bundle.putString("overview" , movie.getOverview());
                bundle.putString("release_date" , movie.getRelease());
                bundle.putString("poster_path" , movie.getPoster());
                bundle.putString("backdrop_path" , movie.getBackdrop());
                bundle.putDouble("vote_average" , movie.getRating());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView imageView, backdrop;
        TextView title , overview ,release, rating;
        ConstraintLayout constraintLayout;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            backdrop = itemView.findViewById(R.id.poster_image);
            title = itemView.findViewById(R.id.title_tv);
            overview = itemView.findViewById(R.id.overview_tv);
            release = itemView.findViewById(R.id.release_tv);
            rating = itemView.findViewById(R.id.rating);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
