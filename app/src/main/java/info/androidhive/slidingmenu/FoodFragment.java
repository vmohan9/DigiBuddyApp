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
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class FoodFragment extends Fragment {
    Button bt,bt1,bt2;
    Spinner spinner1,spinner2,spinner3;

    SQLiteDatabase db;
    TextView t,l,d;
    TextView e,f,g;
    HashMap<String,String> hp=new HashMap<String,String>();
    HashMap<String,String> hpl=new HashMap<String,String>();
    HashMap<String,String> hpd=new HashMap<String,String>();
    public static int Calorie_val;
    private int type = 0;
    final int alarmID=(int)System.currentTimeMillis();

    final int alarmID1=(int)System.currentTimeMillis() + 10;
    final static private long ONE_SECOND = 1000;
    final static private long TWENTY_SECONDS = ONE_SECOND * 60;
    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;

    final static private long S_SECONDS = ONE_SECOND * 30;
    PendingIntent pi1;
    BroadcastReceiver br1;
    AlarmManager am1;


    final static private long T_SECONDS = ONE_SECOND * 90;
    PendingIntent pi2;
    BroadcastReceiver br2;
    AlarmManager am2;

    final static private long TH_SECONDS = ONE_SECOND * 180;
    PendingIntent pi3;
    BroadcastReceiver br3;
    AlarmManager am3;

	public FoodFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_food, container, false);
        setup();
        //setup1();
        //setup2();
        Calendar cal = Calendar.getInstance();
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), TWENTY_SECONDS, pi);
        //am1.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),S_SECONDS, pi1);
        //am2.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),T_SECONDS, pi2);
        hp.put("Bagel","100");
        hp.put("Sandwich","200");
        hp.put("Pancake","230");
        hp.put("Coffee","40");
        hp.put("Pasta","320");


        hpl.put("Chicken","420");
        hpl.put("Rice","300");
        hpl.put("Noodles","430");
        hpl.put("Roti","210");
        hpl.put("Turkey","430");

        hpd.put("Salad","120");
        hpd.put("Chicken with Maccaroni","210");
        hpd.put("Cornbread","240");
        hpd.put("Pizza","400");
        hpd.put("Potato wedges","210");






        spinner1 =(Spinner)rootView.findViewById(R.id.spinner1);
        spinner2=(Spinner)rootView.findViewById(R.id.spinner_l);
        spinner3=(Spinner)rootView.findViewById(R.id.spinner_d);
        t=(TextView)rootView.findViewById(R.id.Calorie_id);
        t.setVisibility(rootView.GONE);
        e=(TextView)rootView.findViewById(R.id.Value_id);
        e.setVisibility(rootView.GONE);
        l=(TextView)rootView.findViewById(R.id.lunch_id);
        l.setVisibility(rootView.GONE);
        f=(TextView)rootView.findViewById(R.id.lunch_val);
        f.setVisibility(rootView.GONE);
        d=(TextView)rootView.findViewById(R.id.dinner_id);
        d.setVisibility(rootView.GONE);
        g=(TextView)rootView.findViewById(R.id.dinner_value);
        g.setVisibility(rootView.GONE);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.fragment_food,R.id.textView,FoodList);
       // spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
         bt=(Button)rootView.findViewById(R.id.saveBtn);
         bt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 final String td = spinner1.getSelectedItem().toString();

                 for(String key:hp.keySet()) {
                    Boolean c = td.equals(key);
                     if (c==true) {
                      String  st = hp.get(key);
                         e.setText(st);
                         FoodFragment.Calorie_val += Integer.parseInt(st);
                     }
                 }

                 t.setVisibility(rootView.VISIBLE);

                 e.setVisibility(rootView.VISIBLE);

                 String p= e.getText().toString();

                Toast.makeText(getActivity(), "\nBreakfast selected : "+ String.valueOf(spinner1.getSelectedItem()),Toast.LENGTH_SHORT).show();

             }
         });
        bt1=(Button)rootView.findViewById(R.id.button_l);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String st=spinner2.getSelectedItem().toString();
                for(String key:hpl.keySet()){
                    Boolean c=st.equals(key);
                    if(c==true){
                        String l=hpl.get(key);
                        f.setText(l);
                        FoodFragment.Calorie_val+=Integer.parseInt(l);
                    }
                }

                l.setVisibility(View.VISIBLE);
                f.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "\nLunch Selected : "+ String.valueOf(spinner2.getSelectedItem()),Toast.LENGTH_SHORT).show();

            }
        });
        bt2=(Button)rootView.findViewById(R.id.button_d);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String st1=spinner3.getSelectedItem().toString();
                for(String key:hpd.keySet()){
                    Boolean t=st1.equals(key);
                    if(t==true){
                        String l=hpd.get(key);
                        g.setText(l);
                        FoodFragment.Calorie_val+=Integer.parseInt(l);
                    }
                }

                d.setVisibility(View.VISIBLE);
                g.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "\nDinner selected : "+ String.valueOf(spinner3.getSelectedItem()),Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

    private void setup1() {
        br1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set title
                alertDialogBuilder.setTitle("Food Log remainder");


                // set dialog message
                alertDialogBuilder
                        .setMessage("Log your breakfast for today")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

        };

        getActivity().registerReceiver(br1, new IntentFilter("info.androidhive.slidingmenu"));
        pi1 = PendingIntent.getBroadcast(getActivity(), alarmID, new Intent("info.androidhive.slidingmenu"),
                pi1.FLAG_UPDATE_CURRENT);
        am1 = (AlarmManager) (getActivity().getSystemService(Context.ALARM_SERVICE));
    }

    private void setup2() {
        br2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set title
                alertDialogBuilder.setTitle("Food Log remainder");


                // set dialog message
                alertDialogBuilder
                        .setMessage("Log your lunch for today")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

        };

        getActivity().registerReceiver(br2, new IntentFilter("info.androidhive.slidingmenu"));
        pi2 = PendingIntent.getBroadcast(getActivity(), alarmID1, new Intent("info.androidhive.slidingmenu"),
                pi2.FLAG_UPDATE_CURRENT);
        am2 = (AlarmManager) (getActivity().getSystemService(Context.ALARM_SERVICE));
    }


    private void setup() {
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                db = getActivity().openOrCreateDatabase("FitBud",android.content.Context.MODE_PRIVATE ,null);
                String sql = "CREATE TABLE IF NOT EXISTS FoodLog1(entry_date VARCHAR,Calorie REAL);";
                db.execSQL(sql);
                Calendar cal = Calendar.getInstance(); DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                cal.add(Calendar.DATE, 0); String d = dateformat.format(cal.getTime());
                Log.v("vahini:","abc");
                sql = "INSERT INTO FoodLog1 VALUES('"+d+"',"+FoodFragment.Calorie_val+");";
                db.execSQL(sql);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set title
                alertDialogBuilder.setTitle("Food Log remainder");


                // set dialog message
                alertDialogBuilder
                        .setMessage("Log your Food for today")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                FoodFragment.Calorie_val=0;
            }

        };

        getActivity().registerReceiver(br, new IntentFilter("info.androidhive.slidingmenu"));
        pi = PendingIntent.getBroadcast(getActivity(), 0, new Intent("info.androidhive.slidingmenu"),
                0);
        am = (AlarmManager) (getActivity().getSystemService(Context.ALARM_SERVICE));
    }


}
