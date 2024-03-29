package com.example.clubmanagement.DataBase.DBConnect;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Thread.sleep;

public class CNT_JoinResponse {
    private URL url;
    private String result;

    private String readStream(InputStream in) throws IOException {
        StringBuilder jsonHtml = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

        String line = null;
        while((line = reader.readLine()) != null)
            jsonHtml.append(line);

        reader.close();
        return jsonHtml.toString();
    }

    public String CNT_JoinResponse(final String CLUB_ID,final String STUDENT_ID, final String Order) {
        new Thread() {
            public void run() {
                try {
                    url = new URL("http://210.115.230.212/Member_Join_Response.php");
                    String postData = "CLUB_ID=" + CLUB_ID + "&"
                            + "STUDENT_ID=" + STUDENT_ID + "&"
                            + "Order=" + Order;

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(postData.getBytes("UTF-8"));

                    outputStream.flush();
                    outputStream.close();
                    result = readStream(conn.getInputStream());
                    conn.disconnect();
                }
                catch (Exception e) {
                    Log.i("PHPRequest", "request was failed.");
                }
            }
        }.start();
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
