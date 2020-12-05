package com.example.fitnesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Leg extends AppCompatActivity {
    // variable initialization
    public TextView intropage, subintropage, fitonetitle, fitonedesc, timerValue, nextexercise,startexercise;
    public View divpage, bgprogress;
    public LinearLayout fitone;
    public ImageView imgTimer;
    private Handler mHandler = new Handler();
    private static final long START_TIME_IN_MILLIS = 3600100;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    Animation btthree, bttfour, ttbone, ttbtwo, alphago;

    // on create activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leg);

        //Load Animations
        btthree = AnimationUtils.loadAnimation(this, R.anim.btthree);
        bttfour = AnimationUtils.loadAnimation(this, R.anim.bttfour);
        ttbone = AnimationUtils.loadAnimation(this, R.anim.ttbone);
        ttbtwo = AnimationUtils.loadAnimation(this, R.anim.ttbtwo);
        alphago = AnimationUtils.loadAnimation(this, R.anim.alphago);

        // assigning xml values to variables
        intropage = (TextView) findViewById(R.id.intropage);
        subintropage = (TextView) findViewById(R.id.subintropage);
        fitonetitle = (TextView) findViewById(R.id.fitonetitle);
        fitonedesc = (TextView) findViewById(R.id.fitonedesc);
        timerValue = (TextView) findViewById(R.id.timerValue);
        nextexercise = (TextView) findViewById(R.id.nextexercise);
        startexercise = (TextView) findViewById(R.id.startexercise);
        divpage = (View) findViewById(R.id.divpage);
        bgprogress = (View) findViewById(R.id.bgprogress);
        fitone = (LinearLayout) findViewById(R.id.fitone);
        imgTimer = (ImageView) findViewById(R.id.imgtimer);

        //assign animation
        nextexercise.startAnimation(bttfour);
        startexercise.startAnimation(bttfour);
        bgprogress.startAnimation(btthree);
        fitone.startAnimation(ttbone);
        intropage.startAnimation(ttbtwo);
        subintropage.startAnimation(ttbtwo);
        divpage.startAnimation(ttbtwo);
        timerValue.startAnimation(alphago);
        imgTimer.startAnimation(alphago);

        // event listener
        nextexercise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Leg.this,MainActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
        // another event listener
        startexercise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mTimerRunning){
                    pauseTime();
                } else
                    startTimer();
            }
        });
    }
    // start timer method
    private void startTimer(){
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                startexercise.setText("START");
                Toast.makeText(getApplicationContext(),"Done!", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(delayRun, 1500);
            }
        }.start();
        mTimerRunning = true;
        startexercise.setText("PAUSE");
    }
    // handler method to delay the transition
    private Runnable delayRun  = new Runnable() {
        @Override
        public void run() {
            Intent a = new Intent(Leg.this,WorkoutAct.class);
            startActivity(a);
        }
    };
    // update the timer
    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds) ;
        timerValue.setText(timeLeft);
    }
    // pause time
    private void pauseTime(){
        countDownTimer.cancel();
        mTimerRunning = false;
        startexercise.setText("START");
    }
    // back pressed activity
    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(Leg.this,WorkoutAct.class);
        startActivity(a);
        finish();
    }

}
