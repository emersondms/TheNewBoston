package com.example.emerson.thenewboston;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    int counter;
    Button add, sub;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.tvDisplay);
        add = (Button) findViewById(R.id.btAdd);
        sub = (Button) findViewById(R.id.btSub);
        counter = 0;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                display.setText("Your total is " + counter);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                display.setText("Your total is " + counter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutUs: {
                Intent i = new Intent(
                    "com.example.emerson.thenewboston.ABOUT"
                );
                startActivity(i); break;
            }
            case R.id.preferences: {
                Intent p = new Intent(
                    "com.example.emerson.thenewboston.PREFS"
                );
                startActivity(p); break;
            }
            case R.id.exit: {
                finish(); break;
            }
        }
        return true;
    }

}
