package com.telstra.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Ruban on 1/22/2016.
 * Splash Screen Displaying App logo
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Intent i= new Intent(SplashActivity.this,ItemListActivity.class); // TODO: 1/22/2016 need to put 3 sec timer
        startActivity(i);
        finish();

    }




}
