package com.example.week04list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lvFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] value = {"어머니 01084958754","아버지 01078963365",
                "할아버지 01078459888","할머니 01048887588","과일5",
                "과일6","과일7","과일8","과일9","과일10",
                "과일11","과일12","과일13","과일14","과일15",
                "과일16","과일17","과일18","과일19","과일20"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,value);

        lvFruit = (ListView) findViewById(R.id.lvFruit);
        lvFruit.setAdapter(adapter);
        lvFruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                item =item.substring(item.length()-11, item.length());
                System.out.println(item);
                Uri uri = Uri.parse("tel:"+item);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(callIntent);
            }
        });
    }
}