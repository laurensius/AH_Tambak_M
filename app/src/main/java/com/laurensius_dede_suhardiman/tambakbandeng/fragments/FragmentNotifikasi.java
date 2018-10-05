package com.laurensius_dede_suhardiman.tambakbandeng.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laurensius_dede_suhardiman.tambakbandeng.R;
import com.laurensius_dede_suhardiman.tambakbandeng.TambakBandeng;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentNotifikasi extends Fragment {

    private TextView tvNotifikasi;
    private ImageView ivNotifikasi;
    Timer timer = new Timer();


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
        View inflaterMonitoring = inflater.inflate(R.layout.fragment_notifikasi, container, false);
        ivNotifikasi = (ImageView) inflaterMonitoring.findViewById(R.id.iv_notif_logo);
        tvNotifikasi = (TextView)inflaterMonitoring.findViewById(R.id.tv_notif_message);
//
        return inflaterMonitoring;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        final Handler handler = new Handler();
        TimerTask doDataToUI = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            tvNotifikasi.setText(TambakBandeng.str_notifikasi);
                            if(TambakBandeng.str_notifikasi.equals("Semua sensor dalam keadaan normal")){
                                ivNotifikasi.setImageResource(R.mipmap.normal);
                                tvNotifikasi.setBackgroundColor(Color.parseColor("#00CC00"));
                            }else{ivNotifikasi.setImageResource(R.mipmap.warning);
                                tvNotifikasi.setBackgroundColor(Color.parseColor("#CC0000"));
                            }
                        } catch (Exception e) {
                        }
                    }
                });
            }
        };
        timer.schedule(doDataToUI, 0, 1000);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        timer.cancel();
    }
}
