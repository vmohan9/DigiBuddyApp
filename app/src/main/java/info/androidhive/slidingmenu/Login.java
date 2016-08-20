package info.androidhive.slidingmenu;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    DataBaseHelper help=new DataBaseHelper(this);
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt=(Button)findViewById(R.id.Loginbtn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText n=(EditText) findViewById(R.id.editUser);
                String str=n.getText().toString();
                EditText p=(EditText)findViewById(R.id.editPass);
                String strpass=p.getText().toString();

                String password=help.searchpass(str);
                if(strpass.equals(password))
                {
                    Log.d("vahini12",Float.toString(DataBaseHelper.height));
                    Log.d("vahini34",Float.toString(DataBaseHelper.weight));
                    Intent i = new Intent(Login.this,HomeActivity.class);
                    startActivity(i);
                }

                else
                {
                    Toast pass1= Toast.makeText(Login.this,"Username and password do not match", Toast.LENGTH_SHORT);
                    pass1.show();
                }


            }


        });

    }
}
