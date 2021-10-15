package com.example.thread;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class LoginPanel extends AppCompatActivity {
    EditText user,pass;
    TextView text;
    Button button;
    int count=5;
    @Override
    public void onCreate(Bundle savedInstanceView)
    {
        super.onCreate(savedInstanceView);
        setContentView(R.layout.loginpage);
        user=(EditText)findViewById(R.id.user_id);
        pass=(EditText)findViewById(R.id.password);
        text=(TextView)findViewById(R.id.attempt);
        button=(Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equals("A")&&pass.getText().toString().equals("A"))
                {
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else
                {

                    count--;
                    text.setText("You have only "+count+" attempts");
                }
            }
        });
    }

}
