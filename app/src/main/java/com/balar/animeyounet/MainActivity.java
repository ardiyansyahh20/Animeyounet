package com.balar.animeyounet;

import android.app.ProgressDialog;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{
    private static final String JSON_URL = "https://animeyou.net/api/home.php";
    /*ListView listView;

    private List<AnimeItem> animeItemList;*/

    private SwipeRefreshLayout swipeRefreshLayout;

    private ProgressDialog dialog;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        loadAnime();
                        return true;
                    case R.id.navigation_search:
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        swipeRefreshLayout = findViewById(R.id.swLayout);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(() -> {
                    swipeRefreshLayout.setRefreshing(false);
                    loadAnime();
                },3000);
            }
        });

//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        Toast.makeText(MainActivity.this, "Recents", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.navigation_search:
//                        Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.navigation_collection:
//                        Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//        });


        ButterKnife.bind(this);
        adapter = new AnimeAdapter(this);

        /*listView = findViewById(R.id.listView);
        animeItemList = new ArrayList<>();*/


        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();


        loadAnime();

    }



    /*@Override
    public void onRefresh() {
        setRefresh();
    }

    private void setRefresh() {

        swipeRefreshLayout.setRefreshing(false);
        loadAnime();

    }*/

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
                        /*animeItemList.add(animeItem);*/
                        animex.add(animeItem);
                    }

                    /*AnimeAdapter adapter = new AnimeAdapter(animeItemList, getApplicationContext());
                    listView.setAdapter(adapter);
                    dialog.dismiss();*/
                    Log.d("test", "onResponse: "+JSON_URL);
                    dialog.dismiss();
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


/*public class MainActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<AnimeItem> animeItemList;
    private AnimeAdapter adapter;
    private String JUDUL;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        animeItemList = new ArrayList<>();

        adapter = new AnimeAdapter(this);



        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();

    }



}*/





