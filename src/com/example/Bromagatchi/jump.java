package com.example.Bromagatchi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by sdc on 7/16/14.
 */
public class jump extends Activity implements SensorEventListener {

    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private final float NOISE = 6.0f; //Changed 11:19 torsdag
    public int hp = 100;
    public int xp = 0;
    public int energy = 50;
    public float hap = 1;
    private boolean hasJumped;
    private SharedPreferences prefs;
    private ImageView broImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumpsquats);
        broImage = (ImageView) findViewById(R.id.BroImage);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        xp = prefs.getInt("XP", 1);
        energy = prefs.getInt("Energy", 1);
        hap = prefs.getFloat("hap", 1);
        Log.d("XP: ", xp + "");
        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        hasJumped = false;

        update();
        final ImageView broImage = (ImageView) findViewById(R.id.BroImage); //ImageView
        final Button jumpButton = (Button) findViewById(R.id.jumpButton);
        jumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (energy > 0) {
                    Animation animationu = new TranslateAnimation(0, 0, 0, -400);
                    animationu.setDuration(500);
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
                            animationd.setDuration(500);
                            animationd.setFillAfter(true);
                            broImage.startAnimation(animationd);
                            jumpButton.setEnabled(true);
                            //SAVE STATS FOR XP 16 July 2014 19:25

                            if (energy >= 10) {
                                Random rnd = new Random();
                                int randno = rnd.nextInt(10); //ÆNDRE FREKVENSEN AF INJURY
                                //Injury
                                if (randno == 4) {
                                    if (hap <= 0.1) {
                                        hap = (float) 0;
                                    }
                                    if (hap > 0.1) {
                                        hap -= (float) 0.1;
                                    }
                                    energy -= 10 * (hap + 0.1);
                                    Toast.makeText(getApplicationContext(), "Injury", Toast.LENGTH_SHORT).show();
                                    System.out.println(hap);
                                    System.out.println(energy);
                                }
                                energy -= 10;
                                //xp += 1*hap;
                                update();
                            } else {
                                Toast.makeText(getApplicationContext(), "Not enough energy", Toast.LENGTH_SHORT).show();
                            }
                            xp += 1;
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("XP", xp);
                            editor.putInt("Energy", energy);
                            editor.commit();
                            update();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    xp++;
                } else {
                    Toast.makeText(getApplicationContext(), "Not enough energy", Toast.LENGTH_SHORT).show();
                }
            }


        });
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

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // can be safely ignored
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        hasJumped = false;
        Log.d("Has Jumped", hasJumped + "");
        Log.d("Y", y + "");
            if (y > NOISE && !hasJumped) {
                Log.d("Y", y + "");
                if (energy > 0) {
                    Animation animationu = new TranslateAnimation(0, 0, 0, -400);
                    animationu.setDuration(500);
                    animationu.setFillAfter(true);
                    broImage.startAnimation(animationu);


                    animationu.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation animationd = new TranslateAnimation(0, 0, -400, 0);
                            animationd.setDuration(500);
                            animationd.setFillAfter(true);
                            broImage.startAnimation(animationd);
                            //SAVE STATS FOR XP 16 July 2014 19:25

                            if (energy >= 10) {
                                Random rnd = new Random();
                                int randno = rnd.nextInt(10); //ÆNDRE FREKVENSEN AF INJURY
                                //Injury
                                if (randno == 4) {
                                    if (hap <= 0.1) {
                                        hap = (float) 0;
                                    }
                                    if (hap > 0.1) {
                                        hap -= (float) 0.1;
                                    }
                                    energy -= 10 * (hap + 0.1);
                                    Toast.makeText(getApplicationContext(), "Injury", Toast.LENGTH_SHORT).show();
                                    System.out.println(hap);
                                    System.out.println(energy);
                                }
                                energy -= 10;
                                //xp += 1*hap;
                                update();
                            } else {
                                Toast.makeText(getApplicationContext(), "Not enough energy", Toast.LENGTH_SHORT).show();
                            }
                            xp += 1;
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("XP", xp);
                            editor.putInt("Energy", energy);
                            editor.commit();
                            update();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    if (y < -5) {
                        hasJumped = false;
                        Log.d("Has Jumped", hasJumped + "");
                    }
                }
            }

        }
    }
