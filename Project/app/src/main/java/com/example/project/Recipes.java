package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.manager.DbConect;
import com.example.project.adapter.RecipesAdapter;
import com.example.project.manager.SessionManager;
import com.example.project.model.RecipesDto;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Recipes extends AppCompatActivity {
    Button btnLogout, btnAdd;
    LinearLayout memberLayout, nonMemberLayout;
    ImageView imgFavorite;
    TextView toolbarName, userName;
    DrawerLayout drawerLayout;
    TabLayout tabLayout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("레시피");
        tabLayout = findViewById(R.id.tabLayout);

        imgFavorite = (ImageView) findViewById(R.id.favorite);

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

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RecipesInsert.class));
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

        int i = tabLayout.getSelectedTabPosition();
        clickTab((String) tabLayout.getTabAt(i).getContentDescription());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i = tabLayout.getSelectedTabPosition();
                clickTab((String) tabLayout.getTabAt(i).getContentDescription());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
        recreate();
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
        MainActivity.redirectActivity(this,Info.class);
    }

    public void ClickExit(View view){
        MainActivity.exit(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }

    private void clickTab(String base){
        ListView listView = findViewById(R.id.listView);
        RecipesAdapter adapter = new RecipesAdapter();
        listView.setAdapter(adapter);

        ArrayList<RecipesDto> dtos = new ArrayList<>();

        String result="";
        DbConect conect = new DbConect();
        try {
            result = conect.execute("selectBase","recipes",base).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(result.equals("fail")){
            Toast.makeText(Recipes.this, "검색결과가 없습니다", Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("error")){
            Toast.makeText(Recipes.this, "에러 발생", Toast.LENGTH_SHORT).show();
        }
        else{
            String[] results = result.split(",");
            for(int i=0; i<results.length;){
                RecipesDto dto = new RecipesDto();
                dto.setName(results[i++]);
                dto.setProof(results[i++]);
                dtos.add(dto);
            }
            for(RecipesDto dto : dtos){
                adapter.addItem(dto);
            }
        }
    }
}