package com.example.clubmanagement.DataBase;

import com.example.clubmanagement.DataBase.DataPool.Club;
import com.example.clubmanagement.DataBase.DataPool.Member;
import com.example.clubmanagement.DataBase.DataPool.Restaurant;
import com.example.clubmanagement.DataBase.DataPool.Student;

public class DBRefresh {
    public static void Refresh(){
        new Student();
        new Club();
        new Member();
        new Restaurant();
    }
}
