  package com.example.fitnesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public TextView titlepage, subtitlepage, btnexercise, quoteText, logout;
    public ImageView imgpage;
    public Animation animimgpage, bttone, bttwo, btthree, lefttoright;
    public View bgprogress;
    public Button nextButton, prevButton;
    public Stack<String> previousQuotes;
    public int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animimgpage = AnimationUtils.loadAnimation(this, R.anim.animimgpage);
        bttone = AnimationUtils.loadAnimation(this, R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this, R.anim.bttwo);
        btthree = AnimationUtils.loadAnimation(this, R.anim.btthree);
        lefttoright = AnimationUtils.loadAnimation(this, R.anim.lefttoright);


        titlepage = (TextView) findViewById(R.id.titlepage);
        subtitlepage = (TextView) findViewById(R.id.subtitlepage);
        btnexercise = (TextView) findViewById(R.id.btnexercise);
        logout = (TextView) findViewById(R.id.logout);
        imgpage = (ImageView) findViewById(R.id.imgpage);
        quoteText = (TextView) findViewById(R.id.titlepage);
        nextButton = (Button) findViewById(R.id.nextbutton);
        prevButton = (Button)findViewById(R.id.backbutton);


        bgprogress = (View) findViewById(R.id.bgprogress);

        imgpage.startAnimation(animimgpage);
        titlepage.startAnimation(bttone);
        subtitlepage.startAnimation(bttone);

        btnexercise.startAnimation(btthree);
        logout.startAnimation(btthree);
        bgprogress.startAnimation(bttwo);

        //give an event to another page
        btnexercise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,WorkoutAct.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
        Resources res = getResources();
        final String[] allQuotes = res.getStringArray(R.array.gymquotes);
        final String[] allAuthors = res.getStringArray(R.array.authors);

        previousQuotes = new Stack<>();

        index = getRandomQuote(allQuotes.length-1);
        quoteText.setText(allQuotes[index]+" ~~ "+ allAuthors[index]);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = getRandomQuote(allQuotes.length-1);
                quoteText.setText(allQuotes[index]+" ~~ "+ allAuthors[index]);
                previousQuotes.push(allQuotes[index]+" ~~ "+ allAuthors[index]);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previousQuotes.size()>0){
                    quoteText.setText(previousQuotes.pop());
                }
                else{
                    Toast.makeText(MainActivity.this,"Get new Quote!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("EventsFragment", "User has signed out");
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }

    public int getRandomQuote(int length){
        return (int) (Math.random() * length) + 1;
    }

    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}