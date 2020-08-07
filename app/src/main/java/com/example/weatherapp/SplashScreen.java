package com.example.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();


                try
                {
                    sleep(3000);

                }catch (Exception e )
                {
                    Toast.makeText(SplashScreen.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }
                finally {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    SplashScreen.this.finish();
                }

            }
        };
        thread.start();

    }
}
