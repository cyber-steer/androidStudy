package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.adapter.DbConect;
import com.example.project.adapter.RecipesAdapter;
import com.example.project.model.RecipesDto;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Recipes extends AppCompatActivity {
    ImageView imgFavorite;
    TextView toolbarName;
    DrawerLayout drawerLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("레시피");
        tabLayout = findViewById(R.id.tabLayout);

        imgFavorite = (ImageView) findViewById(R.id.favorite);


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
}