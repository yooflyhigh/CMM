package com.example.clubmanagement.ClubPage.Restaurant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.clubmanagement.System.Adapter.ListViewAdapter.Restaurant_ListAdapter;
import com.example.clubmanagement.R;

public class Main_Restaurant extends AppCompatActivity {

    private Restaurant_ListAdapter adapter_rest;
    private ListView listview;

    private String store[] = {"도스마스", "대암감자탕", "룡의부활", "병천순대국", "이삭토스트", "타박네", "쥬시", "한솥", "함께밥상", "GS25"};
    private String number[] = {"01041972767", "01039581075", "01099145304", "01089928043", "01041972767", "01039581075", "01099145304", "01089928043", "01041972767", "01039581075"};
    Button[] btn;
    private int Pos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_main);
        btn = new Button[10];
        for(int i =0;i< btn.length;i++){
            btn[i] = new Button(this);
            btn[i].setText(number[i]);
        }
        adapter_rest = new Restaurant_ListAdapter();
        listview = (ListView) findViewById(R.id.restaurant_info);
        listview.setAdapter(adapter_rest);

        DataInput();
    }
    public void Call(View v){
        Context c = v.getContext();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:01041972767"));
        intent.setData(Uri.parse("tel:01039581075"));
        intent.setData(Uri.parse("tel:01099145304"));
        intent.setData(Uri.parse("tel:01089928043"));
        intent.setData(Uri.parse("tel:01041972767"));
        intent.setData(Uri.parse("tel:01039581075"));
        intent.setData(Uri.parse("tel:01099145304"));
        intent.setData(Uri.parse("tel:01089928043"));
        intent.setData(Uri.parse("tel:01041972767"));
        intent.setData(Uri.parse("tel:01039581075"));
        try {
            c.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void DataInput() {
        listview.setAdapter(adapter_rest);
        for (int i = 0; i < store.length; i++) {
            adapter_rest.addVO(store[i],btn[i]);
        }
    }
}
