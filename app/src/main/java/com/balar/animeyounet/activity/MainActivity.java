package com.balar.animeyounet.activity;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;

import com.balar.animeyounet.entity.AnimeItem;
import com.balar.animeyounet.R;
import com.balar.animeyounet.fragment.CollectionFragment;
import com.balar.animeyounet.fragment.HomeFragment;
import com.balar.animeyounet.fragment.SearchFragment;
import com.balar.animeyounet.utils.ViewPagerAdapter;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.nav_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private PublisherAdView mPublisherAdView;

    HomeFragment homeFragment;
    SearchFragment searchFragment;
    CollectionFragment collectionFragment;
    MenuItem menuItem;
    AnimeItem animeItem;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ButterKnife.bind(this);

        animeItem = getIntent().getParcelableExtra("detail");

        bottomNavigationView.setOnNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()){
                        case R.id.navigation_home:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.navigation_search:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.navigation_collection:
                            viewPager.setCurrentItem(2);
                            break;
                    }
                    return false;
                }
        );

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null){
                    menuItem.setChecked(false);
                }else{
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        mematikan fungsi swipe viewpager
//        viewPager.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                return true;
//            }
//        });

        mPublisherAdView = findViewById(R.id.publisherAdView);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        mPublisherAdView.loadAd(adRequest);

        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        homeFragment = new HomeFragment();
        adapter.addFragment(homeFragment);
        viewPager.setAdapter(adapter);

        searchFragment = new SearchFragment();
        adapter.addFragment(searchFragment);
        viewPager.setAdapter(adapter);

        collectionFragment = new CollectionFragment();
        adapter.addFragment(collectionFragment);
        viewPager.setAdapter(adapter);


    }


}




