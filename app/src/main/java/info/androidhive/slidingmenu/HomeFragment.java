package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HomeFragment extends Fragment {

    TextView stepcount,foodcal,stepcal,sleeplog,caltot,scorecard;
    Button bt_r,bt_e;
    public int score;
    TextView stat;
    static NumberFormat formatter = new DecimalFormat("#0.00000");
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        bt_r = (Button)rootView.findViewById(R.id.button_Report);
        bt_e=(Button)rootView.findViewById(R.id.bt_estimate);

        stepcount = (TextView)rootView.findViewById(R.id.step);
        stepcount.setText("Step count: " + String.valueOf(User.stepcount));

        float d= (float) ((20*5) /100);
        stat = (TextView)rootView.findViewById(R.id.status);

        scorecard = (TextView)rootView.findViewById(R.id.score);
        if(User.stepcount>200.00 && User.stepcount<400.00)
        {
            scorecard.setText("Score: 10");
            RatingBar rb = (RatingBar)rootView.findViewById(R.id.ratingBar1);
            rb.setRating(1);
            stat.setBackgroundColor(Color.GREEN);
            stat.setText("Can Do Better..!!!");
        }
        else if(User.stepcount>400.00 && User.stepcount<600)
        {
            scorecard.setText("Score: 20");
            RatingBar rb = (RatingBar)rootView.findViewById(R.id.ratingBar1);
            rb.setRating(2);
            stat.setBackgroundColor(Color.GREEN);
            stat.setText("Very Good Keep Up..!!!");
        }
       else if(User.stepcount>600.00)
        {
            scorecard.setText("Score: 30");
            RatingBar rb = (RatingBar)rootView.findViewById(R.id.ratingBar1);
            rb.setRating(3);
            stat.setBackgroundColor(Color.GREEN);
            stat.setText("Amazing Performance..!!!");
        }
        else if(User.stepcount<200.00)
        {
            stat.setBackgroundColor(Color.RED);
            stat.setText("Critical...!!!");
        }
        stepcal = (TextView)rootView.findViewById(R.id.stepcal);
        stepcal.setText("Step calorie: "+String.valueOf(formatter.format(User.stepcal)));

        foodcal = (TextView)rootView.findViewById(R.id.foodcal);
        foodcal.setText("Food calorie: "+FoodFragment.Calorie_val);

        caltot = (TextView)rootView.findViewById(R.id.caltotal);
        caltot.setText("Weighted calorie: "+formatter.format(new Integer(FoodFragment.Calorie_val).doubleValue()- User.stepcal));

        sleeplog = (TextView)rootView.findViewById(R.id.sleeplog);
        sleeplog.setText("Number of hours slept: "+String.valueOf(SleepFragment.no_hrs));

        bt_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it;
                it = new Intent(getActivity(), Report.class);
                startActivity(it);

            }


        });
        bt_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),Estimate.class);
                startActivity(i);
            }
        });



        return rootView;






    }
}
