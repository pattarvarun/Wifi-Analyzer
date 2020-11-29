package com.example.wifianalyzer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.se.omapi.Channel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;

public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager manager;
    private WifiP2pManager.Channel channel;
    private DeviceList activity;
    private List _peers = new ArrayList();

    WifiP2pManager.PeerListListener _peerListListener;


    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
            DeviceList activity, WifiP2pManager.PeerListListener peerListListener) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
        this._peerListListener = peerListListener;

        checkDiscoverPeersPermission();

    }

    private void checkDiscoverPeersPermission() {
        discoverPeers();
        /*int permissionCheck = activity.getActivity().checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION);
        if (!(permissionCheck == PackageManager.PERMISSION_GRANTED)) {
            // User may have declined earlier, ask Android if we should show him a reason
            if (activity.getActivity().shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // show an explanation to the user
                // Good practise: don't block thread after the user sees the explanation, try again to request the permission.
            } else {
                // request the permission.
                // CALLBACK_NUMBER is a integer constants
                activity.getActivity().requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                // The callback method gets the result of the request.
            }
        } else {
            discoverPeers();
        }*/
    }

    private void checkRequestPeersPermission() {
        requestPeers();
        /*int permissionCheck = activity.getActivity().checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION);
        if (!(permissionCheck == PackageManager.PERMISSION_GRANTED)) {
            // User may have declined earlier, ask Android if we should show him a reason
            if (activity.getActivity().shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // show an explanation to the user
                // Good practise: don't block thread after the user sees the explanation, try again to request the permission.
            } else {
                // request the permission.
                // CALLBACK_NUMBER is a integer constants
                activity.getActivity().requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 3);
                // The callback method gets the result of the request.
            }
        } else {
            requestPeers();
        }*/
    }

    @SuppressLint("MissingPermission")
    public void discoverPeers() {
        manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.d("wifi", "onsuccess");
            }

            @Override
            public void onFailure(int reasonCode) {
                Log.d("wifi", "onfailure");
            }
        });
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.d("wifi", "receive: " + intent.getAction());

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Check to see if Wi-Fi is enabled and notify appropriate activity
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Log.d("wifi", "WIFI DIRECT ON");

            } else {
                Log.d("wifi", "WIFI DIRECT OFF");
            }

        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            Log.d("wifi", "Peers changed");
            if (manager != null) {
                checkRequestPeersPermission();

            }

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
        }

    }

    @SuppressLint("MissingPermission")
    public void requestPeers() {
        manager.requestPeers(channel, _peerListListener);
    }



}