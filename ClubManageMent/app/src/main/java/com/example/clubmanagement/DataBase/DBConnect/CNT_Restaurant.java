package com.example.clubmanagement.DataBase.DBConnect;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class CNT_Restaurant {
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String REST_NM = "REST_NM";
    private static final String REST_PHONE_NO = "REST_PHONE_NO"; // 동아리 이름


    public JSONArray JSON_Restaurant;
    public ArrayList<HashMap<String, String>> Restaurant_list;

    public CNT_Restaurant() {
        JSON_Restaurant = null;
        Restaurant_list = new ArrayList<>();
        new Thread() {
            public void run() {
                myJSON = getData("http://210.115.230.212:80/RESTAURANT.php");
                GetListData();
            }
        }.start();
    }

    protected ArrayList<HashMap<String, String>> GetListData() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSON_Restaurant = jsonObj.getJSONArray(TAG_RESULTS);
            for (int i = 0; i < JSON_Restaurant.length(); i++) {
                JSONObject c = JSON_Restaurant.getJSONObject(i);
                String name = c.getString(REST_NM);
                String number = c.getString(REST_PHONE_NO);
                HashMap<String, String> Restaurant = new HashMap<String, String>();
                Restaurant.put(REST_NM, name);
                Restaurant.put(REST_PHONE_NO, number);
                Restaurant_list.add(Restaurant);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Restaurant_list;
    }

    public String getData(String uri) {
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setConnectTimeout(1000);
            con.setUseCaches(false);
            con.setRequestMethod("POST");

            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                sb.append(json + "\n");
            }

            return sb.toString().trim();

        } catch (Exception e) {
            return null;
        }
    }
}
