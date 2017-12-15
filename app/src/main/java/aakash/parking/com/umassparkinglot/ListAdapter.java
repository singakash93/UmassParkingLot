package aakash.parking.com.umassparkinglot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import static aakash.parking.com.umassparkinglot.R.id.startCount;



public class ListAdapter  extends BaseAdapter {

    private ArrayList<LocationObj>  lv;
    private Context  mContext;
    LayoutInflater  inflater;
    private SharedPreferences sheredPrefarence;
    private  SharedPreferences.Editor editor;
    ViewHolder viewHolder = null;
    DBHelper  dbHelper;
    SQLiteDatabase  db;



    public ListAdapter(ParkingActivity parkingActivity, ArrayList<LocationObj> list) {
        mContext= parkingActivity;//parking activity-list adapter//
        lv= list;


        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//Layout inflater is used to create a new view object from XML layouts//
        dbHelper  = new DBHelper(mContext);
        db = dbHelper.getWritableDatabase();


    }


    @Override
    public int getCount() {
        return lv.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final TextView  tcount;

        convertView= inflater.inflate(R.layout.list_adapter,null);
        TextView  tv = (TextView)  convertView.findViewById(R.id.placeName);
        SeekBar sb = (SeekBar) convertView.findViewById(R.id.itemseek);
        tcount = (TextView) convertView.findViewById(startCount);
        tv.setText(lv.get(position).getLocationName());

        sheredPrefarence = mContext.getSharedPreferences(Utils.SH_NAME, Context.MODE_PRIVATE);
        editor = sheredPrefarence.edit();

        if(sheredPrefarence.getBoolean(Utils.IS_admin,false) == false){
            sb.setEnabled(false);
        }else if(sheredPrefarence.getBoolean(Utils.IS_admin,false) == true){
            sb.setEnabled(true);
        }
        sb.setProgress(Integer.parseInt(lv.get(position).getseekCount()));
        tcount.setText(""+Integer.parseInt(lv.get(position).getseekCount()));

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MAp Activity","this is map Activityl"+lv.get(position).getLon());

                // remove this line for remove map
                //Intent  i = new Intent(mContext,ParkingMap.class);
               /*Intent  i = new Intent(mContext,MainActivity.class);
                i.putExtra("Name",lv.get(position).getLocationName());
                i.putExtra("Lat",lv.get(position).getLat());
                i.putExtra("Lon",lv.get(position).getLon());
                i.putExtra("scount",lv.get(position).getseekCount());
                mContext.startActivity(i);
*/
            }
        });
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tcount.setText("" + progress);
                dbHelper.updateContact(lv.get(position).getLocationName(),""+progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView countstart;
        TextView home;
    }

}
