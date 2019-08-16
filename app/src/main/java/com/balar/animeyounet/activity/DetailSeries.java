package com.balar.animeyounet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.balar.animeyounet.R;
import com.balar.animeyounet.entity.Anime;
import com.balar.animeyounet.entity.Series;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSeries extends AppCompatActivity {
    @BindView(R.id.dt_gambar_series)
    ImageView detail_gambar;
    @BindView(R.id.dt_judul_series)
    TextView detail_judul;
    @BindView(R.id.dt_tanggal_series)
    TextView detail_tanggal_series;
    @BindView(R.id.dt_title)
    TextView detail_title;
    @BindView(R.id.dt_judul_lain)
    TextView detail_judul_lain;
    @BindView(R.id.dt_japanese)
    TextView detail_japanese;
    @BindView(R.id.dt_type)
    TextView detail_type;
    @BindView(R.id.dt_status)
    TextView detail_status;
    @BindView(R.id.dt_episode)
    TextView detail_episode;
    @BindView(R.id.dt_aired)
    TextView detail_aired;
    @BindView(R.id.dt_studios)
    TextView detail_studios;
    @BindView(R.id.dt_genre)
    TextView detail_genre;
    @BindView(R.id.dt_durasi)
    TextView detail_durasi;
    @BindView(R.id.dt_score)
    TextView detail_score;
    @BindView(R.id.dt_sinopsis)
    TextView detail_sinopsis;

    @BindView(R.id.dt_fb)
    TextView fb;

    @BindView(R.id.dt_ig)
    TextView ig;

    public String series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_series);

        ButterKnife.bind(this);

        series = getIntent().getStringExtra("detail_series");
        detail_judul.setText(series);
//        Glide.with(this)
//                .load(series.getGambar_series())
//                .into(detail_gambar);
//        detail_judul.setText(series.getJudul_series());
//        detail_tanggal_series.setText(series.getGambar_series());
//        detail_title.setText(series.getTitle_series());
//        detail_judul_lain.setText(series.getJudul_lain_series());
//        detail_japanese.setText(series.getJapanese_series());
//        detail_type.setText(series.getType_series());
//        detail_status.setText(series.getStatus_series());
//        detail_episode.setText(series.getEpisode_series());
//        detail_aired.setText(series.getAired_series());
//        detail_studios.setText(series.getStudios_series());
//        detail_genre.setText(series.getGenre_series());
//        detail_durasi.setText(series.getDurasi_series());
//        detail_score.setText(series.getScore_series());
//        detail_sinopsis.setText(series.getSinopsis_series());

        fb.setOnClickListener(v -> {
            Uri url = Uri.parse("https://www.facebook.com/animeyou.net/");
            Intent intent = new Intent(Intent.ACTION_VIEW, url);
            startActivity(intent);
        });
        ig.setOnClickListener(v -> {
            Uri url = Uri.parse("https://www.instagram.com/animeyou_net/");
            Intent intent = new Intent(Intent.ACTION_VIEW, url);
            startActivity(intent);
        });
    }
}
