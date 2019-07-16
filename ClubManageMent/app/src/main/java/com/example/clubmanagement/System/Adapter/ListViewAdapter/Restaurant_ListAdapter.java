package com.example.clubmanagement.System.Adapter.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.clubmanagement.System.ListVO.ListVO_Restaurant;
import com.example.clubmanagement.R;
import java.util.ArrayList;

public class Restaurant_ListAdapter extends BaseAdapter {
    private ArrayList<ListVO_Restaurant> listVO_Res = new ArrayList<ListVO_Restaurant>();

    @Override
    public int getCount() {
        return listVO_Res.size();
    }

    // ** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.restaurant_list_adapter, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.store);
        Button number = (Button) convertView.findViewById(R.id.number);

        final ListVO_Restaurant listViewItem = listVO_Res.get(position);

        name.setText(listViewItem.getStore());
        number.setText(listViewItem.getNumber().getText());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return listVO_Res.get(position);
    }

    // 데이터값 넣어줌
    public void addVO(String name, Button number) {

        ListVO_Restaurant item = new ListVO_Restaurant();

        //  item.setImg(icon);
        item.setStore(name);
        item.setNUmber(number);
        listVO_Res.add(item);
    }
}