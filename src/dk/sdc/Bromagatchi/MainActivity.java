package dk.sdc.Bromagatchi;

import android.app.Activity;
/*import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;*/
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.*;

import java.util.Date;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    public int hp = 100;
    public int xp = 0;
    public int energy = 99999;
    public float hap = 1;
    //public AlarmManager alarmMgr;
    //public PendingIntent alarmIntent;*/
    public Date date;

    private ImageView broImage;
    private ImageView imageView;
    private Button exercisebutton;

    private SharedPreferences prefs;


    public void update() {
        TextView hptext = (TextView) findViewById(R.id.HPstatTEXT);
        hptext.setText("HP"); //HP fjernet
        TextView XPstat = (TextView) findViewById(R.id.XPstatTEXT);
        XPstat.setText("GAINZ"); //XP fjernet
        TextView NRJstat = (TextView) findViewById(R.id.ENERGYstatTEXT);
        NRJstat.setText("ENERGY"); //Energy fjernet
        ProgressBar HPBar = (ProgressBar) findViewById(R.id.HPBar);
        HPBar.setProgress(hp);
        ProgressBar ENERGYBar = (ProgressBar) findViewById(R.id.ENERGYBar);
        ENERGYBar.setProgress((int) ((float) energy/50*100));
        ProgressBar XPBar = (ProgressBar) findViewById(R.id.XPBar);
        if (xp <= 5) {
            setImageBRO(R.drawable.phase0); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
            XPBar.setProgress((int) ((float) xp/5*100));
        }
        if (xp >= 5){
            setImageBRO(R.drawable.phase1); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
            XPBar.setProgress((int) ((float) (xp-5)/25*100));
        }
        if (xp > 30) {
            setImageBRO(R.drawable.phase2); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
            XPBar.setProgress((int) ((float) (xp-30)/20*100));
        }
        if (xp > 50) {
            setImageBRO(R.drawable.phase3); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
            XPBar.setProgress((int) ((float) (xp-50)/50*100));
        }
        if (xp > 100) {
            setImageBRO(R.drawable.phase4); //Ændre til BRO, så vi har en metode til at ændre baggrunden
            //Background skifte
            XPBar.setProgress((int) ((float) 100));
        }
        if (energy >= 50) {
            energy = 50;
        }
        if (energy <= 0) {
            energy = 0;
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
        if (diffrence / 1000 > 1) //HVERT MINUT
        {
            hp += diffrence/1000;
            energy += diffrence/1000; //10000 er blot en faktor
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
    protected void onPause() {
        super.onPause();

        // Gem nuværende tid
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("lastTime", System.currentTimeMillis());
        // Gem nuværende XP:
        editor.putInt("XP", xp);
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

        final Intent jumpIntent = new Intent(MainActivity.this , jump.class);
        final Intent restIntent = new Intent(MainActivity.this , rest.class);

        Button exercise = (Button) findViewById(R.id.exercisebutton);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( jumpIntent);


                //SAVE STATS FOR JUMPING ACTIVITY 16 July 2014 10:46
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("XP", xp);
                editor.putInt("Energy", energy);
                editor.putFloat("HAP", hp);
                editor.commit();
            }
        });

        Button rest = (Button) findViewById(R.id.restbutton);
        rest.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                startActivity(restIntent);

            }
        });

        //Reset Button
        Button bReset = (Button) findViewById(R.id.bRESET);
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp = 100;
                xp = 0;
                energy = 50;
                hap = 1;
                update();
            }
        });
    }
}
