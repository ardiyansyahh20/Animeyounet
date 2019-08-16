package com.balar.animeyounet.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balar.animeyounet.activity.DetailSeries;
import com.balar.animeyounet.entity.AnimeItem;
import com.balar.animeyounet.entity.SeriesItem;
import com.balar.animeyounet.listener.CustomOnItemClickListener;
import com.balar.animeyounet.R;
import com.balar.animeyounet.activity.DetailAnime;
import com.balar.animeyounet.entity.Anime;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {
//    private ArrayList<SeriesItem> animeItemList;
    private ArrayList<AnimeItem> animeItemList;
    private Context context;


    public AnimeAdapter(ArrayList<AnimeItem> animeItems, Context context){
        this.animeItemList = animeItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_home_item, viewGroup, false);

        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final AnimeItem anime = animeItemList.get(i);
        viewHolder.tvJudul_series.setText(anime.getJudul_series());
        Glide.with(context)
                .load(anime.getGambar_series())
                .into(viewHolder.Gambar_series);
        viewHolder.tvJudul.setText(anime.getJudul());
        Glide.with(context)
                .load(anime.getGambar())
                .into(viewHolder.imgAnime);
        viewHolder.tvTanggal.setText(anime.getTanggal());

        viewHolder.tvJudul_series.setOnClickListener(new CustomOnItemClickListener(i, (view, position) -> {
            Intent intent = new Intent(context, DetailSeries.class);
            intent.putExtra("detail_series", anime.getId());
            context.startActivity(intent);
        }));

        viewHolder.tvJudul.setOnClickListener(new CustomOnItemClickListener(i, (view, position) -> {
            Intent intent = new Intent(context, DetailAnime.class);
            Anime anime1 = new Anime(anime.getId(),
                    anime.getJudul(),
                    anime.getGambar(),
                    anime.getTanggal(),
                    anime.getVideo(),
                    anime.getVideo1(),
                    anime.getVideo2(),
                    anime.getJudul_series(),
                    anime.getGambar_series(),
                    anime.getUrl()
            );
            intent.putExtra("detail", anime1);
            context.startActivity(intent);
        }));

        viewHolder.imgAnime.setOnClickListener(new CustomOnItemClickListener(i, (view, position) -> {
            Intent intent = new Intent(context, DetailAnime.class);
            Anime anime12 = new Anime(anime.getId(),
                    anime.getJudul(),
                    anime.getGambar(),
                    anime.getTanggal(),
                    anime.getVideo(),
                    anime.getVideo1(),
                    anime.getVideo2(),
                    anime.getJudul_series(),
                    anime.getGambar_series(),
                    anime.getUrl()
            );
            intent.putExtra("detail", anime12);
            context.startActivity(intent);
        }));

        viewHolder.btn_share.setOnClickListener(new CustomOnItemClickListener(i, (view, position) -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, anime.getUrl());
            intent.setType("text/plain");
            context.startActivity(intent);
        }));

    }

    @Override
    public int getItemCount() {
        return animeItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvJudul, tvTanggal, tvJudul_series;
        private ImageView imgAnime, Gambar_series;
        private ImageButton btn_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            imgAnime = itemView.findViewById(R.id.Gambar);
            tvJudul_series = itemView.findViewById(R.id.tvJudul_series);
            Gambar_series = itemView.findViewById(R.id.Gambar_series);
            btn_share = itemView.findViewById(R.id.btn_share);
        }
    }
}


