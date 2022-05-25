package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.adapter.BoardAdapter;
import com.example.project.adapter.FavoriteAdapter;
import com.example.project.manager.DbConect;
import com.example.project.manager.SessionManager;
import com.example.project.model.BoardDto;

import java.util.ArrayList;

public class Board extends AppCompatActivity {
    Button btnLogout, btnAdd;
    LinearLayout memberLayout, nonMemberLayout;
    TextView toolbarName, userName;
    DrawerLayout drawerLayout;
    ListView listView;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("게시판");
        btnAdd = findViewById(R.id.btnAdd);

        sessionManager = new SessionManager(getApplicationContext());

        select("selectBoard");

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
        if(sessionManager.getLogin()){
            btnAdd.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = btnAdd.getLayoutParams();
            params.height = 150;
            btnAdd.setLayoutParams(params);
        }else{
            btnAdd.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = btnAdd.getLayoutParams();
            params.height = 0;
            btnAdd.setLayoutParams(params);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BoardInsert.class));
                finish();
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
    public void ClickSignUp(View view){
        MainActivity.redirectActivity(this,SignUp.class);
    }

    public void ClickHome(View view){
        MainActivity.redirectActivity(this, MainActivity.class);
    }
    public void ClickSignIn(View view){
        MainActivity.redirectActivity(this,SignIn.class);
    }

    public void ClickBoard(View view){
        recreate();
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
    public void select(String ...strings){
        listView = findViewById(R.id.listView);
        BoardAdapter adapter = new BoardAdapter();
        listView.setAdapter(adapter);

        ArrayList<BoardDto> dtos = new ArrayList<>();
        String result="";
        DbConect conect = new DbConect();
        try {
            if(strings[0].equals("selectBoard")){
                result = conect.execute(strings[0],"board").get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result.equals("fail")){
            Toast.makeText(this, "검색 결과가 없습니다", Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("error")){
            Toast.makeText(this, "에러 발생", Toast.LENGTH_SHORT).show();
        }
        else{
            String[] title = result.split(",")[0].split(" ");
            String[] nickName = result.split(",")[1].split(" ");
            String[] date = result.split(",")[2].split(" ");
            String[] id = result.split(",")[3].split(" ");

            for(int i=0;i<title.length;i++){
                BoardDto dto = new BoardDto();
                dto.setId(Integer.parseInt(id[i]));
                dto.setTitle(title[i]);
                dto.setNickName(nickName[i]);
                dto.setDate(date[i]);
                adapter.addItem(dto);
            }
        }
    }
}