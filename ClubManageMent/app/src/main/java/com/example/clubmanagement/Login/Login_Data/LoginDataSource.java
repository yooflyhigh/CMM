package com.example.clubmanagement.Login.Login_Data;

import android.widget.ImageView;

import com.example.clubmanagement.Profile.UserID;
import com.example.clubmanagement.Login.Login_Data.model.LoggedInUser;
import com.example.clubmanagement.Profile.UserImage;
import com.example.clubmanagement.R;

import java.io.IOException;
import java.util.HashMap;

import static com.example.clubmanagement.DataBase.DataPool.Student.Student_Item_list;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    public Result<LoggedInUser> login(String username, String password) {
        HashMap<String, String> User;
        try {
            for (int i = 0; i < Student_Item_list.size(); i++) {
                User = Student_Item_list.get(i);
                String STUDENT_ID = User.get("STUDENT_ID");
                String PASSWORD= User.get("PASSWORD");
                if(STUDENT_ID.equals(username) && PASSWORD.equals(password)) {
                    UserID.UserID = username;
                    LoggedInUser RealUser =
                            new LoggedInUser(
                                    java.util.UUID.randomUUID().toString(),
                                    STUDENT_ID);
                    return  new Result.Success<>(RealUser);
                }
            }
            return new Result.Error(new IOException("Error logging in"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
