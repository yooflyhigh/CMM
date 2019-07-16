package com.example.clubmanagement.DataBase.DataPool;

import com.example.clubmanagement.DataBase.DBConnect.CNT_Student;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    public static ArrayList<HashMap<String, String>> Student_Item_list;

    public Student() {
        CNT_Student Student = new CNT_Student();
        Student_Item_list = Student.Student_Item_list;
    }
}
