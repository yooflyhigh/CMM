package com.example.clubmanagement.ClubPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.clubmanagement.Item.Item_Activity;
import com.example.clubmanagement.R;
import com.example.clubmanagement.Restaurant.Restaurant_Activity;

import static java.lang.Thread.sleep;

public class Club_page extends AppCompatActivity {
    private int[] img = {R.drawable.hallym,R.drawable.light,R.drawable.eleven,R.drawable.noname,R.drawable.video,R.drawable.cloud,R.drawable.general,R.drawable.wings,R.drawable.heart,R.drawable.shop,R.drawable.triangle,R.drawable.waterdrop};
    //HashMap<String, String> Club_Item = new HashMap<String, String>();
    //ArrayList<HashMap<String, String>> Club_Item_list;
    Button button1;
    Button button4;
      Button button2;
      Button button3;
      /*
      Button button5;
      Button button6;
  */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubpage);
       // for (int i = 0; i < 4; i++) {
            ImageView imageView1 = (ImageView) findViewById(R.id.imageView) ;
            imageView1.setImageResource(img[ClubPositon.position]);
      //  }
        /*
        Club_Item_list = Club.Club_Item_list;
        CNT_Image_File ht;
        for (int i = 0; i < 4; i++) {
            Club_Item = Club_Item_list.get(i);
            if(Club_Item.get("CLUB_ID").equals(ClubPositon.position)) {
                String url = Club_Item.get("INTRO_FILE_NM");
                ht = new CNT_Image_File(url);
                ht.run();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ImageView imageView1 = (ImageView) findViewById(R.id.imageView) ;
                imageView1.setImageBitmap(ht.bitmap);
            }
        }
*/
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Club_page.this, Club_page_member.class));
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Club_page.this, Club_Apply_member.class));
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Club_page.this, Item_Activity.class));
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Club_page.this, Restaurant_Activity.class));
            }
        });
/*
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Club_page.this, ApplyActivity.class));
            }
        });

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Club_page.this, ApplyActivity.class));
            }
        });
*/
    }
}
