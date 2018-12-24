package com.example.balar.animeyounet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAnime extends AppCompatActivity {

    @BindView(R.id.dtJudul)
    TextView judul;
    @BindView(R.id.dtGambar)
    ImageView gambar;
    @BindView(R.id.dtTanggal)
    TextView tanggal;
    /*@BindView(R.id.dtGenre)
    TextView genre;*/

    @BindView(R.id.video)
    WebView video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anime);

        ButterKnife.bind(this);



        Anime Detail = getIntent().getParcelableExtra("detail");
        judul.setText(Detail.getJudul());
        tanggal.setText(Detail.getTanggal());
        Glide.with(this)
                .load(Detail.getGambar())
                .into(gambar);

        video.getSettings().setJavaScriptEnabled(true);
        video.loadUrl("file:///android_asset/video.html");

    }
}
