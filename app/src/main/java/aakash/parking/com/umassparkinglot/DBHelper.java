package aakash.parking.com.umassparkinglot;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper  extends SQLiteOpenHelper {

    public static String FULL_NAME = "FullName";
    public static String USER_NAME = "FulUserNamelName";
    public static String PASSWORD = "Password";
    public static String Gmail = "Gmail";
    public static String AGE = "age";
    Context mContext;
    public DBHelper(Context context) {
        super(context, "UmsPark", null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String   query = "create table Register("+FULL_NAME+" varchar(40),"+USER_NAME+" varchar(40),"+PASSWORD+" varchar(40),"+Gmail+" varchar(20),"+AGE+" varchar(20))";
        String  parkTable = "create table Location(LocationName varchar(30),Lat varchar(20),Lon varchar(20),seekCount varchar(20))";

        db.execSQL(query);
        db.execSQL(parkTable);

        db.execSQL("Insert into Location values('Boston Globe','42.3155805','-71.0532585','60')");
        db.execSQL("Insert into Location values('Church Lot','42.3185868','-71.0505214','75')");
        db.execSQL("Insert into Location values('Bayside Lot','42.319923','-71.0495927','42')");
        db.execSQL("Insert into Location values('University Lot D','42.3170519','-71.0432591','20')");
        db.execSQL("Insert into Location values('Beacons Lot','42.3170519','-71.0432591','89')");
        db.execSQL("Insert into Location values('Campus Center Garage','42.3169906','-71.0432591','10')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    /* public void loginDb(String user,String pass){
         SQLiteDatabase  db = new SQLiteDatabase(mContext);
     }*/
    public boolean updateContact(String rowId, String name) {
        SQLiteDatabase  db = getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put("seekCount", name);//Updating the seek value as per the locations//
        return db.update("Location", args, "LocationName ='" + rowId+"'", null) > 0;

    }


}

