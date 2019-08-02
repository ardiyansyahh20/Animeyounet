package com.balar.animeyounet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.balar.animeyounet.R;
import com.balar.animeyounet.activity.MainActivity;
import com.balar.animeyounet.entity.AnimeItem;
import com.balar.animeyounet.entity.AnimeSearchItem;
import com.balar.animeyounet.fragment.HomeFragment;
import com.balar.animeyounet.fragment.SearchFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<AnimeSearchItem> imageModelArrayList;
    private ArrayList<AnimeSearchItem> arraylist;
    private Context context;

    public SearchAdapter(ArrayList<AnimeSearchItem> imageModelArrayList, Context context){
        this.imageModelArrayList = imageModelArrayList;
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(SearchFragment.imageModelArrayList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_search_item, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AnimeSearchItem search = imageModelArrayList.get(position);
        holder.sc_judul_series.setText(search.getJudul());
        Glide.with(context)
                .load(search.getGambar())
                .into(holder.sc_gambar);
        holder.sc_tanggal.setText(search.getTanggal());
        holder.sc_genre.setText(search.getGenre());
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        SearchFragment.imageModelArrayList.clear();
        if (charText.length() == 0) {
            SearchFragment.imageModelArrayList.addAll(arraylist);
        } else {
            for (AnimeSearchItem wp : arraylist) {
                if (wp.getJudul().toLowerCase(Locale.getDefault()).contains(charText)) {
                    SearchFragment.imageModelArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sc_judul_series, sc_tanggal, sc_genre;
        private ImageView sc_gambar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sc_judul_series = itemView.findViewById(R.id.sc_judul_series);
            sc_tanggal = itemView.findViewById(R.id.sc_tanggal);
            sc_gambar = itemView.findViewById(R.id.sc_gambar);
            sc_genre = itemView.findViewById(R.id.sc_genre);
        }
    }
}
