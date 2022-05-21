package com.example.project.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences("AppKey",0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLogin(boolean login){
        editor.putBoolean("KEY_LOGIN",login);
        editor.commit();
    }
    public boolean getLogin(){
        return sharedPreferences.getBoolean("KEY_LOGIN",false);
    }
    public void setId(String id){
        editor.putString("KEY_ID",id);
        editor.commit();
    }
    public String getId(){
        return sharedPreferences.getString("KEY_ID","");
    }
    public void setPwd(String pwd){
        editor.putString("KEY_PWD",pwd);
        editor.commit();
    }
    public String getPwd(){
        return sharedPreferences.getString("KEY_PWD","");
    }
    public void setNickName(String nickName){
        editor.putString("KEY_NICKNAME",nickName);
        editor.commit();
    }
    public String getNickName(){
        return sharedPreferences.getString("KEY_NICKNAME","");
    }
}
