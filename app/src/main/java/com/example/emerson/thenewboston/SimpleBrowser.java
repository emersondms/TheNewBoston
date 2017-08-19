package com.example.emerson.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements View.OnClickListener {

    EditText url;
    WebView ourBrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        ourBrow = (WebView) findViewById(R.id.wvBrowser);
        ourBrow.getSettings().setJavaScriptEnabled(true);
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        ourBrow.getSettings().setUseWideViewPort(true);
        ourBrow.setWebViewClient(new OurViewClient());

        try {
            ourBrow.loadUrl("http://www.mybringback.com");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button go = (Button) findViewById(R.id.btGo);
        Button btBack = (Button) findViewById(R.id.btBack);
        Button refresh = (Button) findViewById(R.id.btRefresh);
        Button forward = (Button) findViewById(R.id.btForward);
        Button clearHistory = (Button) findViewById(R.id.btClearHistory);
        url = (EditText) findViewById(R.id.etUrl);
        go.setOnClickListener(this);
        btBack.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btGo: {
                String theWebsite = "http://" + url.getText().toString();
                try {
                    ourBrow.loadUrl(theWebsite);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Hiding the keyboard after using an EditText
                InputMethodManager imm = (InputMethodManager)getSystemService(
                    Context.INPUT_METHOD_SERVICE
                );
                imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
                break;
            }
            case R.id.btBack: {
                if (ourBrow.canGoBack()) ourBrow.goBack(); break;
            }
            case R.id.btForward: {
                if (ourBrow.canGoForward()) ourBrow.goForward(); break;
            }
            case R.id.btRefresh: {
                ourBrow.reload(); break;
            }
            case R.id.btClearHistory: {
                ourBrow.clearHistory(); break;
            }
        }
    }

}
