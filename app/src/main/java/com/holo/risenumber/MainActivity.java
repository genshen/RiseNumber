package com.holo.risenumber;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.holo.risenumber.views.RiseNumberTextView;

public class MainActivity extends AppCompatActivity {
    RiseNumberTextView rnt;
    RiseNumberTextView money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton btn = (AppCompatButton)findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

    }

    private void start() {
        // int rise
        rnt = (RiseNumberTextView) findViewById(R.id.rise);
        rnt.setRiseInterval(1, 99)
                .setDuration(3000)
                .runInt(true)
                .start();

        rnt.setOnRiseEndListener(new RiseNumberTextView.RiseListener() {
            @Override
            public void onRiseEndFinish() {
                Snackbar.make(rnt, "end", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        // float rise
        money = (RiseNumberTextView) findViewById(R.id.money);
        money.setRiseInterval(0, 120)
                .setDuration(5000)
                .runInt(false)
                .setDecimal(2)
                .start();

        money.setOnRiseEndListener(new RiseNumberTextView.RiseListener() {
            @Override
            public void onRiseEndFinish() {
                Snackbar.make(rnt, "Money end", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}
