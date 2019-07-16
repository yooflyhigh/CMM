package com.example.clubmanagement.ClubPage.Item;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.clubmanagement.System.Adapter.ItemListAdapter;
import com.example.clubmanagement.R;

public class Main_ItemStart extends AppCompatActivity {
    private String[] Item = {"컴퓨터", "A4", "키보드", "충전기"};
    public static int[] quantity = {3, 50, 3, 5};
    public static int select;
    private ItemListAdapter adapter_Item;
    private ListView listview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_form);

        adapter_Item = new ItemListAdapter();
        listview = (ListView) findViewById(R.id.item_listview);
        listview.setAdapter(adapter_Item);

        for (int i = 0; i < 4; i++) {
            adapter_Item.addVO(Item[i], quantity[i] + "개");
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select = position;
                Intent intent = new Intent(Main_ItemStart.this, ItemPopUp.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        adapter_Item = new ItemListAdapter();
        //listview = (ListView) findViewById(R.id.item_listview);
        listview.setAdapter(adapter_Item);
        for (int i = 0; i < 4; i++) {
            adapter_Item.addVO(Item[i], quantity[i] + "개");
        }
    }
}
