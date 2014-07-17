package com.example.Bromagatchi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by sdc on 7/16/14.
 */
public class rest extends Activity {
    public int hp = 100;
    public int xp = 0;
    public int energy = 50;
    public float hap = 1;

    private SharedPreferences prefs;
    private ImageView broImage;
    private ImageView imageView;


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
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final ImageView broImage = (ImageView) findViewById(R.id.BroImage);

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Gem nuværende tid
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("lastTime", System.currentTimeMillis());
        // Gem nuværende XP:
        editor.putInt("XP", xp);
        editor.commit();
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
    public void setImageBACKGROUND(int image) {
        imageView.setImageResource(image); //Metoden til at ændre baggrunden
    }
}