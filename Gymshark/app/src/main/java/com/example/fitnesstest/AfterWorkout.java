package com.example.fitnesstest;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Vibrator;

public class AfterWorkout extends AppCompatActivity {
    public Button alarmbutton;
    int t1Hour, t1Minute;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_workout);

        alarmbutton = findViewById(R.id.alarmbutton);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        alarmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AfterWorkout.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;
                                String time  =  t1Hour + ":" + t1Minute;
                                String Hour = String.valueOf(t1Hour);
                                String Minute = String.valueOf(t1Minute);
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date  = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    Toast.makeText(AfterWorkout.this,"Alarm Set",Toast.LENGTH_LONG).show();
//                                    Toast.makeText(AfterWorkout.this,Minute,Toast.LENGTH_LONG).show();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR,t1Hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,t1Minute);
                startActivity(intent);
            }
        });
        vibrator.vibrate(1000);
    }
}
