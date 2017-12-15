package aakash.parking.com.umassparkinglot;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.AbstractCollection;



public class Registeractivity extends Activity {
    private SharedPreferences shared_preference;
    private SharedPreferences.Editor  edit_or;
    Button but_submit;
    EditText edName,edpassword,edUsername,edconfirmpassword,edemailaddress,edage;

    DBHelper dbhelper;
    SQLiteDatabase  db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        initUI();
        dbhelper  = new DBHelper(this);
        db = dbhelper.getWritableDatabase();

        but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Integer.parseInt(edage.getText().toString()) <18){
                    Toast.makeText(Registeractivity.this, "age lesser", Toast.LENGTH_LONG).show();
                }else if(edemailaddress.getText().length() == 0) {

                    Toast.makeText(Registeractivity.this, "Empty fields are not allowed", Toast.LENGTH_LONG).show();
                } else
                 if(edemailaddress.getText().length() > 0){

                    String  query  = "select * from Register where Gmail = '"+edemailaddress.getText().toString()+"'";
                    Cursor  cg = db.rawQuery(query,null);
                    if(cg.getCount() > 0){

                        Toast.makeText(getApplicationContext(),"email id already exits",Toast.LENGTH_LONG).show();

                    }else{
                        ContentValues cv = new ContentValues();
                        cv.put(DBHelper.FULL_NAME, edName.getText().toString());
                        cv.put(DBHelper.USER_NAME, edUsername.getText().toString());
                        cv.put(DBHelper.PASSWORD, edpassword.getText().toString());
                        cv.put(DBHelper.Gmail, edemailaddress.getText().toString());
                        cv.put(DBHelper.AGE, edage.getText().toString());
                        db.insert("Register", null, cv);
                        Toast.makeText(Registeractivity.this, "inserted successfull", Toast.LENGTH_LONG).show();
                        Intent loginLogin = new Intent(Registeractivity.this, LoinActivity.class);
                        startActivity(loginLogin);
                        finish();
                    }
                }else{

                }
            }
        });
    }

    private void initUI() {

        edName = (EditText)  findViewById(R.id.Name);
        edpassword = (EditText)  findViewById(R.id.password);
        edUsername = (EditText)  findViewById(R.id.Username);
        edconfirmpassword = (EditText)  findViewById(R.id.confirmpassword);
        edemailaddress = (EditText)  findViewById(R.id.emailaddress);
        edage = (EditText)  findViewById(R.id.age);
        but_submit = (Button)   findViewById(R.id.button_submit);



    }
}
