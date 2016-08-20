package info.androidhive.slidingmenu;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class SleepFragment extends Fragment {
    Button submitbtn; EditText hr;
    public static int no_hrs;
    final static private long ONE_SECOND = 1000;
    final static private long TWENTY_SECONDS = ONE_SECOND * 60;
    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;
    View rootView;
    SQLiteDatabase db;
	public SleepFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.fragment_sleep, container, false);
        submitbtn = (Button)rootView.findViewById(R.id.submit);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("FitBud",android.content.Context.MODE_PRIVATE ,null);
                String sql = "CREATE TABLE IF NOT EXISTS SleepLog4(entry_date VARCHAR,hours INTEGER);";
                db.execSQL(sql);
                Calendar cal = Calendar.getInstance(); DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                cal.add(Calendar.DATE, -1); String d = dateformat.format(cal.getTime());
                hr =(EditText) rootView.findViewById(R.id.sleephr);
                no_hrs = Integer.parseInt(hr.getText().toString());
                sql = "INSERT INTO SleepLog4 VALUES('"+d+"',"+Integer.parseInt(hr.getText().toString())+");";
                db.execSQL(sql);
                Cursor cursor = db.rawQuery("Select * from SleepLog4",null);
                if (cursor.moveToFirst()) {

                    while (cursor.isAfterLast() == false) {

                        String date = cursor.getString(cursor.getColumnIndex("entry_date"));
                        int yValue = cursor.getInt(cursor.getColumnIndex("hours"));
                        Log.d("vahu1", "Xvalue in top10 is" + date);
                        Log.d("vahu2", "YValue in top10 is" + yValue);
                        cursor.moveToNext();
                    }
                }
                Toast.makeText(getActivity(), "Sleep hours logged", Toast.LENGTH_LONG).show();
            }
        });

        setup();
        Calendar cal = Calendar.getInstance();
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), TWENTY_SECONDS, pi);
            return rootView;
    }

    private void setup() {
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                //Toast.makeText(c, "Rise and Shine!", Toast.LENGTH_LONG).show();
                popup();
            }
        };

        getActivity().registerReceiver(br, new IntentFilter("info.androidhive.slidingmenu"));
        pi = PendingIntent.getBroadcast(getActivity(), 0, new Intent("info.androidhive.slidingmenu"),
                0);
        am = (AlarmManager) (getActivity().getSystemService(Context.ALARM_SERVICE));
    }

    private void popup()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle("Sleep Log remainder");


        // set dialog message
        alertDialogBuilder
                .setMessage("Log your sleep hours for today")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
