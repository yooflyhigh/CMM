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
    String CLUB_ID;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_main);
        Intent intent = getIntent();
        CLUB_ID = intent.getExtras().getString("CLUB_ID");

        ImageView ClubImage = (ImageView) findViewById(R.id.imageView);
        ClubImage.setImageBitmap(ClubPoster.image.getBitmap());


        button1 = (Button) findViewById(R.id.MemberList);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_HomePage.this, Main_MemberList.class));
            }
        });

        button2 = (Button) findViewById(R.id.ApplyList);
        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_HomePage.this, Main_ApplyList.class);
                intent.putExtra("CLUB_ID", CLUB_ID);
                startActivity(intent);
            }
        });

        button3 = (Button) findViewById(R.id.ItemList);
        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_HomePage.this, Main_ItemList.class));
            }
        });

        button4 = (Button) findViewById(R.id.RestaurantList);
        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_HomePage.this, Main_Restaurant.class));
            }
        });

    }
}
