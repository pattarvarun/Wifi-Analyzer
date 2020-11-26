package com.example.wifianalyzer;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    RadarView mRadarView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radioview);
        mRadarView = findViewById(R.id.radarView);
        mRadarView.setFrameRate(1000);
        mRadarView.setShowCircles(true);
        mRadarView.setShowPoints(true);
    }

    public void stopAnimation(View view) {
        if (mRadarView != null) mRadarView.stopAnimation();
    }

    public void startAniamtion(View view) {
        if (mRadarView != null) mRadarView.startAnimation();
    }
}
