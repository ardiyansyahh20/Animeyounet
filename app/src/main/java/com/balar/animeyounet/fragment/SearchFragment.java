package com.balar.animeyounet.fragment;


import android.app.SearchManager;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balar.animeyounet.R;
import com.balar.animeyounet.adapter.SearchAdapter;
import com.balar.animeyounet.entity.AnimeSearchItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{
    public static ArrayList<AnimeSearchItem> imageModelArrayList;
    private SearchView searchView;
    private RecyclerView rvsearch;
    private SearchAdapter adapter;

    private static final String JSON_URL = "https://animeyou.net/api/search.php";



    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        rvsearch = view.findViewById(R.id.rv_search_list_anime);
        rvsearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvsearch.setHasFixedSize(true);
        imageModelArrayList = new ArrayList<>();

        loadSearch();

        return view;
    }

    private void loadSearch() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, response -> {
            try {
                JSONObject object = new JSONObject(response);
                JSONArray animeArray = object.getJSONArray("data");

                for (int i = 0; i < animeArray.length(); i++) {
                    JSONObject animeObject = animeArray.getJSONObject(i);

                    AnimeSearchItem animeSearchItem = new AnimeSearchItem(
                            animeObject.getString("judul"),
                            animeObject.getString("gambar"),
                            animeObject.getString("tanggal"),
                            animeObject.getString("genre")

                    );
                    imageModelArrayList.add(animeSearchItem);
                }
                adapter = new SearchAdapter(imageModelArrayList, getActivity());
                rvsearch.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            loadSearch();
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }


}
