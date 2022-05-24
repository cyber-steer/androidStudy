package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.manager.DbConect;
import com.example.project.manager.SessionManager;

public class RecipeContent extends AppCompatActivity {
    Button btnLogout, btnBack;
    LinearLayout memberLayout, nonMemberLayout;
    ImageView favorite;
    TextView toolbarName, userName;
    TextView tvName, tvContent, tvProof, tvMeterial, tvVoluem, tvFormalities;
    DrawerLayout drawerLayout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_content);

        sessionManager = new SessionManager(getApplicationContext());

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        tvName = findViewById(R.id.tvName);
        tvContent = findViewById(R.id.tvContent);
        tvProof = findViewById(R.id.tvProof);
        tvMeterial = findViewById(R.id.tvMeterial);
        tvVoluem = findViewById(R.id.tvVoluem);
        tvFormalities = findViewById(R.id. tvFormalities);
        favorite = findViewById(R.id.favorite);

        String result = "";
        DbConect conect = new DbConect();
        try{
            result = conect.execute("selectRecipecontent","recipecontent",name).get();
            System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(result.equals("fail")){
            Toast.makeText(this, "불러올수없습니다", Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("error")){
            Toast.makeText(this, "에러 발생", Toast.LENGTH_SHORT).show();
        }
        else{
            String[] msg = result.split(",");
            String recipeName = msg[0];
            String recipeContent = msg[1];
            String recipeProof = msg[2];
            String recipeMeterial = msg[3].replace(" ", "\n");
            String recipeVoluem = msg[4].replace(" ","\n");
            String recipeFormalities = msg[5].replace(" ", "\n");

            tvName.setText(recipeName);
            tvContent.setText(recipeContent);
            tvProof.setText(recipeProof);
            tvMeterial.setText(recipeMeterial);
            tvVoluem.setText(recipeVoluem);
            tvFormalities.setText(recipeFormalities);

        }

        String userid = sessionManager.getId();
        Drawable star =  ContextCompat.getDrawable(this,R.drawable.ic_star);
        Drawable unstar =  ContextCompat.getDrawable(this,R.drawable.ic_unstar);
        conect = null;
        conect = new DbConect();
        result="";
        try{
            result = conect.execute("favoriteCheck","favorite",userid,name).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result.equals("true")){
            favorite.setImageDrawable(star);
        }
        else if(result.equals("false")){
            favorite.setImageDrawable(unstar);
        }
        else{
            favorite.setImageDrawable(null);
        }

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbConect conect = new DbConect();
                String result="";
                try{
                    result = conect.execute("favoriteCheck","favorite",userid,name).get();
                    if(result.equals("true")){
                        conect = null;
                        conect = new DbConect();
                        result = conect.execute("deleteFavorite","favorite",userid,name).get();
                        favorite.setImageDrawable(unstar);
                    }
                    else{
                        conect = null;
                        conect = new DbConect();
                        result = conect.execute("insertFavorite","favorite",userid,name).get();
                        favorite.setImageDrawable(star);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                if(result.equals("false")){
                    Toast.makeText(RecipeContent.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), Recipes.class));
                finish();
            }
        });


        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("레시피");



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
}