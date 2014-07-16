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
    public int energy = 99999;
    public double hap = 1;
    //public AlarmManager alarmMgr;
    //public PendingIntent alarmIntent;*/
    public Date date;

    private ImageView broImage;
    private ImageView imageView;
    private Button exercisebutton;

    private SharedPreferences prefs;


    public void update() {
        TextView hptext = (TextView) findViewById(R.id.HPstatTEXT);
        hptext.setText("HP: " + hp + "");
        TextView XPstat = (TextView) findViewById(R.id.XPstatTEXT);
        XPstat.setText("GAINZ: " + xp + "");
        TextView NRJstat = (TextView) findViewById(R.id.ENERGYstatTEXT);
        NRJstat.setText("ENERGY: " + energy + "");
        if (xp < 5) {
            setImageBRO(R.drawable.phase0); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 5) {
            setImageBRO(R.drawable.phase1); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 30) {
            setImageBRO(R.drawable.phase2); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 50) {
            setImageBRO(R.drawable.phase3); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 100) {
            setImageBRO(R.drawable.phase4); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }

    }

    public void setImageBRO(int image) {
        broImage.setImageResource(image); //Ændre til BRO, så vi har en metode til at ændre baggrunden
    }

    public void setImageBACKGROUND(int image) {
        imageView.setImageResource(image); //Metoden til at ændre baggrunden
    }






    @Override
    protected void onResume() {
        super.onResume();

        // Indlæs tiden appen blev paused
        long lastTime = prefs.getLong("lastTime", System.currentTimeMillis()); // Default værdi er System.currentTime...
        long diffrence = System.currentTimeMillis() - lastTime; // Difference i ms
        System.out.println(diffrence);
        if (diffrence/1000 > 2)
        {

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
                    int randno = rnd.nextInt(10); //ÆNDRE FREKVENSEN AF INJURY
                    //Injury
                    if (randno == 4) {
                        if (hap <= 0.5) {
                            hap = 0;
                        }
                        if (hap > 0.5) {
                            hap -= 0.5;
                        }

                        energy -= 10 * hap;
                        Toast.makeText(getApplicationContext(), "Injury", Toast.LENGTH_SHORT).show();
                        System.out.println(hap);
                        System.out.println(energy);
                    }
                    energy -= 10;
                    xp += 1*hap;
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
                    energy+= 5 ;
                    update();
                    if (hap >= 1.5) {
                        hap = 2.0;
                        System.out.println(hap);
                    }
                    if (hap < 1.5) {
                        hap += 0.5;
                        System.out.println(hap);
                    }
                }

            }
        });











    }
}
