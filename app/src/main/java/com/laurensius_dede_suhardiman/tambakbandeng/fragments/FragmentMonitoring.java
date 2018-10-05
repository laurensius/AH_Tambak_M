package com.laurensius_dede_suhardiman.tambakbandeng.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laurensius_dede_suhardiman.tambakbandeng.R;
import com.laurensius_dede_suhardiman.tambakbandeng.TambakBandeng;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentMonitoring extends Fragment {

    private TextView tvValSuhu,tvValKekeruhan, tvValKedalaman;
    Timer timer = new Timer();

    public FragmentMonitoring() {}

    public static FragmentMonitoring newInstance() {
        FragmentMonitoring fragment = new FragmentMonitoring();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflaterMonitoring = inflater.inflate(R.layout.fragment_monitoring, container, false);
        tvValSuhu = (TextView)inflaterMonitoring.findViewById(R.id.tv_val_suhu);
        tvValKekeruhan = (TextView)inflaterMonitoring.findViewById(R.id.tv_val_kekeruhan);
        tvValKedalaman = (TextView)inflaterMonitoring.findViewById(R.id.tv_val_kedalaman);
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
                            tvValSuhu.setText(TambakBandeng.val_suhu.concat(" °C"));
                            tvValKekeruhan.setText(TambakBandeng.val_kekeruhan);
                            tvValKedalaman.setText(TambakBandeng.val_kedalaman.concat(" Cm"));
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
