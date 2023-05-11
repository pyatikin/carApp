package com.example.layouts.Repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.layouts.Models.AuthUser;

import java.util.ArrayList;
import java.util.List;

public class AuthRepository {
    List<AuthUser> users = new ArrayList<>();

    public AuthRepository(){
        AuthUser authUser = new AuthUser();
        authUser.setEmail("pyatkin@mail.ru");
        authUser.setPassword("123456");
        users.add(authUser);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public AuthUser getUserByEmail(String email){
        AuthUser  result = null;
        result = users.stream().filter(authUser -> authUser.getEmail().equals(email)).findFirst().get();
        return result;
    }
}
