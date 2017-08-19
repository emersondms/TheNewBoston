package com.example.emerson.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {

    MediaPlayer ourSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ourSong = MediaPlayer.create(Splash.this, R.raw.bass_solo);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(
            getBaseContext()
        );
        boolean music = getPrefs.getBoolean("checkbox", true);
        if (music) ourSong.start();

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openStartingPoint = new Intent(
                        "com.example.emerson.thenewboston.MENU"
                    );
                    startActivity(openStartingPoint);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }

}
