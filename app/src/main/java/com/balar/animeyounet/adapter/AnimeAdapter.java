package com.balar.animeyounet.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.balar.animeyounet.listener.CustomOnItemClickListener;
import com.balar.animeyounet.R;
import com.balar.animeyounet.activity.DetailAnime;
import com.balar.animeyounet.entity.Anime;
import com.bumptech.glide.Glide;


import java.util.ArrayList;








    /*private List<AnimeItem> animeItemList;
    private Context context;


    public AnimeAdapter(List<AnimeItem> animeItemList, Context context) {
        super(context, R.layout.list_item, animeItemList);
        this.animeItemList = animeItemList;
        this.context = context;


    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView tvJudul = listViewItem.findViewById(R.id.tvJudul);
        TextView tvTanggal = listViewItem.findViewById(R.id.tvTanggal);
        ImageView imageView = listViewItem.findViewById(R.id.Gambar);
        Button btnWatch = listViewItem.findViewById(R.id.btn_set_watch);


        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Toast.makeText(context,"Comming Soon Broo...",Toast.LENGTH_SHORT).show();*//*
                Intent intent = new Intent(context, DetailAnime.class);
                context.startActivity(intent);
            }
        });

        AnimeItem animeItem = animeItemList.get(position);
        tvJudul.setText(animeItem.getJudul());
        tvTanggal.setText(animeItem.getTanggal());
        Glide.with(context).load(animeItem.getGambar()).into(imageView);

        return listViewItem;
    }*/



public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {
    private ArrayList<AnimeItem> animeItemList;
    private Context context;

    public AnimeAdapter(Context context){
        this.context = context;
    }

    public ArrayList<AnimeItem> getAnimeItem(){
        return animeItemList;
    }

    public void setAnimeItemList(ArrayList<AnimeItem> animeItemList) {
        this.animeItemList = animeItemList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final AnimeItem anime = animeItemList.get(i);

        Glide.with(context)
                .load(getAnimeItem().get(i).getGambar_series())
                .into(viewHolder.Gambar_series);

        viewHolder.tvJudul_series.setText(getAnimeItem().get(i).getJudul_series());


        viewHolder.tvJudul.setText(getAnimeItem().get(i).getJudul());
        viewHolder.tvJudul.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {

            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailAnime.class);
                Anime anime = new Anime(getAnimeItem().get(i).getJudul(),
                        getAnimeItem().get(i).getGambar(),
                        getAnimeItem().get(i).getTanggal(),
                        getAnimeItem().get(i).getGenre(),
                        getAnimeItem().get(i).getVideo(),
                        getAnimeItem().get(i).getVideo1(),
                        getAnimeItem().get(i).getVideo2(),
                        getAnimeItem().get(i).getJudul_series(),
                        getAnimeItem().get(i).getGambar_series(),
                        getAnimeItem().get(i).getUrl(),
                        getAnimeItem().get(i).getHalaman()
                );
                intent.putExtra("detail", anime);
                context.startActivity(intent);
            }
        }));
        viewHolder.tvTanggal.setText(getAnimeItem().get(i).getTanggal());

        Glide.with(context)
                .load(getAnimeItem().get(i).getGambar())
                .into(viewHolder.imgAnime);
        viewHolder.imgAnime.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailAnime.class);
                Anime anime = new Anime(getAnimeItem().get(i).getJudul(),
                        getAnimeItem().get(i).getGambar(),
                        getAnimeItem().get(i).getTanggal(),
                        getAnimeItem().get(i).getGenre(),
                        getAnimeItem().get(i).getVideo(),
                        getAnimeItem().get(i).getVideo1(),
                        getAnimeItem().get(i).getVideo2(),
                        getAnimeItem().get(i).getJudul_series(),
                        getAnimeItem().get(i).getGambar_series(),
                        getAnimeItem().get(i).getUrl(),
                        getAnimeItem().get(i).getHalaman()
                );
                intent.putExtra("detail", anime);
                context.startActivity(intent);
            }
        }));

        viewHolder.btn_share.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, anime.getUrl());
                intent.setType("text/plain");
                context.startActivity(intent);
            }
        }));

        /*viewHolder.btnWatch.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailAnime.class);
                Anime anime = new Anime(getAnimeItem().get(i).getJudul(),
                        getAnimeItem().get(i).getGambar(),
                        getAnimeItem().get(i).getTanggal(),
                        getAnimeItem().get(i).getGenre()
                        );
                intent.putExtra("detail", anime);
                context.startActivity(intent);
            }
        }));*/
    }

    @Override
    public int getItemCount() {
        return getAnimeItem().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJudul, tvTanggal, tvJudul_series;
        private ImageView imgAnime, Gambar_series;
        private ImageButton btn_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvJudul_series = itemView.findViewById(R.id.tvJudul_series);
            imgAnime = itemView.findViewById(R.id.Gambar);
            Gambar_series = itemView.findViewById(R.id.Gambar_series);
            btn_share = itemView.findViewById(R.id.btn_share);
        }
    }
}


