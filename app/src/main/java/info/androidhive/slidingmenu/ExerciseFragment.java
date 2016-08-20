package info.androidhive.slidingmenu;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExerciseFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    public TextView count,calorie,dist;
    boolean activityRunning;

    final static private long ONE_SECOND = 1000;
    final static private long TWENTY_SECONDS = ONE_SECOND * 120;
    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;
    View rootView;
    float tval,nval;
    SQLiteDatabase db;
    final static double walkingFactor = 0.57;
    static double CaloriesBurnedPerMile;
    static double strip;
    static double stepCountMile; // step/mile
    static double conversationFactor;
    static double CaloriesBurned;
    static double distance;
    static NumberFormat formatter = new DecimalFormat("#0.00000");

    public ExerciseFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("vahini123", "022");
        rootView = inflater.inflate(R.layout.fragment_exercise, container, false);
        count = (TextView) rootView.findViewById(R.id.count);
        calorie = (TextView)rootView.findViewById(R.id.calorie);
        dist = (TextView)rootView.findViewById(R.id.dist);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        setup();
        Calendar cal = Calendar.getInstance();
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),TWENTY_SECONDS, pi);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            // Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        activityRunning = false;
// if you unregister the last listener, the hardware will stop detecting step events
// sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning) {
            count.setText(String.valueOf(event.values[0]-nval));
            tval = event.values[0];
            CaloriesBurnedPerMile = walkingFactor * (DataBaseHelper.weight * 2.2);
            strip = DataBaseHelper.height * 0.415;
            stepCountMile = 160934.4 / strip;
            conversationFactor = CaloriesBurnedPerMile / stepCountMile;
            CaloriesBurned = (event.values[0]-nval) * conversationFactor;
            distance = ((event.values[0]-nval) * strip) / 100000;
            calorie.setText(String.valueOf(formatter.format(CaloriesBurned)));
            dist.setText(String.valueOf(formatter.format(distance))+" miles");
            User.stepcount=event.values[0]-nval;
            User.stepcal=CaloriesBurned;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void setup() {
       br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                float a = tval-nval;
                //Toast.makeText(c,Float.toString(a), Toast.LENGTH_LONG).show();
                db = getActivity().openOrCreateDatabase("FitBud",android.content.Context.MODE_PRIVATE ,null);
                String sql = "CREATE TABLE IF NOT EXISTS ExerciseLog(entry_date VARCHAR,steps REAL);";
                db.execSQL(sql);
                Calendar cal = Calendar.getInstance(); DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                cal.add(Calendar.DATE, -1); String d = dateformat.format(cal.getTime());
                Log.v("vahini:","abc");
                sql = "INSERT INTO ExerciseLog VALUES('"+d+"',"+a+");";
                db.execSQL(sql);
                Log.v("vahini:", "def");
                Cursor cursor = db.rawQuery("Select * from ExerciseLog",null);
                if (cursor.moveToFirst()) {

                    while (cursor.isAfterLast() == false) {

                        String date = cursor.getString(cursor.getColumnIndex("entry_date"));
                        int yValue = cursor.getInt(cursor.getColumnIndex("steps"));
                        Log.d("vahu1", "entry date is" + date);
                        Log.d("vahu2", "steps is" + yValue);
                        cursor.moveToNext();
                    }
                }
                Toast.makeText(getActivity(), "The step count has been logged for today", Toast.LENGTH_LONG).show();
                nval = tval;

            }
        };

        getActivity().registerReceiver(br, new IntentFilter("info.androidhive.slidingmenu"));
        pi = PendingIntent.getBroadcast(getActivity(), 0, new Intent("info.androidhive.slidingmenu"),
                0);
        am = (AlarmManager) (getActivity().getSystemService(Context.ALARM_SERVICE));
    }
}


