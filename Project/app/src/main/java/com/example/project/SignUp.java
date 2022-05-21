package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.manager.DbConect;
import com.example.project.manager.SessionManager;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    Button btnSignUp;
    EditText et_id, et_nickName, et_pwd, et_pwdCheck;
    TextView toolbarName;
    DrawerLayout drawerLayout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("회원가입");

        sessionManager = new SessionManager(getApplicationContext());
        btnSignUp = findViewById(R.id.bt_signUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_id = findViewById(R.id.et_id);
                et_pwd = findViewById(R.id.et_password);
                et_pwdCheck = findViewById(R.id.et_passwordCheck);
                et_nickName = findViewById(R.id.et_nickName);
                String id = et_id.getText().toString();
                String nickName = et_nickName.getText().toString();
                String pwd = et_pwd.getText().toString();
                String pwdCheck = et_pwdCheck.getText().toString();

                id = id.trim();
                nickName = nickName.trim();
                pwd = pwd.trim();
                pwdCheck = pwdCheck.trim();
                
                if(id.equals("")){
                    et_id.setError("아이디는 필수입니다");
                }
                if(nickName.equals("")){
                    et_nickName.setError("닉네임은 필수입니다");
                }
                if(pwd.equals("")){
                    et_pwd.setError("비밀번호는 필수입니다");
                }
                String constraint = "pass";
                if(!Pattern.matches("^[a-zA-Z0-9]*$",id)){
                    et_id.setError("잘못된 아이디 형식입니다");
                    constraint = "failId";
                }
                if(!Pattern.matches("^[a-zA-Z0-9가-힣]*$",nickName)){
                    et_nickName.setError("잘못된 닉네임 형식입니다");
                    constraint = "failNickName";
                }
                if(!Pattern.matches("^[a-zA-Z0-9]*$",pwd)){
                    et_nickName.setError("잘못된 비밀번호 형식입니다");
                    constraint = "failPwd";
                }
                if(!pwd.equals(pwdCheck)){
                    et_pwdCheck.setError("비밀번호가 다릅니다");
                    constraint="failPwdCheck";
                }

                if(constraint.equals("pass")){
                    DbConect conect = new DbConect();
                    String result="";

                    try{
                        result = conect.execute("insertUser","user",id,nickName,pwd).get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    
                    if(result.equals("true")){
                        startActivity(new Intent(getApplicationContext(),SignIn.class));
                        finish();
                    }
                    else if(result.equals("fail")){
                        Toast.makeText(SignUp.this, "저장하지 못했습니다", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SignUp.this, result+"", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    switch (constraint){
                        case "failId":
                            et_id.setText("");
                            break;
                        case "failNickName":
                            et_nickName.setText("");
                            break;
                        case "failPwd":
                            et_pwd.setText("");
                            break;
                        case "failPwdCheck":
                            et_pwdCheck.setText("");
                            break;
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
        MainActivity.redirectActivity(this,SignIn.class);
    }
    public void ClickSignUp(View view){
        recreate();
    }

    public void ClickBoard(View view){
        MainActivity.redirectActivity(this, Board.class);
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