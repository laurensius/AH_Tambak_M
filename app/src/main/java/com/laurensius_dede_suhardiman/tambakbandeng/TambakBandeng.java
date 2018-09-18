package com.laurensius_dede_suhardiman.tambakbandeng;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.laurensius_dede_suhardiman.tambakbandeng.Fragments.FragmentMonitoring;
import com.laurensius_dede_suhardiman.tambakbandeng.Fragments.FragmentNotifikasi;
import com.laurensius_dede_suhardiman.tambakbandeng.Fragments.FragmentTentang;

public class TambakBandeng extends AppCompatActivity {

    private TextView mTextMessage;
    private FrameLayout flMaster;

    private BottomNavigationView navigation;
    private Dialog dialBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambak_bandeng);

        dialBox = createDialogBox();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_monitoring:
                        selectedFragment = FragmentMonitoring.newInstance();
                        break;
                    case R.id.navigation_notifikasi:
                        selectedFragment = FragmentNotifikasi.newInstance();
                        break;
                    case R.id.navigation_tentang:
                        selectedFragment = FragmentTentang.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_master, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_master, FragmentMonitoring.newInstance());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        dialBox.show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Dialog createDialogBox(){
        dialBox = new AlertDialog.Builder(this)
                .setTitle("Konfirmasi Keluar")
                .setMessage("Apakah Anda yakin akan keluar dari aplikasi ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialBox.dismiss();
                    }
                })
                .create();
        return dialBox;
    }
}
