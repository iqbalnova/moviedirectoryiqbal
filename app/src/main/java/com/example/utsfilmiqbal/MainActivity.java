package com.example.utsfilmiqbal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = RequestAPI.getmInstance(this).getRequestQueue();

        movieList = new ArrayList<>();
        fetchMovies();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),AboutMe.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.dashboard:
                        return true;
                }
                return false;
            }
        });
    }

    private void fetchMovies() {

        String url = "https://api.jsonbin.io/b/6195e91462ed886f91509cd0";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0 ; i < response.length() ; i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String release = jsonObject.getString("release_date");
                        String overview = jsonObject.getString("overview");
                        String poster = jsonObject.getString("poster_path");
                        String backdrop = jsonObject.getString("backdrop_path");
                        Double rating = jsonObject.getDouble("vote_average");

                        Movie movie = new Movie(title , poster ,backdrop , release , overview , rating);
                        movieList.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    AdapterMovie adapter = new AdapterMovie(MainActivity.this , movieList);

                    recyclerView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}