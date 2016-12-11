package com.byteshaft.requests.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.byteshaft.requests.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements
        HttpRequest.OnReadyStateChangeListener {

    private static final String API_BASE = "http://localhost:8000/api/%s";
    private static final String TEST_URL = String.format(API_BASE, "me");
    private static final String TOKEN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpRequest request = new HttpRequest(getApplicationContext());
        request.setOnReadyStateChangeListener(this);
        request.open("PUT", TEST_URL);
        request.setRequestHeader("Authorization", String.format("Token %s", TOKEN));
        request.send(getData());
    }

    private String getData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("last_name", "TTTTTTTT");
            jsonObject.put("first_name", "UUSUFUS");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public void onReadyStateChange(HttpRequest request, int readyState) {
        switch (readyState) {
            case HttpRequest.STATE_DONE:
                System.out.println(request.getResponseText());
        }
    }
}
