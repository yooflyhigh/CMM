package com.example.clubmanagement.System.Adapter.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.clubmanagement.System.ListVO.ListVO_Item;
import com.example.clubmanagement.R;

import java.util.ArrayList;

public class Item_ListAdapter extends BaseAdapter {
    private ArrayList<ListVO_Item> listVO_Item = new ArrayList<ListVO_Item>();

    @Override
    public int getCount() {return listVO_Item.size();}

    // ** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_adapter, parent, false);
        }

        TextView Item = (TextView) convertView.findViewById(R.id.item);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);

        final ListVO_Item listViewItem = listVO_Item.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        Item.setText(listViewItem.getItem());
        quantity.setText(listViewItem.getQuantity());
        //Toast.makeText(context, (pos + 1) + "번째 리스트가 클릭되었습니다.", Toast.LENGTH_SHORT).show();

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {return listVO_Item.get(position);}

    // 데이터값 넣어줌
    public void addVO(String Item, String number) {
        ListVO_Item item = new ListVO_Item();
        item.setItem(Item);
        item.setQuantity(number);
        listVO_Item.add(item);
    }
}