package aakash.parking.com.umassparkinglot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LoinActivity  extends AppCompatActivity {

    private SharedPreferences  sheredPrefarence;
    private SharedPreferences.Editor  editor; 
    Button  btnLogin,btnForget,btnRegister;
    EditText  edUser,edPass;
    DBHelper dbhelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.login_screen);
       setContentView(R.layout.newlogin);
        initUI();
        dbhelper  = new DBHelper(this);
        db = dbhelper.getWritableDatabase();
        sheredPrefarence = getSharedPreferences(Utils.SH_NAME, Context.MODE_PRIVATE);
        editor = sheredPrefarence.edit();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edUser.getText().toString();//compare with shared //
                String pass = edPass.getText().toString();//compare with shared preference object temp memory //
                //String  query = "select * from Register where "+DBHelper.USER_NAME+"= '"+userName+"' and "+DBHelper.PASSWORD+"='"+pass+"'";
                // String  loginQuery = "select * from Register where Gmail "
                String qwerty = "select * from Register where FulUserNamelName ='" +userName+"'  and Password = '"+pass+"'";
                Cursor c = db.rawQuery(qwerty,null);
                if(c.getCount() >0){

                    if(c.moveToFirst()){
                        do{

                            if(c.getString(1).equalsIgnoreCase("admin")){
                                editor.putBoolean(Utils.IS_admin, true);//shared preference temporary //
                                editor.apply();
                            }else{
                                editor.putBoolean(Utils.IS_admin, false);//shared preference temporary //
                                editor.apply();
                            }


                        }while (c.moveToNext());
                    }
                    Log.e("is Admin",""+sheredPrefarence.getBoolean(Utils.IS_admin,false));
                    Intent parkingInatent = new Intent(LoinActivity.this, ParkingActivity.class);
                    startActivity(parkingInatent);
                    editor.putBoolean(Utils.IS_LOGINED, true);
                    editor.apply();//temporary changes //
                    editor.commit();//shared prefernces interface used for modifying data values ina shared prefernce object//
                    finish();//and uses commit those changes back using editor.commit()- permanent changes //




                }else{
                    Toast.makeText(LoinActivity.this,"Wrong Detailes",Toast.LENGTH_LONG).show();
                }

               /* Intent parkingInatent = new Intent(LoinActivity.this, ParkingActivity.class);
                startActivity(parkingInatent);
                editor.putBoolean(Utils.IS_LOGINED, true);
                editor.commit();*/

            }
        });




        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent parkingInatent = new Intent(LoinActivity.this, ForgetPassword.class);
                startActivity(parkingInatent);
            }

        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parkingInatent = new Intent(LoinActivity.this, Registeractivity.class);
                startActivity(parkingInatent);
            }

        });





    }

    private void initUI() {
        btnLogin = (Button)  findViewById(R.id.btn_login);
        btnForget = (Button) findViewById(R.id.button3);
        btnRegister = (Button) findViewById(R.id.register);
        edUser = (EditText)  findViewById(R.id.ed_userName);
        edPass = (EditText)  findViewById(R.id.ed_password);
    }
}