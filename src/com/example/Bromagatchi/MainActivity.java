package com.example.Bromagatchi;

import android.app.Activity;
/*import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;*/
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    public int hp = 100;
    public int xp = 0;
    public int energy = 50;
    /*public double happiness = 100;
    public AlarmManager alarmMgr;
    public PendingIntent alarmIntent;*/
    public Date date;

    private ImageView broImage;

    private SharedPreferences prefs;

    public void update() {
        TextView hptext = (TextView) findViewById(R.id.HPstatTEXT);
        hptext.setText("HP: " + hp + "");
        TextView XPstat = (TextView) findViewById(R.id.XPstatTEXT);
        XPstat.setText("GAINZ: " + xp + "");
        TextView NRJstat = (TextView) findViewById(R.id.ENERGYstatTEXT);
        NRJstat.setText("ENERGY: " + energy + "");
        if (xp > 30) {
            setImage(R.drawable.phase2);
        }
        if (xp > 100) {
            setImage(R.drawable.phase3);
        }

    }

    public void setImage(int image) {
        broImage.setImageResource(image);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Indlæs tiden appen blev paused
        long lastTime = prefs.getLong("lastTime", System.currentTimeMillis()); // Default værdi er System.currentTime...
        long diffrence = System.currentTimeMillis() - lastTime; // Difference i ms
        System.out.println(diffrence);
        if (diffrence/1000 > 2) {
            energy += diffrence/10000; //OVERVEJE, OM DET ER RIMELIGT.
        }
        update();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Gem nuværende tid
        SharedPreferences.Editor editor = prefs.edit();
        //editor.putLong("lastTime", System.currentTimeMillis());
        editor.putLong("lastTime", System.currentTimeMillis());
        editor.commit();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        date = new Date();
        // Find sharedPreferences
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // Find views
        broImage = (ImageView)findViewById(R.id.BroImage);

        Button exercise = (Button) findViewById(R.id.exercisebutton);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (energy >= 10) {
                    Random rnd = new Random();
                    int randno = rnd.nextInt(10);
                    //Injury
                    if (randno == 4) {
                        hp -= 10;
                        Toast.makeText(getApplicationContext(), "Injury", Toast.LENGTH_SHORT).show();
                    }
                    energy -= 10;
                    xp ++;
                    update();
                } else {
                    Toast.makeText(getApplicationContext(), "Not enough energy", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button rest = (Button) findViewById(R.id.restbutton);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (energy == 50) {
                    Toast.makeText(getApplicationContext(), "Cant have more than 50 energy", Toast.LENGTH_SHORT).show();
                } else {
                    energy+= 5;
                    update();
                }

            }
        });











    }
    /*public void alarm() {

        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
    }*/
}
