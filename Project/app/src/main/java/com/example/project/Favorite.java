package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.adapter.DbConect;
import com.example.project.adapter.FavoriteAdapter;
import com.example.project.model.RecipesDto;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {
    ListView listView;
    TextView toolbarName;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("즐겨찾기");

        listView = findViewById(R.id.listView);
        FavoriteAdapter adapter = new FavoriteAdapter();
        listView.setAdapter(adapter);

        ArrayList<RecipesDto> dtos = new ArrayList<>();
        String result="";
        DbConect conect = new DbConect();
        try {
            result = conect.execute("selectFavorite","recipes").get();
        } catch(Exception e){
            e.printStackTrace();
        }
        if(result.equals("fail")){
            Toast.makeText(this, "즐겨찾기한 레시피가 없습니다", Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("error")){
            Toast.makeText(this, "에러 벌생", Toast.LENGTH_SHORT).show();
        }
        else{
            String[] results = result.split(",");
            for(int i=0; i<results.length;){
                RecipesDto dto = new RecipesDto();
                dto.setName(results[i++]);
                dto.setProof(results[i++]);
                String bool = results[i++];
                boolean favorite = bool.equals("true") ? true:false;
                dto.setFavorite(favorite);
                dtos.add(dto);
            }
            for(RecipesDto dto : dtos){
                adapter.addItem(dto);
            }
        }

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
        MainActivity.redirectActivity(this,Favorite.class);
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