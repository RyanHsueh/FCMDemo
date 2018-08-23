package com.example.ryanhsueh.fcmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NotificationCenter.OnFCMReceivedListener {

    private TextView mTextFCM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextFCM = findViewById(R.id.text_fcm);

        NotificationCenter.getInstance().registerFCMReceivedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationCenter.getInstance().unregisterFCMReceivedListener(this);
    }

    @Override
    public void onMessageReceived(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextFCM.setText(message);
            }
        });
    }
}
