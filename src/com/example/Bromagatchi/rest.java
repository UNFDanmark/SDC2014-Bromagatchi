package com.example.Bromagatchi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;

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


    @Override
    protected void onResume() {
        super.onResume();

        // Indlæs tiden appen blev paused
        long lastTime = prefs.getLong("lastTime", System.currentTimeMillis()); // Default værdi er System.currentTime...
        long diffrence = System.currentTimeMillis() - lastTime; // Difference i ms
        System.out.println(diffrence);
        if (diffrence / 10000 > 60) //HVERT MINUT
        {
            hp += diffrence / 10;
            energy += diffrence / 10; //10000 er blot en faktor
        }
        xp = prefs.getInt("XP", xp);
        energy = prefs.getInt("Energy", energy);
        if (energy > 50) {
            energy = 50;
        }
        if (hp > 100) {
            hp = 100;
        }
        update();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restlayout);
        broImage = (ImageView) findViewById(R.id.BroImage);
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
}