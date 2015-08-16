package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyActivity extends Activity {

    private TextView displayTimer;
    private int seconds = 0;
    private Timer timer;
    private Button start_button;
    private Button back_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        displayTimer = (TextView) findViewById(R.id.timer);
        start_button = (Button) findViewById(R.id.start_button);
        back_button = (Button) findViewById(R.id.back_button);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pause_button:
                timer.cancel();
                start_button.setEnabled(true);
                back_button.setEnabled(true);
                break;
            case R.id.start_button:
                if (timer != null) {
                    timer.cancel();
                }
                start_button.setEnabled(false);
                back_button.setEnabled(true);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timerMethod();
                    }

                }, 0, 1000);
                break;
            case R.id.stop_button:
                start_button.setEnabled(true);
                back_button.setEnabled(true);
                seconds = 0;
                timer.cancel();
                displayTimer.setText("00:00");
                break;
            case R.id.back_button:
                back_button.setEnabled(false);
                start_button.setEnabled(true);
                if (timer != null) {
                    timer.cancel();
                }
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        backTimer();
                    }
                }, 0, 1000);
                break;
        }
    }

    private void backTimer() {
        this.runOnUiThread(backTimer);
    }

    private Runnable backTimer = new Runnable() {
        @Override
        public void run() {
            seconds--;
//            String temp = (new SimpleDateFormat("mm:ss")).format(new Date(seconds * 1000));
            String temp = (new SimpleDateFormat("mm:ss")).format(new Date(seconds * 1000));
            displayTimer.setText(temp);
        }
    };


    private void timerMethod() {
        this.runOnUiThread(timerTick);
    }

    private Runnable timerTick = new Runnable() {
        @Override
        public void run() {
            seconds++;
            String temp = (new SimpleDateFormat("mm:ss")).format(new Date(seconds * 1000));
            displayTimer.setText(temp);
        }
    };


}
