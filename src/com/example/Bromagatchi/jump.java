package com.example.Bromagatchi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by sdc on 7/16/14.
 */
public class jump extends Activity {

    public int hp = 100;
    public int xp = 0;
    public int energy = 50;
    public double hap = 1;

    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumpsquats);
    }

    public void update() {

        if (xp < 5) {
            setImageBRO(R.drawable.phase0); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
        }
        if (xp > 4) {
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


    private ImageView broImage;
    public void setImageBRO(int image) {
        broImage.setImageResource(image); //Ændre til BRO, så vi har en metode til at ændre baggrunden
    }



    @Override
    protected void onResume() {
        super.onResume();

        // Indlæs tiden appen blev paused
        //long lastTime = prefs.getLong("lastTime", System.currentTimeMillis()); // Default værdi er System.currentTime...
        //long diffrence = System.currentTimeMillis() - lastTime; // Difference i ms
        //System.out.println(diffrence);
        /*if (diffrence / 1000 > 60) //HVERT MINUT
        {
            hp += diffrence / 10000;
            energy += diffrence / 10000; //10000 er blot en faktor
        }
        xp = prefs.getInt("XP on pause", xp);
        if (energy >= 50) {
            energy = 50;
        }
        if (hp >= 100) {
            hp = 100;
        }
        update();*/
    }


}