package com.laurensius_dede_suhardiman.tambakbandeng.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laurensius_dede_suhardiman.tambakbandeng.R;

public class FragmentNotifikasi extends Fragment {

    public FragmentNotifikasi() {}

    public static FragmentNotifikasi newInstance() {
        FragmentNotifikasi fragment = new FragmentNotifikasi();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifikasi, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}