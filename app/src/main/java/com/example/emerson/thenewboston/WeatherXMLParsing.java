package com.example.emerson.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WeatherXMLParsing extends Activity implements View.OnClickListener {

    static final String baseURL = "http://www.gooogle.com/ig/api?weather=";
    TextView tv;
    EditText city, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        Button b = (Button) findViewById(R.id.btWeather);
        tv = (TextView) findViewById(R.id.tvWeather);
        city = (EditText) findViewById(R.id.etCity);
        state = (EditText) findViewById(R.id.etState);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String c = city.getText().toString();
        String s = state.getText().toString();

        StringBuilder url = new StringBuilder(baseURL);
        url.append(c + "," + s);
        String fullURL = url.toString();

        try {
            URL website = new URL(fullURL);
            //Getting xmlreader to parse data
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            HandlingXMLStuff doingWork = new HandlingXMLStuff();
            xr.setContentHandler(doingWork);
            xr.parse(new InputSource(website.openStream()));
            String information = doingWork.getInformation();
            tv.setText(information);
        } catch (Exception e) {
            tv.setText("Deprecated API");
        }
    }

}
