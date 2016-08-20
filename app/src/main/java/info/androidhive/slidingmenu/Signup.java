package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Signup extends Activity {
    DataBaseHelper help=new DataBaseHelper(this);
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=(Button)findViewById(R.id.Sbtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.editname);
                EditText email = (EditText) findViewById(R.id.editEmail);
                EditText uname = (EditText) findViewById(R.id.editUname);
                EditText pass1 = (EditText) findViewById(R.id.editPassword);
                EditText pass2 = (EditText) findViewById(R.id.editCpass);
                EditText age=(EditText)findViewById(R.id.editAge);
                RadioGroup sex=(RadioGroup)findViewById(R.id.rdg);
                EditText height=(EditText)findViewById(R.id.editHeight);
                EditText weight=(EditText)findViewById(R.id.editWeight);

                String n = name.getText().toString();
                String e = email.getText().toString();
                String u = uname.getText().toString();
                String st = pass1.getText().toString();
                String st1 = pass2.getText().toString();
                String se = ((RadioButton)findViewById(sex.getCheckedRadioButtonId() )).getText().toString();
                String ag=age.getText().toString();
                int h=Integer.parseInt(height.getText().toString());
                int w=Integer.parseInt(weight.getText().toString());

                if (!st.equals(st1)) {
                    Toast p = Toast.makeText(Signup.this, "Passwords dont match", Toast.LENGTH_SHORT);
                    p.show();
                } else {
                    User user = new User();
                    user.setName(n);
                    user.setEmailaddress(e);
                    user.setUsername(u);
                    user.setPassword(st);
                    user.setAge(ag);
                    user.setSex(se);
                    user.setHeight(h);
                    user.setWeight(w);

                    help.insertmethod(user);

                    Intent fit=new Intent(Signup.this,MainActivity.class);
                    startActivity(fit);
                }




            }
        });


    }
}
