package com.example.fitnesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkoutAct extends AppCompatActivity {
    // variable initialization
     public TextView titlepage, subtitlepage, intropage, subintropage, fitonetitle, fitonedesc, fittwotitle, fittwodesc, fitthreetitle,
    fitthreedesc, fitfourtitle, fitfourdesc, fitfivetitle,fitfivedesc;
    public View divpage;
    public Animation bttone, bttwo, bttfour, bttfive, bttsix, bttseven, btteight;
    public LinearLayout fitone, fittwo, fitthree, fitfour, fitfive;
    // on create activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //Load Animation
        bttone = AnimationUtils.loadAnimation(this,R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this,R.anim.bttwo);
        bttfour = AnimationUtils.loadAnimation(this,R.anim.bttfour);
        bttfive = AnimationUtils.loadAnimation(this,R.anim.bttfive);
        bttsix = AnimationUtils.loadAnimation(this,R.anim.bttsix);
        bttseven = AnimationUtils.loadAnimation(this,R.anim.bttseven);
        btteight = AnimationUtils.loadAnimation(this,R.anim.btteight);

        // assigning xml values to variables
        titlepage = (TextView) findViewById(R.id.titlepage);
        subtitlepage = (TextView) findViewById(R.id.subtitlepage);
        intropage = (TextView) findViewById(R.id.intropage);
        subintropage = (TextView) findViewById(R.id.subintropage);
        fitonetitle = (TextView) findViewById(R.id.fitonetitle);
        fitonedesc = (TextView) findViewById(R.id.fitonedesc);
        fittwotitle = (TextView) findViewById(R.id.fittwotitle);
        fittwodesc = (TextView) findViewById(R.id.fittwodesc);
        fitthreetitle = (TextView) findViewById(R.id.fitthreetitle);
        fitthreedesc = (TextView) findViewById(R.id.fitthreedesc);
        fitfourtitle = (TextView) findViewById(R.id.fitfourtitle);
        fitfourdesc = (TextView) findViewById(R.id.fitfourdesc);
        fitfivetitle = (TextView) findViewById(R.id.fitfivetitle);
        fitfivedesc = (TextView) findViewById(R.id.fitfivedesc);
        fitone = (LinearLayout) findViewById(R.id.fitone);
        fittwo = (LinearLayout) findViewById(R.id.fittwo);
        fitthree = (LinearLayout) findViewById(R.id.fitthree);
        fitfour = (LinearLayout) findViewById(R.id.fitfour);
        fitfive = (LinearLayout) findViewById(R.id.fitfive);
        divpage = (View) findViewById(R.id.divpage);

        // takes user to chest workout
        fitonetitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent a = new Intent(WorkoutAct.this,Chest.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
        // takes user to bicep workout
        fittwotitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent a = new Intent(WorkoutAct.this,Bicep.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
        // takes user to back workout
        fitthreetitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent a = new Intent(WorkoutAct.this,Back.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
        // takes user to shoulder workout
        fitfourtitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent a = new Intent(WorkoutAct.this,Shoulder.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
        // takes user to leg workout
       fitfivetitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent a = new Intent(WorkoutAct.this,Leg.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

        //assign the animation
        titlepage.startAnimation(bttone);
        subtitlepage.startAnimation(bttone);
        divpage.startAnimation(bttone);
        intropage.startAnimation(bttwo);
        subintropage.startAnimation(bttwo);
        fitone.startAnimation(bttfour);
        fittwo.startAnimation(bttfour);
        fitthree.startAnimation(bttfive);
        fitfour.startAnimation(bttsix);
        fitfive.startAnimation(bttsix);
    }
    // back pressed activity
    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(WorkoutAct.this,MainActivity.class);
        startActivity(a);
        finish();
    }

}