package com.example.clubmanagement.System.Adapter.ListViewAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clubmanagement.System.ListVO.ListVO_Apply;
import com.example.clubmanagement.R;

import java.util.ArrayList;

public class ApplyList_ListAdapter extends BaseAdapter {

    private ArrayList<ListVO_Apply> listVO_Apply = new ArrayList<ListVO_Apply>();

    @Override
    public int getCount() {
        return listVO_Apply.size();
    }

    // ** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 첫번째면 position = 0
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.applylist_list_adapter, parent, false);
        }

        ImageView Apply_img = (ImageView) convertView.findViewById(R.id.Apply_img);
        TextView Apply_Major = (TextView) convertView.findViewById(R.id.Apply_Major);
        TextView Apply_Student_ID = (TextView) convertView.findViewById(R.id.Apply_Student_ID);
        TextView Apply_Grade = (TextView) convertView.findViewById(R.id.Apply_Grade);
        TextView Apply_NM = (TextView) convertView.findViewById(R.id.Apply_NM);
        TextView Apply_Gender = (TextView) convertView.findViewById(R.id.Apply_Gender);
        TextView Apply_Phone_No = (TextView) convertView.findViewById(R.id.Apply_Phone_No);

        final ListVO_Apply listViewItem = listVO_Apply.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        Apply_img.setImageDrawable(listViewItem.getApply_Img());
        Apply_Major.setText(listViewItem.getMAJOR());
        Apply_Student_ID.setText(listViewItem.getSTUDENT_ID());
        Apply_Grade.setText(listViewItem.getGRADE());
        Apply_NM.setText(listViewItem.getNM());
        Apply_Gender.setText(listViewItem.getGENDER_CD());
        Apply_Phone_No.setText(listViewItem.getPHONE_NO());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listVO_Apply.get(position);
    }

    // 데이터값 넣어줌
    public void addVO(Drawable Apply_Img, String Apply_Major, String Apply_Student_ID, String Apply_Grade,String Apply_NM,String Apply_Gender,String Apply_Phone_No) {
        ListVO_Apply item = new ListVO_Apply();

        item.setApply_Img(Apply_Img);
        item.setMAJOR(Apply_Major);
        item.setSTUDENT_ID(Apply_Student_ID);
        item.setGRADE(Apply_Grade);
        item.setNM(Apply_NM);
        item.setGENDER_CD(Apply_Gender);
        item.setPHONE_NO(Apply_Phone_No);

        listVO_Apply.add(item);
    }
}