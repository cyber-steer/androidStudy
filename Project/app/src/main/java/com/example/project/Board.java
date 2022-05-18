package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Board extends AppCompatActivity {
    TextView toolbarName;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("게시판");
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

    public void ClickHome(View view){
        MainActivity.redirectActivity(this, MainActivity.class);
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