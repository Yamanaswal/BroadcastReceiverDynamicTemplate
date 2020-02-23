package com.example.broadcastreceiverdynamictemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
       Broadcast Method By (onReceive) Method
     */
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Test the BroadCast - uncomment it
            Toast.makeText(context, "Broadcast is Working", Toast.LENGTH_SHORT).show();

           /*
           Check The Connectivity with Broadcast
            */
            if (ConnectivityManager.EXTRA_NO_CONNECTIVITY.equals(intent.getAction())) {
                boolean noConnectivity = intent.getBooleanExtra(
                        ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
                );
                if (noConnectivity) {
                    Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    /*
        End of Dynamic BroadcastReceiver
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Dynamic Broadcast by IntentFilter and Register it
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //UnRegister the Broadcast
        unregisterReceiver(broadcastReceiver);
    }
}
