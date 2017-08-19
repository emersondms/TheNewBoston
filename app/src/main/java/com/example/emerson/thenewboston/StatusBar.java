package com.example.emerson.thenewboston;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatusBar extends Activity implements View.OnClickListener {

    NotificationManager nm;
    static final int uniqueID = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbar);
        Button stat = (Button) findViewById(R.id.btStatusBar);
        stat.setOnClickListener(this);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(uniqueID);
        //finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StatusBar.class);
        PendingIntent pi = PendingIntent.getActivities(
            this, 0, new Intent[]{intent}, 0
        );
        String body = "This is a message from Emerson, thanks for your support";
        String title = "Emerson Delmondes";
        Notification n = new Notification(
            R.drawable.lightning, body, System.currentTimeMillis()
        );
        n.setLatestEventInfo(this, title, body, pi);
        n.defaults = Notification.DEFAULT_ALL;
        nm.notify(uniqueID, n);
    }

}
