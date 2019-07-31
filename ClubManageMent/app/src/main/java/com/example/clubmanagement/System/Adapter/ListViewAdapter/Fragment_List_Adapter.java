package com.example.clubmanagement.System.Adapter.ListViewAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clubmanagement.System.ListVO.ListVO_Frg;
import com.example.clubmanagement.R;
import java.util.ArrayList;

public class Fragment_List_Adapter extends BaseAdapter {

    private ArrayList<ListVO_Frg> listVOFrg = new ArrayList<ListVO_Frg>();

    public Fragment_List_Adapter() {
    }
    @Override
    public int getCount() {
        return listVOFrg.size();
    }

    // ** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //postion = ListView의 위치      /   첫번째면 position = 0
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.frg_fragment_list_adapter, parent, false);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.img);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView Context = (TextView) convertView.findViewById(R.id.context);

        final ListVO_Frg listViewItem = listVOFrg.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        image.setImageDrawable(listViewItem.getImg());
        title.setText(listViewItem.getTitle());
        Context.setText(listViewItem.getContext());
        //Toast.makeText(context, (pos + 1) + "번째 리스트가 클릭되었습니다.", Toast.LENGTH_SHORT).show();

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return listVOFrg.get(position);
    }

    // 데이터값 넣어줌
    public void addVO(Drawable icon, String CLUB_ID, String title, String desc) {
        ListVO_Frg item = new ListVO_Frg();

        item.setImg(icon);
        item.setTitle(title);
        item.setContext(desc);
        item.setCLUB_ID(CLUB_ID);
        listVOFrg.add(item);
    }
}