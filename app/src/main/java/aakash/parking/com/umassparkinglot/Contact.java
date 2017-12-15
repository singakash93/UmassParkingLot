package aakash.parking.com.umassparkinglot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



public class Contact extends AppCompatActivity{

    SharedPreferences sheredPrefarence;
    SharedPreferences.Editor  editor;

    @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactinfo);
        sheredPrefarence = getSharedPreferences(Utils.SH_NAME, Context.MODE_PRIVATE);
        editor = sheredPrefarence.edit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_splashscreen, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id){
            case R.id.menu_parking:

                Intent In = new Intent(Contact.this,ParkingActivity.class);
                startActivity(In);
                finish();
                break;
            case R.id.menu_shuttle:
                Intent inti = new Intent(Contact.this,Shuttle.class);
                startActivity(inti);
                finish();
                break;
            case R.id.menu_wallet:
                Intent u =new Intent(Contact.this,Wallet.class);
                startActivity(u);
                finish();
                break;
            case R.id.menu_payment:
                Intent a = new Intent(Contact.this,Secure.class);
                startActivity(a);
                finish();
                break;
            case R.id.menu_contact:
                Intent b = new Intent(Contact.this,Contact.class);
                startActivity(b);
                finish();
                break;
            case R.id.menu_logout:
                editor.clear();
                editor.commit();
                Intent  i = new Intent(Contact.this,LoinActivity.class);
                startActivity(i);
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
