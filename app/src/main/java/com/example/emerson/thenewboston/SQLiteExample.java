package com.example.emerson.thenewboston;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.sql.SQLException;

public class SQLiteExample extends Activity implements View.OnClickListener {

    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlHotness, sqlRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);
        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
        sqlView = (Button) findViewById(R.id.bSQLopenView);
        sqlModify = (Button) findViewById(R.id.bSQLmodify);
        sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLdelete);
        sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlModify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSQLUpdate: {
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();
                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                } catch (Exception e) {
                    didItWork = false;
                    showDialog(didItWork, e);
                } finally {
                    showDialog(didItWork, null);
                } break;
            }
            case R.id.bSQLopenView: {
                Intent i = new Intent(
                    "com.example.emerson.thenewboston.SQLVIEW"
                );
                startActivity(i);
                break;
            }
            case R.id.bgetInfo: {
                String s = sqlRow.getText().toString();
                long l = Long.parseLong(s);
                HotOrNot hon = new HotOrNot(this);
                try {
                    hon.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String returnedName = hon.getName(l);
                String returnedHotness = hon.getHotness(l);
                hon.close();
                sqlName.setText(returnedName);
                sqlHotness.setText(returnedHotness);
                break;
            }
            case R.id.bSQLmodify: {
                boolean didItWork = true;
                try {
                    String sRow = sqlRow.getText().toString();
                    long lRow = Long.parseLong(sRow);
                    String mName = sqlName.getText().toString();
                    String mHotness = sqlHotness.getText().toString();
                    HotOrNot ex = new HotOrNot(this);
                    try {
                        ex.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ex.updateEntry(lRow, mName, mHotness);
                    ex.close();
                } catch (Exception e) {
                    didItWork = false;
                    showDialog(didItWork, e);
                } finally {
                    showDialog(didItWork, null);
                } break;
            }
            case R.id.bSQLdelete: {
                boolean didItWork = true;
                try {
                    String sRow1 = sqlRow.getText().toString();
                    long lRow1 = Long.parseLong(sRow1);
                    HotOrNot ex1 = new HotOrNot(this);
                    try {
                        ex1.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ex1.deleteEntry(lRow1);
                    ex1.close();
                } catch (Exception e) {
                    didItWork = false;
                    showDialog(didItWork, e);
                } finally {
                    showDialog(didItWork, null);
                } break;
            }
        }
    }

    public void showDialog(boolean success, Exception e) {
        Dialog d = new Dialog(SQLiteExample.this);
        if (success) {
            d.setTitle("Heck Yea!");
            TextView tv = new TextView(SQLiteExample.this);
            tv.setText("Success");
            d.setContentView(tv);
            d.show();
        } else {
            String error = e.toString();
            d.setTitle("Dang it!");
            TextView tv = new TextView(SQLiteExample.this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();
        }
    }

}
