package aakash.parking.com.umassparkinglot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class ParkingActivity  extends AppCompatActivity {

    SharedPreferences  sheredPrefarence;
    SharedPreferences.Editor  editor;
    DBHelper dbhelper;
    SQLiteDatabase db;

    ArrayList<LocationObj> list;
    ListView  lv ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity);
        lv = (ListView)  findViewById(R.id.listView);
        dbhelper  = new DBHelper(this);
        //DBChecker.copyDatabase(ParkingActivity.this,"UmsPark");//
        sheredPrefarence = getSharedPreferences(Utils.SH_NAME, Context.MODE_PRIVATE);
        editor = sheredPrefarence.edit();
        db = dbhelper.getWritableDatabase();//db permission to write database//
        list = new ArrayList<>();
        String  query = "select * from Location ";
        Cursor c = db.rawQuery(query,null,null);
        if(c.getCount() > 0){
            if(c.moveToFirst()){
                do{

                    LocationObj  obj = new LocationObj();
                    obj.setLocationName(c.getString(0));//list of obj string//
                    obj.setLat(c.getString(1));
                    obj.setLon(c.getString(2));
                    obj.setseekCount(c.getString(3));
                    Log.e("Parking Activity","LocationName");
                    list.add(obj);//placing four values in the list at a tim e//

                }while(c.moveToNext());
            }


           // list.add(obj);

        }else{
            Toast.makeText(ParkingActivity.this,"Wrong Detailes",Toast.LENGTH_LONG).show();
        }
    ListAdapter  adapter = new ListAdapter(this,list);
        lv.setAdapter(adapter);//to display in the list we use adapter class//




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

           Intent  In = new Intent(ParkingActivity.this,ParkingActivity.class);
            startActivity(In);
            finish();
            break;
        case R.id.menu_shuttle:
            Intent inti = new Intent(ParkingActivity.this,Shuttle.class);
            startActivity(inti);
            finish();
            break;
        case R.id.menu_wallet:
            Intent u =new Intent(ParkingActivity.this,Secure.class);
            startActivity(u);
            finish();
            break;
        case R.id.menu_payment:
            Intent a = new Intent(ParkingActivity.this,Wallet.class);
            startActivity(a);
            finish();
            break;
        case R.id.menu_contact:
            Intent b = new Intent(ParkingActivity.this,Contact.class);
            startActivity(b);
            finish();
            break;
        case R.id.menu_logout:
            editor.clear();
            editor.commit();
            Intent  i = new Intent(ParkingActivity.this,LoinActivity.class);
            startActivity(i);
            finish();
            break;

    }

        return super.onOptionsItemSelected(item);
    }

}
