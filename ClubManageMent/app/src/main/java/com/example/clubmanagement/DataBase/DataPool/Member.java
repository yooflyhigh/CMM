package com.example.clubmanagement.DataBase.DataPool;

import com.example.clubmanagement.DataBase.DBConnect.CNT_Club_Member;

import java.util.ArrayList;
import java.util.HashMap;

public class Member {
    public static ArrayList<HashMap<String, String>> Club_Member_Item_list;
    public Member(){
        CNT_Club_Member ClubMember = new CNT_Club_Member();
        Club_Member_Item_list = ClubMember.Club_Member_Item_list;
    }
}
