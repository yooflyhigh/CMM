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

public class CNT_Student {
    String myJSON;
    public String Temp;
    private static final String TAG_RESULTS = "result";
    private static final String STUDENT_ID = "STUDENT_ID"; // 동아리 번호
    private static final String PASSWORD = "PASSWORD"; // 동아리 이름
    private static final String INPUT_ID = "INPUT_ID";
    private static final String INPUT_IP = "INPUT_IP";
    private static final String INPUT_DATE = "INPUT_DATE";
    private static final String UPDATE_ID = "UPDATE_ID";
    private static final String UPDATE_IP = "UPDATE_IP";
    private static final String UPDATE_DATE = "UPDATE_DATE";

    public JSONArray JSON_Student_Item;
    public ArrayList<HashMap<String, String>> Student_Item_list;

    public CNT_Student(){
        JSON_Student_Item = null;
        Student_Item_list = new ArrayList<HashMap<String, String>>();
        new Thread() {
            public void run() {
                myJSON = getData("http://210.115.230.212/Student.php");
                GetListData();
            }
        }.start();
    }

    public ArrayList<HashMap<String, String>> GetListData() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSON_Student_Item = jsonObj.getJSONArray(TAG_RESULTS);
            for (int i = 0; i < JSON_Student_Item.length(); i++) {
                JSONObject c = JSON_Student_Item.getJSONObject(i);
                String id = c.getString(STUDENT_ID);
                String name = c.getString(PASSWORD);
                /*
                String INID = c.getString(INPUT_ID);
                String INIP = c.getString(INPUT_IP);
                String INDATE = c.getString(INPUT_DATE);
                String UPID = c.getString(UPDATE_ID);
                String UPIP = c.getString(UPDATE_IP);
                String UPDATE = c.getString(UPDATE_DATE);
                */
                HashMap<String, String> Club_Item = new HashMap<String, String>();
                Club_Item.put(STUDENT_ID, id);
                Club_Item.put(PASSWORD, name);
                /*
                Club_Item.put(INPUT_ID, INID);
                Club_Item.put(INPUT_IP, INIP);
                Club_Item.put(INPUT_DATE, INDATE);
                Club_Item.put(UPDATE_ID, UPID);
                Club_Item.put(UPDATE_IP, UPIP);
                Club_Item.put(UPDATE_DATE, UPDATE);
                */
                Student_Item_list.add(Club_Item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Student_Item_list;
    }
    public void ClearListData(){
        Student_Item_list.clear();
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
