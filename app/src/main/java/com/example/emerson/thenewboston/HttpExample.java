package com.example.emerson.thenewboston;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class HttpExample extends Activity {

    TextView httpStuff;
    HttpClient client;
    JSONObject json;
    final static String URL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpex);
        httpStuff = (TextView) findViewById(R.id.tvHttp);
        client = new DefaultHttpClient();
        //new Read().execute("text");
    }

    public JSONObject lastTweet(String username) throws ClientProtocolException, IOException, JSONException{
        StringBuilder url = new StringBuilder(URL);
        url.append(username);
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if (status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray timeline = new JSONArray(data);
            JSONObject last = timeline.getJSONObject(0);
            return last;
        } else {
            Toast.makeText(HttpExample.this, "Error", Toast.LENGTH_SHORT);
            return null;
        }
    }

    public class Read extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                json = lastTweet("mybringback");
                return json.getString(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            httpStuff.setText(result);
        }
    }

}
