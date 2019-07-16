package com.example.clubmanagement.ClubPage.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.clubmanagement.ClubPage.ApplyList.Main_ApplyList;
import com.example.clubmanagement.ClubPage.MemberList.Main_MemberList;
import com.example.clubmanagement.ClubPage.Item.Main_ItemList;
import com.example.clubmanagement.R;
import com.example.clubmanagement.ClubPage.Restaurant.Main_Restaurant;

import static java.lang.Thread.sleep;

public class Main_HomePage extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_main);
        ImageView ClubImage = (ImageView) findViewById(R.id.imageView);
        ClubImage.setImageBitmap(ClubPoster.image.getBitmap());

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_HomePage.this, Main_MemberList.class));
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_HomePage.this, Main_ApplyList.class));
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_HomePage.this, Main_ItemList.class));
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_HomePage.this, Main_Restaurant.class));
            }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Main_HomePage.this, JoinPopUp.class));
            }
        });

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Main_HomePage.this, JoinPopUp.class));
            }
        });
    }
}
