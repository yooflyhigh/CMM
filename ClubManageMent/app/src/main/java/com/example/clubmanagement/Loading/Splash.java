package com.example.clubmanagement.Loading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.clubmanagement.DataBase.DataPool.Club;
import com.example.clubmanagement.DataBase.DataPool.Member;
import com.example.clubmanagement.DataBase.DataPool.Restaurant;
import com.example.clubmanagement.DataBase.DataPool.Student;
import com.example.clubmanagement.Login.Login_View.LoginActivity;

import static java.lang.Thread.sleep;

public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            new Club();
            new Member();
            new Restaurant();
            new Student();
            sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));
    }
}
