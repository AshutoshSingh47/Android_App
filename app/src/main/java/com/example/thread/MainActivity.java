package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button, button1,button2,button3;
    TextView text;
    public int count = 0;
    Thread thread, thread2;
    Handler handler, handler2;
    Switch switch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            button = (Button) findViewById(R.id.increase);
            button1 = (Button) findViewById(R.id.decrease);
            button2=(Button)findViewById(R.id.clear);
            button3=(Button)findViewById(R.id.logout);
            text = (TextView) findViewById(R.id.update_ui);
            switch1 = (Switch) findViewById(R.id.mode);
            SharedPreferences sharedpref=getSharedPreferences("night",0);
            Boolean booleanValue =sharedpref.getBoolean("night mode",true);
            if(booleanValue)
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                switch1.setChecked(true);
            }

        // Increases the value of count variable.
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            count++;
                            SharedPreferences shrd=getSharedPreferences("demo",MODE_PRIVATE);
                            SharedPreferences.Editor editor=shrd.edit();
                            editor.putInt("counter",count);
                            editor.apply();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    text.setText(Integer.toString(count));
                                }
                            });
                        }

                    };
                    thread = new Thread(runnable);
                    thread.start();
                }
            });
        // Decreases the value of count variable.
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler2 = new Handler();
                    Runnable runnable2 = new Runnable() {
                        @Override
                        public void run() {
                            count--;
                            SharedPreferences shrd=getSharedPreferences("demo",MODE_PRIVATE);
                            SharedPreferences.Editor editor=shrd.edit();
                            editor.putInt("counter",count);
                            editor.apply();
                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    text.setText(Integer.toString(count));
                                }
                            });
                        }
                    };
                    thread2 = new Thread(runnable2);
                    thread2.start();
                }
            });
        // Reset the value of count variable to 0.
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                SharedPreferences shrd=getSharedPreferences("demo",MODE_PRIVATE);
                SharedPreferences.Editor editor= shrd.edit();
                editor.putInt("counter",count);
                editor.apply();
                text.setText(Integer.toString(count));
            }
        });
            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                    if(isChecked){
                        SharedPreferences getshared =getSharedPreferences("demo",MODE_PRIVATE);
                        int c=getshared.getInt("counter",0);
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        text.setText(Integer.toString(c));
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        switch1.setChecked(true);
                        SharedPreferences.Editor editor=sharedpref.edit();
                        editor.putBoolean("night mode",true);
                        editor.commit();
                    }
                    else{
                        SharedPreferences getshared =getSharedPreferences("demo",MODE_PRIVATE);
                        int c=getshared.getInt("counter",0);
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        text.setText(Integer.toString(c));
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        switch1.setChecked(false);
                        SharedPreferences.Editor editor=sharedpref.edit();
                        editor.putBoolean("night mode",false);
                        editor.commit();
                    }
                }
            });
        SharedPreferences getshared =getSharedPreferences("demo",MODE_PRIVATE);
        int c=getshared.getInt("counter",0);
        text.setText(Integer.toString(c));
        count=c;
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginPanel.class);
                startActivity(intent);
            }
        });

        }
//    @Override
//    protected void onPause() {
//        super.onPause();
//       SharedPreferences ss=getSharedPreferences("X",MODE_PRIVATE);
//        SharedPreferences.Editor editor =ss.edit();
//        editor.putString("lastactivity",getClass().getName());
//        editor.commit();
//    }
//
//        @Override
//    public void onRestoreInstanceState(Bundle savedInstance)
//        {
//            super.onRestoreInstanceState(savedInstance);
//        }
//    @Override
//    public void onSaveInstanceState(Bundle savedInstance)
//    {
//        super.onSaveInstanceState(savedInstance);
//    }



}
