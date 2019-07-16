package com.example.clubmanagement.DataBase.DataPool;

import com.example.clubmanagement.DataBase.DBConnect.CNT_Restaurant;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
    public static ArrayList<HashMap<String, String>> Restaurant_list;
    public Restaurant(){
        CNT_Restaurant Club_Restaurant = new CNT_Restaurant();
        Restaurant_list = Club_Restaurant.Restaurant_list;
    }
}
