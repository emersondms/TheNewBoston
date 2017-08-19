package com.example.emerson.thenewboston;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

    Class ourClass;
    String classes [] = {
        "MainActivity", "TextPlay", "Email",
        "Camera", "Data", "GFX", "GFXSurface",
        "SoundStuff", "Slider", "Tabs",
        "SimpleBrowser", "Flipper", "SharedPrefs",
        "InternalData", "ExternalData", "SQLiteExample",
        "Accelerate", "HttpExample", "WeatherXMLParsing",
        "Voice", "TextVoice", "StatusBar", "SeekBarVolume"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );*/

        setListAdapter(new ArrayAdapter<String>(
            Menu.this, android.R.layout.simple_list_item_1, classes
        ));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese = classes[position];
        try {
            ourClass = Class.forName(
                "com.example.emerson.thenewboston." + cheese
            );
            Intent ourIntent = new Intent(Menu.this, ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
