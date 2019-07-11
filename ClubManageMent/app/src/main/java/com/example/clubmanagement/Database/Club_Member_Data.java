package com.example.clubmanagement.Database;

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

public class Club_Member_Data {
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String CLUB_ID = "CLUB_ID"; // 동아리 번호
    private static final String STUDENT_ID = "STUDENT_ID"; // 학번
    private static final String NM = "NM";
    private static final String MAJOR = "MAJOR"; // 이름
    private static final String GRADE = "GRADE";
    private static final String GENDER_CD = "GENDER_CD";
    private static final String STAFF_CD = "STAFF_CD";
    private static final String PHONE_NO = "PHONE_NO";
    private static final String POST_NO = "POST_NO";
    private static final String ADDRESS = "ADDRESS";
    private static final String PER_INFO_AGG_YN = "PER_INFO_AGG_YN";
    private static final String EMAIL = "EMAIL";
    private static final String JOIN_DT = "JOIN_DT";
    private static final String BIRTH_DT = "BIRTH_DT";
    private static final String ING_CD = "ING_CD";
    private static final String JOIN_CD = "JOIN_CD";
    private static final String INPUT_ID = "INPUT_ID";
    private static final String INPUT_IP = "INPUT_IP";
    private static final String INPUT_DATE = "INPUT_DATE";
    private static final String UPDATE_ID = "UPDATE_ID";
    private static final String UPDATE_IP = "UPDATE_IP";
    private static final String UPDATE_DATE = "UPDATE_DATE";

    public JSONArray JSON_Club_Item = null;
    public ArrayList<HashMap<String, String>> Club_Member_Item_list;

    public Club_Member_Data() {
        JSON_Club_Item = null;
        Club_Member_Item_list = new ArrayList<HashMap<String, String>>();

        new Thread() {
            public void run() {
                myJSON = getData("http://210.115.230.212/CLUB_MEMBER.php");
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
                String S_id = c.getString(STUDENT_ID);
                String name = c.getString(NM);
                String MAJ = c.getString(MAJOR);
                String GRA = c.getString(GRADE);
                String GENDERCD = c.getString(GENDER_CD);
                String STAFFCD = c.getString(STAFF_CD);
                String PHONENO = c.getString(PHONE_NO);
                String POSTNO = c.getString(POST_NO);
                String ADD = c.getString(ADDRESS);
                String PER_INFO = c.getString(PER_INFO_AGG_YN);
                String EMA = c.getString(EMAIL);
                String JOINDT = c.getString(JOIN_DT);
                String BIRTHDT = c.getString(BIRTH_DT);
                String INGCD = c.getString(ING_CD);
                String Join_Cd = c.getString(JOIN_CD);
                String INID = c.getString(INPUT_ID);
                String INIP = c.getString(INPUT_IP);
                String INDATE = c.getString(INPUT_DATE);
                String UPID = c.getString(UPDATE_ID);
                String UPIP = c.getString(UPDATE_IP);
                String UPDATE = c.getString(UPDATE_DATE);
                HashMap<String, String> Club_Item = new HashMap<String, String>();
                Club_Item.put(CLUB_ID, id);
                Club_Item.put(STUDENT_ID, S_id);
                Club_Item.put(NM, name);
                Club_Item.put(MAJOR, MAJ);
                Club_Item.put(GRADE, GRA);
                Club_Item.put(GENDER_CD, GENDERCD);
                Club_Item.put(STAFF_CD, STAFFCD);
                Club_Item.put(PHONE_NO, PHONENO);
                Club_Item.put(POST_NO, POSTNO);
                Club_Item.put(ADDRESS, ADD);
                Club_Item.put(PER_INFO_AGG_YN, PER_INFO);
                Club_Item.put(EMAIL, EMA);
                Club_Item.put(JOIN_DT, JOINDT);
                Club_Item.put(BIRTH_DT, BIRTHDT);
                Club_Item.put(ING_CD, INGCD);
                Club_Item.put(JOIN_CD, Join_Cd);
                Club_Item.put(INPUT_ID, INID);
                Club_Item.put(INPUT_IP, INIP);
                Club_Item.put(INPUT_DATE, INDATE);
                Club_Item.put(UPDATE_ID, UPID);
                Club_Item.put(UPDATE_IP, UPIP);
                Club_Item.put(UPDATE_DATE, UPDATE);
                Club_Member_Item_list.add(Club_Item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Club_Member_Item_list;
    }

    public void ClearListData() {
        Club_Member_Item_list.clear();
    }

    public String getData(String uri) {
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            //StringBuilder sb = new StringBuilder();
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