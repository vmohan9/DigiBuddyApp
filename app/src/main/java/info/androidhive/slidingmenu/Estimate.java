package info.androidhive.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Estimate extends Activity {

    public static float ideal_stepcount=4000;
    public static float nextstep;
    public static  double nextcal;
    public static int sleepLog=10;
    static NumberFormat formatter = new DecimalFormat("#0.00000");


    TextView idealcount,stepcount,stepnext,exersicecal,calfood,calnext,sleeplog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);


        nextstep=ideal_stepcount-User.stepcount;

        nextcal=FoodFragment.Calorie_val-User.stepcal;

        idealcount=(TextView)findViewById(R.id.idealstep_val);
        idealcount.setText(Float.toString(ideal_stepcount));

        stepcount=(TextView)findViewById(R.id.stepcount_val);
        stepcount.setText(Float.toString(User.stepcount));

        stepnext=(TextView)findViewById(R.id.stepnext_val);
        stepnext.setText(Float.toString(nextstep));

        exersicecal=(TextView)findViewById(R.id.exercise_val);
        exersicecal.setText((formatter.format(User.stepcal)));

        calfood=(TextView)findViewById(R.id.foodcal_val);
        calfood.setText(String.valueOf(FoodFragment.Calorie_val));

        calnext=(TextView)findViewById(R.id.nextday_cal);
        calnext.setText(formatter.format(nextcal));

        sleeplog=(TextView)findViewById(R.id.sleephours_logged);
        sleeplog.setText(Integer.toString(sleepLog));







    }
}
