package com.balar.animeyounet.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balar.animeyounet.R;
import com.balar.animeyounet.activity.MainActivity;
import com.balar.animeyounet.adapter.AnimeAdapter;
import com.balar.animeyounet.adapter.AnimeItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView rvanime;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<AnimeItem> animex;
    private static final String JSON_URL = "https://animeyou.net/api/home.php";



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
        rvanime = view.findViewById(R.id.rv_category);
        rvanime.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvanime.setHasFixedSize(true);
        animex = new ArrayList<>();
        loadAnime();
        return view;

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
                    }
                    adapter = new AnimeAdapter(animex, getActivity());
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
