package com.balar.animeyounet.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.balar.animeyounet.R;
import com.balar.animeyounet.entity.Anime;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAnime extends AppCompatActivity {


    @BindView(R.id.dtJudul)
    TextView judul;
    @BindView(R.id.dtGambar)
    ImageView gambar;
    @BindView(R.id.dtTanggal)
    TextView tanggal;
    @BindView(R.id.fb)
    TextView fb;

    @BindView(R.id.ig)
    TextView ig;

    /*@BindView(R.id.dtGenre)
    TextView genre;*/

    @BindView(R.id.video)
    WebView Video;


    public Anime Detail;
    private  CountDownTimer countDownTimer;

    private PublisherAdView adView;
//    private PublisherInterstitialAd mPublisherInterstitialAd;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anime);

//        countDownTimer = new CountDownTimer(30000, 1000) {
//            @Override
//            public void onTick(long l) {
//                Toast.makeText(DetailAnime.this, "seconds remaining: " + l / 1000, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFinish() {
//                Toast.makeText(DetailAnime.this, "Selesai: ", Toast.LENGTH_LONG).show();
//
//            }
//        }.start();

//        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
//        mPublisherInterstitialAd.setAdUnitId("/6499/example/interstitial");
//        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
//
//        if (mPublisherInterstitialAd.isLoaded()) {
//            mPublisherInterstitialAd.show();
//        } else {
//            Toast.makeText(this, "Gagal Tayang", Toast.LENGTH_SHORT).show();
//        }

        ButterKnife.bind(this);

        Detail = getIntent().getParcelableExtra("detail");
        judul.setText(Detail.getJudul());
        tanggal.setText(Detail.getTanggal());
        Glide.with(this)
                .load(Detail.getGambar())
                .into(gambar);
        Video.setWebChromeClient(new MyChrome());
        Video.setWebViewClient(new Browser_home());
        Video.loadUrl("file:///android_asset/video.html");

        WebSettings video = Video.getSettings();
        video.setJavaScriptEnabled(true);
        Video.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                String Google = Detail.getVideo();//Masukan string video 1 disini
//                String GDrive = Detail.getVideo1();//Masukan string video 2 disini
//                String YouDrive = Detail.getVideo2();//Masukan string video 3 disini
//              panggil fungsi loadUrl lalu buat javascript untuk menganti atribute dari iframe dengan id iframe
//              berlaku juga untuk sytax html lain
                Video.loadUrl("javascript:(function(){" +
                        "document.getElementById('Google').src ='"+ Google+"'})()");
//                Video.loadUrl("javascript:(function(){" +
//                        "document.getElementById('GDrive').src ='"+ GDrive+"'})()");
//                Video.loadUrl("javascript:(function(){" +
//                        "document.getElementById('YouDrive').src ='"+ YouDrive+"'})()");
            }
        });

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

        adView = findViewById(R.id.banner);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        adView.loadAd(adRequest);

    }


    class Browser_home extends WebViewClient {

        Browser_home() {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            setTitle(view.getTitle());
            super.onPageFinished(view, url);

        }
    }

    private class MyChrome extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }

            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
        }
    }


}
