package com.example.wifianalyzer;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wifianalyzer.ui.frag2.Frag2Fragment;
import com.example.wifianalyzer.ui.main.SectionsPagerAdapter;
import com.example.wifianalyzer.ui.speed.SpeedFragment;
import com.google.android.material.tabs.TabLayout;

public class Main extends AppCompatActivity {
    private int last_frag = 0;
    private TabLayout tabs;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_track_changes_black_48dp,
            R.drawable.ic_slow_motion_video_black_48dp,
            R.drawable.ic_view_list_black_48dp,
            R.drawable.ic_devices_other_black_48dp
    };

    @Override
    public void onStart() {
        super.onStart();
        int permissionCheck = getApplicationContext().checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.d("MainActivity","PERMISSION not granted");
            /*// User may have declined earlier, ask Android if we should show him a reason
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // show an explanation to the user
                // Good practise: don't block thread after the user sees the explanation, try again to request the permission.
            } else {
                // request the permission.
                // CALLBACK_NUMBER is a integer constants

                // The callback method gets the result of the request.
            }*/
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(com.example.wifianalyzer.Main.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {

                switch (position) {
                    case 0:
                        new Frag1();
                        break;
                    /*case 1:
                        new SpeedFragment();
                        break;*/
                    case 1:
                        new Frag2Fragment();
                        break;
                    /*case 3:
                        new DeviceList();
                        break;*/

                }
            }

            @Override
            public void onPageSelected(int position) {

                // do this instead, assuming your adapter reference
                // is named mAdapter:
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        new com.example.wifianalyzer.Frag1();
                        break;
                    case 1:
                        new SpeedFragment();
                        break;
                    case 2:
                        new Frag2Fragment();
                        break;
                    case 3:
                        new DeviceList();
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabs.setupWithViewPager(viewPager);


        setupTabIcons();
    }

    private void setupTabIcons() {
        tabs.getTabAt(0).setIcon(tabIcons[0]);
        /*tabs.getTabAt(1).setIcon(tabIcons[1]);*/
        tabs.getTabAt(1).setIcon(tabIcons[2]);
        //tabs.getTabAt(3).setIcon(tabIcons[3]);

    }
}