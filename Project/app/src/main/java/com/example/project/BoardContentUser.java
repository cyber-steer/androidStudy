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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.manager.DbConect;
import com.example.project.manager.SessionManager;

public class BoardContentUser extends AppCompatActivity {
    Button btnLogout, btnBack, btnUpdate;
    LinearLayout memberLayout, nonMemberLayout, toolbarLayout;
    TextView toolbarName, userName, tvNickName, tvDate;
    EditText  tvTitle, tvContent;
    DrawerLayout drawerLayout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_content_user);


        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("게시판");
        toolbarLayout = findViewById(R.id.toolbarLayout);
        toolbarLayout.removeViewAt(3);
        toolbarLayout.removeViewAt(2);
        toolbarName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        sessionManager = new SessionManager(getApplicationContext());

        userName = findViewById(R.id.nickName);
        memberLayout = findViewById(R.id.memberLayout);
        nonMemberLayout = findViewById(R.id.nonMemberLayout);
        if(sessionManager.getLogin()){
            memberLayout.setVisibility(View.VISIBLE);
            nonMemberLayout.setVisibility(View.INVISIBLE);
            btnLogout = findViewById(R.id.btnLogout);
            userName.setText(sessionManager.getNickName());
        }else{
            memberLayout.setVisibility(View.INVISIBLE);
            nonMemberLayout.setVisibility(View.VISIBLE);
        }

        tvTitle = findViewById(R.id.tvTitle);
        tvNickName = findViewById(R.id.tvNickName);
        tvDate = findViewById(R.id.tvDate);
        tvContent = findViewById(R.id.tvContent);

        Intent intent = getIntent();
        int boardId = intent.getIntExtra("id", -1);

        String result="";
        DbConect conect = new DbConect();
        try{
            result = conect.execute("selectId","board",boardId+"").get();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result.equals("fail")){
            Toast.makeText(this, "검색 실패", Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("error")){
            Toast.makeText(this, "에러 발생", Toast.LENGTH_SHORT).show();
        }
        else{
            String title = result.split(",")[0];
            String nickname = result.split(",")[1];
            String date = result.split(",")[2].replace("/"," ");
            String content = result.split(",")[3];

            tvTitle.setText(title);
            tvNickName.setText(nickname);
            tvDate.setText(date);
            tvContent.setText(content);
        }

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Board.class));
            }
        });
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = tvTitle.getText().toString().trim();
                String content = tvContent.getText().toString().trim();

                String result="";
                DbConect conect = new DbConect();
                try {
                    result = conect.execute("updateBoard","board",boardId+"",title,content).get();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(result.equals("fail")){
                    Toast.makeText(BoardContentUser.this, "수정 실패", Toast.LENGTH_SHORT).show();
                }
                else if(result.equals("error")){
                    Toast.makeText(BoardContentUser.this, "에러발생", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(getApplicationContext(),Board.class));
                    finish();
                }
            }
        });
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("로그아웃");
                builder.setMessage("정말 로그아웃하시겠습니까?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sessionManager.setLogin(false);
                        sessionManager.setId("");
                        sessionManager.setPwd("");
                        sessionManager.setNickName("");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
        });
    }
    public void ClickSignIn(View view){
        MainActivity.redirectActivity(this,SignIn.class);
    }
    public void ClickSignUp(View view){
        MainActivity.redirectActivity(this,SignUp.class);
    }

    public void ClickHome(View view){
        MainActivity.redirectActivity(this, MainActivity.class);
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