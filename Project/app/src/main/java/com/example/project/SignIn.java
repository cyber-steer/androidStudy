package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.manager.DbConect;
import com.example.project.manager.SessionManager;

public class SignIn extends AppCompatActivity {
    Button btnLogin;
    EditText et_id, et_pwd;
    TextView toolbarName;
    DrawerLayout drawerLayout;
    SessionManager sessionManager;
    LinearLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("로그인");
        toolbarLayout = findViewById(R.id.toolbarLayout);
        toolbarLayout.removeViewAt(3);
        toolbarLayout.removeViewAt(2);
        toolbarName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        sessionManager = new SessionManager(getApplicationContext());

        btnLogin = findViewById(R.id.bt_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_id = findViewById(R.id.et_Id);
                et_pwd = findViewById(R.id.et_pwd);

                String id = et_id.getText().toString();
                String pwd = et_pwd.getText().toString();

                id = id.trim();
                pwd = pwd.trim();
                if(id.equals("") || pwd.equals("")){
                    if(id.equals("")){
                        et_id.setError("아이디를 입력해주세요");
                    }
                    if(pwd.equals("")) {
                        et_pwd.setError("비밀번호를 입력해주세요");
                    }
                }
                else{
                    DbConect conect = new DbConect();
                    String result="";
                    try{
                        result = conect.execute("userCheck","user",id,pwd).get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println("result : "+result);
                    if(result.equals("!false!")){
                        Toast.makeText(SignIn.this, "아이디나 비밀번호가 틀립니다", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        sessionManager.setLogin(true);
                        sessionManager.setId(id);
                        sessionManager.setPwd(pwd);
                        sessionManager.setNickName(result);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                    
                }

            }
        });
    }
    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickRecipes(View view){
        MainActivity.redirectActivity(this,Recipes.class);
    }
    public void ClickFavorite(View view){
        if(sessionManager.getLogin()){
            MainActivity.redirectActivity(this,Favorite.class);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("로그인");
            builder.setMessage("즐겨찾기는 로그인을 해야 이용가능합니다 로그인 하겠습니까?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void ClickHome(View view){
        MainActivity.redirectActivity(this, MainActivity.class);
    }
    public void ClickSignIn(View view){
        recreate();
    }
    public void ClickSignUp(View view){
        MainActivity.redirectActivity(this,SignUp.class);
    }

    public void ClickBoard(View view){
        MainActivity.redirectActivity(this,Board.class);
    }
    public void ClickInfo(View view){
        MainActivity.redirectActivity(this, Info.class);
    }

    public void ClickExit(View view){
        MainActivity.exit(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
}