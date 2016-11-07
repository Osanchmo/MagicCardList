package com.example.a21753725a.magiccardlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(this, SettingsActivity.class));
                    return true;
                }
                return super.onOptionsItemSelected(item);
            }
}
