package aakash.parking.com.umassparkinglot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class Splashscreen extends Activity {

    int SPLASH_DISPLAY_LENGTH = 3000;
    SharedPreferences  sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        sharedPreferences = getSharedPreferences(Utils.SH_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

if(!sharedPreferences.getBoolean(Utils.IS_LOGINED,false)){
    Intent loginIntent = new Intent(Splashscreen.this, LoinActivity.class);
    startActivity(loginIntent);
    Splashscreen.this.finish();

}else {

    Intent loginIntent = new Intent(Splashscreen.this, ParkingActivity.class);
    startActivity(loginIntent);
    Splashscreen.this.finish();

}

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    }


