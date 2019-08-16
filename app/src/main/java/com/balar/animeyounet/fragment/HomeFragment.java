package com.balar.animeyounet.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balar.animeyounet.R;
import com.balar.animeyounet.adapter.AnimeAdapter;
import com.balar.animeyounet.entity.AnimeItem;
import com.balar.animeyounet.entity.SeriesItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView rvanime;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<AnimeItem> animex;
            private static final String JSON_URL = "https://animeyou.net/api/home.php";

    private SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        rvanime = view.findViewById(R.id.rv_list_anime);
        rvanime.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvanime.setHasFixedSize(true);
        animex = new ArrayList<>();
        loadAnime();

        swipeRefreshLayout = view.findViewById(R.id.swLayout);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);

        swipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            swipeRefreshLayout.setRefreshing(false);
            loadAnime();
            Toast.makeText(getActivity(), "Refreshed", Toast.LENGTH_SHORT).show();
        },3000));
        return view;

    }

    private void loadAnime() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, response -> {
            try {
                JSONObject object = new JSONObject(response);
                JSONArray animeArray = object.getJSONArray("data");

                for (int i = 0; i < animeArray.length(); i++) {
                    JSONObject animeObject = animeArray.getJSONObject(i);

                    AnimeItem animeItem = new AnimeItem(
                            animeObject.getString("id"),
                            animeObject.getString("judul"),
                            animeObject.getString("gambar"),
                            animeObject.getString("tanggal"),
                            animeObject.getString("video"),
                            animeObject.getString("video2"),
                            animeObject.getString("video3"),
                            animeObject.getString("judul_series"),
                            animeObject.getString("gambar_series"),
                            animeObject.getString("url")
//                            animeObject.getString("genre"),

//                            animeObject.getString("judul_series"),
//                            animeObject.getString("gambar_series"),
//                            animeObject.getString("halaman")

                    );
                    animex.add(animeItem);
                }
                adapter = new AnimeAdapter(animex, getActivity());
                rvanime.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            loadAnime();
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

}
