package com.balar.animeyounet.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.balar.animeyounet.R;
import com.balar.animeyounet.activity.MainActivity;
import com.balar.animeyounet.listener.CustomOnItemClickListener;
import com.balar.animeyounet.utils.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment {
    @BindView(R.id.daftar_anime)
    Button daftar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        ButterKnife.bind(this,view);

        daftar.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "Daftar Anime",Toast.LENGTH_SHORT).show();
        });
        return view;
    }



}
