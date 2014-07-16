package com.example.Bromagatchi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
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
    private ImageView broImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumpsquats);
        broImage = (ImageView) findViewById(R.id.BroImage);
        final Button jumpButton = (Button) findViewById(R.id.jumpButton);
        jumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationu = new TranslateAnimation(0, 0, 0, -400);
                animationu.setDuration(1000);
                animationu.setFillAfter(true);
                broImage.startAnimation(animationu);



                animationu.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        jumpButton.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation animationd = new TranslateAnimation(0, 0, -400, 0);
                        animationd.setDuration(1000);
                        animationd.setFillAfter(true);
                        broImage.startAnimation(animationd);
                        jumpButton.setEnabled(true);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                xp++;
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

            public void setImageBRO(int image) {
                broImage.setImageResource(image); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            }
        });
    }
}


