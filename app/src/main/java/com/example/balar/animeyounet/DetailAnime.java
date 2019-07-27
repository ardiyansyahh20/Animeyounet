package com.example.balar.animeyounet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

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



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anime);

        ButterKnife.bind(this);




        Detail = getIntent().getParcelableExtra("detail");
        judul.setText(Detail.getJudul());
        tanggal.setText(Detail.getTanggal());
        Glide.with(this)
                .load(Detail.getGambar())
                .into(gambar);

        /*View nonVideoLayout = findViewById(R.id.nonVideoLayout);
        ViewGroup videoLayout = findViewById(R.id.videoLayout);
        View loadingView = getLayoutInflater().inflate(R.layout.fullscreen_video, null);
        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, video){
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                // Your code...
            }
        };*/

        /*webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback()
        {
            @Override
            public void toggledFullscreen(boolean fullscreen)
            {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen)
                {

                   *//* Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_STREAM,Detail.getVideo());
                    intent.setDataAndType(Uri.fromFile(), "video/*");
                    context.startActivity(intent);*//*
                    *//*setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);*//*
                    *//*Toast.makeText(DetailAnime.this, "test full", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailAnime.this, Fullscreen.class);
                    intent.putExtra("link",Detail);
                    startActivity(intent);*//*

                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 21)
                    {
                        //noinspection all
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                }
                else
                {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 21)
                    {
                        //noinspection all
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }

            }
        });*/

        Video.setWebChromeClient(new MyChrome());
        // Call private class InsideWebViewClient
        Video.setWebViewClient(new Browser_home());
        Video.loadUrl("file:///android_asset/video.html");

        WebSettings video = Video.getSettings();
        video.setJavaScriptEnabled(true);



//      untuk set webView saat diload pertamakali
        Video.setWebViewClient(new WebViewClient(){

            // method loadPageFinish untuk set semua asset yang ada sebelum selesai di tampilkan
            @Override
            public void onPageFinished(WebView view, String url) {

                String Google = Detail.getVideo();//Masukan string video 1 disini
                /*String GDrive = Detail.getVideo1();//Masukan string video 2 disini
                String YouDrive = Detail.getVideo2();//Masukan string video 3 disini*/
//              panggil fungsi loadUrl lalu buat javascript untuk menganti atribute dari iframe dengan id iframe
//              berlaku juga untuk sytax html lain
                Video.loadUrl("javascript:(function(){" +
                        "document.getElementById('Google').src ='"+ Google+"'})()");
                /*Video.loadUrl("javascript:(function(){" +
                        "document.getElementById('GDrive').src ='"+ GDrive+"'})()");
                Video.loadUrl("javascript:(function(){" +
                        "document.getElementById('YouDrive').src ='"+ YouDrive+"'})()");*/
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("https://www.facebook.com/animeyou.net/");


                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);


            }
        });
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("https://www.instagram.com/animeyou_net/");


                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);


            }
        });

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



    /*private class InsideWebViewClient extends WebViewClient {
        @Override
        // Force links to be opened inside WebView and not in Default Browser
        // Thanks http://stackoverflow.com/a/33681975/1815624
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed()
    {
        // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
        if (!webChromeClient.onBackPressed())
        {
            if (video.canGoBack())
            {
                video.goBack();
            }
            else
            {
                // Standard back button implementation (for example this could close the app)
                super.onBackPressed();

            }
        }
    }*/


}
