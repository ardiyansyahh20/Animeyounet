package com.balar.animeyounet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balar.animeyounet.adapter.AnimeAdapter;
import com.balar.animeyounet.adapter.AnimeItem;
import com.balar.animeyounet.R;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{
    private static final String JSON_URL = "https://animeyou.net/api/home.php";

    private SwipeRefreshLayout swipeRefreshLayout;



    private ProgressDialog dialog;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        loadAnime();
                        return true;
                    case R.id.navigation_search:
                        Intent intent = new Intent(MainActivity.this, Search.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_collection:
                        return true;
                }
                return false;
            };


    @BindView(R.id.rv_category)
    RecyclerView rvAnime;
    AnimeAdapter adapter;

    final ArrayList<AnimeItem> animex = new ArrayList<>();
    private PublisherAdView mPublisherAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        swipeRefreshLayout = findViewById(R.id.swLayout);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);

        swipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            swipeRefreshLayout.setRefreshing(false);
            loadAnime();
        },3000));

        mPublisherAdView = findViewById(R.id.publisherAdView);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        mPublisherAdView.loadAd(adRequest);





        ButterKnife.bind(this);
        adapter = new AnimeAdapter(this);


        loadAnime();


    }




    private void loadAnime() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray animeArray = object.getJSONArray("data");

                    for (int i = 0; i < animeArray.length(); i++) {
                        JSONObject animeObject = animeArray.getJSONObject(i);

                        AnimeItem animeItem = new AnimeItem(
                                animeObject.getString("judul"),
                                animeObject.getString("gambar"),
                                animeObject.getString("tanggal"),
                                animeObject.getString("genre"),
                                animeObject.getString("video"),
                                animeObject.getString("video2"),
                                animeObject.getString("video3"),
                                animeObject.getString("judul_series"),
                                animeObject.getString("gambar_series"),
                                animeObject.getString("url"),
                                animeObject.getString("halaman")

                        );
                        Log.d("Test", "onResponse: "+animeItem.getJudul());
                        animex.add(animeItem);
                    }

                    Log.d("test", "onResponse: "+JSON_URL);
                    rvAnime.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter.setAnimeItemList(animex);
                    rvAnime.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show());


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



}




