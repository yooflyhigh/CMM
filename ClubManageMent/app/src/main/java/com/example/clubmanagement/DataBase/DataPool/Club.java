package com.example.clubmanagement.DataBase.DataPool;

import com.example.clubmanagement.DataBase.DBConnect.CNT_Club;

import java.util.ArrayList;
import java.util.HashMap;

public class Club{
    public static ArrayList<HashMap<String, String>> Club_Item_list;
    public Club(){
        CNT_Club ClubD = new CNT_Club();
        Club_Item_list = ClubD.Club_Item_list;
    }
}
