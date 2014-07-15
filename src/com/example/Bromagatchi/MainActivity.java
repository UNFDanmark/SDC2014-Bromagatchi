package com.example.Bromagatchi;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    public int hp = 100;
    public int xp = 0;
    public int energy = 50;
    public double happiness = 100;
    public AlarmManager alarmMgr;
    public PendingIntent alarmIntent;


    public void update() {
        TextView hptext = (TextView) findViewById(R.id.HPstatTEXT);
        hptext.setText("HP: " + hp + "");
        TextView XPstat = (TextView) findViewById(R.id.XPstatTEXT);
        XPstat.setText("GAINZ: " + xp + "");
        TextView NRJstat = (TextView) findViewById(R.id.ENERGYstatTEXT);
        NRJstat.setText("ENERGY: " + energy + "");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button exercise = (Button) findViewById(R.id.exercisebutton);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (energy >= 10) {
                    energy -= 10;
                    xp ++;
                    update();
                } else {
                    System.out.println("i cant let you do that dave");
                }

            }
        });

        Button rest = (Button) findViewById(R.id.restbutton);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                energy+= 5;
                update();
            }
        });










    }
    /*public void alarm() {

        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
    }*/
}
