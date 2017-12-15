package aakash.parking.com.umassparkinglot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class ForgetPassword  extends AppCompatActivity {

    Button  btn;
    EditText ed;
    private    DBHelper  dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_screen);
        btn = (Button)  findViewById(R.id.btn_forget);
        ed = (EditText)  findViewById(R.id.ed_forget);
        dbHelper  = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mail = ed.getText().toString();
                String pass = null;
                String qwerty = "select * from Register where Gmail ='" + Mail + "'";
                Cursor c = db.rawQuery(qwerty, null);
                if (c.getCount() > 0) {
                    if (c.moveToFirst()) {
                        pass = c.getString(2);

                    }
                    while (c.moveToNext()) ;

                    String to = ed.getText().toString();

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
                    email.putExtra(Intent.EXTRA_SUBJECT, "Recovery password");
                    email.putExtra(Intent.EXTRA_TEXT, "The last password id :"+pass);

                    email.setType("message/rfc822");//email type//

                    startActivity(Intent.createChooser(email, "Choose an Email client"));

                }else{
                    Toast.makeText(getApplicationContext(),"No mailid matched",Toast.LENGTH_LONG).show();
                }




            }
        });
    }
}
