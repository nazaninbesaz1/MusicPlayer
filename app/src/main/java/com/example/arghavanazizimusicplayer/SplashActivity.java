package com.example.arghavanazizimusicplayer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    AlertDialog alertDialog ;
    TextView btn_retry,btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkConnection();
    }

    // برسی وضعیت  متصل بودن یا نبودن اینترنت
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {

            return false;
        }
    }

    public void checkConnection() {
        if (isOnline())
        {
            splashshow();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_alert_splash,null);
            builder.setView(view);
            alertDialog = builder.create();

            alertDialog.setCancelable(false);

            btn_exit = view.findViewById(R.id.btn_exit);
            btn_retry = view.findViewById(R.id.btn_retry);

            btn_retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    checkConnection();
                }
            });

            btn_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    finish();
                }
            });

            alertDialog.show();
        }

    }


    void splashshow() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.

                Intent mInHome = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mInHome);

                SplashActivity.this.finish();


            }
        }, 3000);
    }
}

