package com.example.Bromagatchi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

import java.util.Random;

/**
 * Created by sdc on 7/16/14.
 */
public class rest extends Activity {
    public int hp = 100;
    public int xp = 0;
    public int energy = 50;
    public float hap = 1;
    public int delay = 1000;

    private SharedPreferences prefs;
    private ImageView broImage;
    private ImageView imageView;
    private TextView energyGained;
    private TextView yourEnergyText;
    private ImageView linearLayout;
    private Handler h;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("lastTime", System.currentTimeMillis());
        xp = prefs.getInt("XP", xp);
        energy = prefs.getInt("Energy", energy);

        if (energy > 50) {
            energy = 50;
        }
        if (hp > 100) {
            hp = 100;
        }
        update();
        editor.commit();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.restlayout);
        broImage = (ImageView) findViewById(R.id.BroImage);
        imageView = (ImageView) findViewById(R.id.imageView);
        energyGained = (TextView) findViewById(R.id.ENERGYgained);
        linearLayout = (ImageView) findViewById(R.id.LinearLayout);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.darude);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        mediaPlayer.start();

        final ImageView broImage = (ImageView) findViewById(R.id.BroImage);
        h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random rnd = new Random();
                energy++;
                if (energy > 50) {
                    energy = 50;
                }
                energyGained.setText("" + energy);
                h.postDelayed(this, delay);
            }
        }, delay);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random rnd = new Random();
                int color = Color.argb(120, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                Log.d("", color + "");
                linearLayout.setBackgroundColor(color);
                h.postDelayed(this, 100);
            }
        }, 100);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Gem nuværende tid
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("lastTime", System.currentTimeMillis());
        // Gem nuværende XP:
        editor.putInt("XP", xp);
        editor.putInt("Energy", energy);
        editor.commit();
        mediaPlayer.pause();
    }

    public void update() {

        if (xp < 5) {
            setImageBro(R.drawable.phase0); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 4) {
            setImageBro(R.drawable.phase1); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 30) {
            setImageBro(R.drawable.phase2); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 50) {
            setImageBro(R.drawable.phase3); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 100) {
            setImageBro(R.drawable.phase4); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }

    }

    public void setImageBro(int image) {
        broImage.setImageResource(image); //Ændre til BRO, så vi har en metode til at ændre baggrunden
    }

}
