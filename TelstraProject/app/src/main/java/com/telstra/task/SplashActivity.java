package com.telstra.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ruban on 1/22/2016.
 * Splash Screen Displaying App logo
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Intent i= new Intent(SplashActivity.this,ItemListActivity.class);
        startActivity(i);
        finish();

    }




}
