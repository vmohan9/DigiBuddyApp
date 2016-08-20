package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Report extends Activity {

    TextView exerciseCal,foodCal,bmi,maxsteps,scorecard,sleep;
    NumberFormat formatter = new DecimalFormat("#0.00000");
    double bodymassindex,ht,wt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_report);
        ShareButton shareButton = (ShareButton)findViewById(R.id.share_btn);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postcontent();
            }
        });




        User.Height=130;
        User.Weight=50;

        ht = User.Height*User.Height;
        wt= User.Weight;

        bodymassindex=(wt/ht)*10000;




        exerciseCal=(TextView)findViewById(R.id.calorie_value);
        exerciseCal.setText(String.valueOf(formatter.format(User.stepcal)));

        foodCal=(TextView)findViewById(R.id.calorie_intake);
        foodCal.setText(String.valueOf(formatter.format(FoodFragment.Calorie_val)));
       // foodCal.setText(Integer.toString(FoodFragment.Calorie_val));

        bmi=(TextView)findViewById(R.id.bmi_val);
        bmi.setText(String.valueOf(formatter.format(bodymassindex)));
       // bmi.setText(Double.toString(bodymassindex));


        sleep=(TextView)findViewById(R.id.sleep_val);
        sleep.setText(String.valueOf(SleepFragment.no_hrs));}

        public void postcontent() {
        //check counter

        //save the screenshot
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);


        //share dialog
        AlertDialog.Builder shareDialog = new AlertDialog.Builder(this);
        shareDialog.setTitle("Share Report");
        shareDialog.setMessage("Share Report to Facebook?");
        final AlertDialog.Builder yes = shareDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                exerciseCal = (TextView) findViewById(R.id.calorie_value);
                exerciseCal.setText(String.valueOf(formatter.format(User.stepcal)));

                foodCal = (TextView) findViewById(R.id.calorie_intake);
                foodCal.setText(String.valueOf(formatter.format(FoodFragment.Calorie_val)));
                // foodCal.setText(Integer.toString(FoodFragment.Calorie_val));

                bmi = (TextView) findViewById(R.id.bmi_val);
                bmi.setText(String.valueOf(formatter.format(bodymassindex)));
                // bmi.setText(Double.toString(bodymassindex));


                sleep = (TextView) findViewById(R.id.sleep_val);
                sleep.setText(String.valueOf(SleepFragment.no_hrs));
            }
        });
        shareDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        shareDialog.show();

    }
        }




