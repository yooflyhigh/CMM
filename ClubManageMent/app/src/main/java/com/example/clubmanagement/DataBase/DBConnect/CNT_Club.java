package com.example.clubmanagement.DataBase.DBConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class CNT_Club {
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String CLUB_ID = "CLUB_ID"; // 동아리 번호
    private static final String CLUB_NM = "CLUB_NM"; // 동아리 이름
    private static final String CLUB_GB_CD = "CLUB_GB_CD"; // 중동/전공
    private static final String CLUB_AT_CD = "CLUB_AT_CD"; // 학술/운동...
    private static final String CLUB_CNT = "CLUB_CNT";
    private static final String CLUB_AIM = "CLUB_AIM";
    private static final String CLUB_ACTIVE = "CLUB_ACTIVE";
    private static final String CLUB_ROOM = "CLUB_ROOM";
    private static final String OPEN_DT = "OPEN_DT";
    private static final String INTRO_CONT = "INTRO_CONT";
    private static final String INTRO_FILE_NM = "INTRO_FILE_NM";
    private static final String INTRO_FILE_PATH = "INTRO_FILE_PATH";
    private static final String INTRO_SAVE_FILE_NM = "INTRO_SAVE_FILE_NM";
    private static final String INPUT_ID = "INPUT_ID";
    private static final String INPUT_IP = "INPUT_IP";
    private static final String INPUT_DATE = "INPUT_DATE";
    private static final String UPDATE_ID = "UPDATE_ID";
    private static final String UPDATE_IP = "UPDATE_IP";
    private static final String UPDATE_DATE = "UPDATE_DATE";

    public JSONArray JSON_Club_Item;
    public ArrayList<HashMap<String, String>> Club_Item_list;


    public CNT_Club() {
        JSON_Club_Item = null;
        Club_Item_list = new ArrayList<HashMap<String, String>>();

        new Thread() {
            public void run() {
                myJSON = getData("http://210.115.230.212:80/CLUB.php");
                GetListData();
            }
        }.start();
    }

    protected ArrayList<HashMap<String, String>> GetListData() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSON_Club_Item = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < JSON_Club_Item.length(); i++) {
                JSONObject c = JSON_Club_Item.getJSONObject(i);
                String id = c.getString(CLUB_ID);
                String name = c.getString(CLUB_NM);
                String GB_CD = c.getString(CLUB_GB_CD);
                String AT_CD = c.getString(CLUB_AT_CD);
                String CNT = c.getString(CLUB_CNT);
                String AIM = c.getString(CLUB_AIM);
                String ACTIVE = c.getString(CLUB_ACTIVE);
                String ROOM = c.getString(CLUB_ROOM);
                String OPENDT = c.getString(OPEN_DT);
                String CONT = c.getString(INTRO_CONT);
                String FILE_NM = c.getString(INTRO_FILE_NM);
                String FILE_PATH = c.getString(INTRO_FILE_PATH);
                String SAVE_FILE_NM = c.getString(INTRO_SAVE_FILE_NM);
                String INID = c.getString(INPUT_ID);
                String INIP = c.getString(INPUT_IP);
                String INDATE = c.getString(INPUT_DATE);
                String UPID = c.getString(UPDATE_ID);
                String UPIP = c.getString(UPDATE_IP);
                String UPDATE = c.getString(UPDATE_DATE);
                HashMap<String, String> Club_Item = new HashMap<String, String>();

                Club_Item.put(CLUB_ID, id);
                Club_Item.put(CLUB_NM, name);
                Club_Item.put(CLUB_GB_CD, GB_CD);
                Club_Item.put(CLUB_AT_CD, AT_CD);
                Club_Item.put(CLUB_CNT, CNT);
                Club_Item.put(CLUB_AIM, AIM);
                Club_Item.put(CLUB_ACTIVE, ACTIVE);
                Club_Item.put(CLUB_ROOM, ROOM);
                Club_Item.put(OPEN_DT, OPENDT);
                Club_Item.put(INTRO_CONT, CONT);
                Club_Item.put(INTRO_FILE_NM, FILE_NM);
                Club_Item.put(INTRO_FILE_PATH, FILE_PATH);
                Club_Item.put(INTRO_SAVE_FILE_NM, SAVE_FILE_NM);
                Club_Item.put(INPUT_ID, INID);
                Club_Item.put(INPUT_IP, INIP);
                Club_Item.put(INPUT_DATE, INDATE);
                Club_Item.put(UPDATE_ID, UPID);
                Club_Item.put(UPDATE_IP, UPIP);
                Club_Item.put(UPDATE_DATE, UPDATE);
                Club_Item_list.add(Club_Item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Club_Item_list;
    }

    public void ClearListData() {
        Club_Item_list.clear();
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