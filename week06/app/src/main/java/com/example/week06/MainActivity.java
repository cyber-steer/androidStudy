package com.example.week06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int img[] = {
                R.drawable.canada,R.drawable.france,R.drawable.korea,
                R.drawable.mexico,R.drawable.poland,R.drawable.saudi_arabia,
                R.drawable.canada,R.drawable.france,R.drawable.korea,
                R.drawable.mexico,R.drawable.poland,R.drawable.saudi_arabia,
                R.drawable.canada,R.drawable.france,R.drawable.korea,
                R.drawable.mexico,R.drawable.poland,R.drawable.saudi_arabia,
                R.drawable.canada,R.drawable.france,R.drawable.korea,
                R.drawable.mexico,R.drawable.poland,R.drawable.saudi_arabia,
                R.drawable.canada,R.drawable.france,R.drawable.korea,
                R.drawable.mexico,R.drawable.poland,R.drawable.saudi_arabia,
        };

        // adapter
        MyAdapter adapter = new MyAdapter(
                getApplicationContext(), // 현재 화면의 제어권자
                R.layout.row,
                img);

        // adapterView
        Gallery g = (Gallery)findViewById(R.id.gallery1);
        g.setAdapter(adapter);

        final ImageView iv = (ImageView)findViewById(R.id.imageView1);

        g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) { // 선택되었을 때 콜백메서드
                iv.setImageResource(img[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    int img[];
    LayoutInflater inf;

    public MyAdapter(Context context, int layout, int[] img) {
        this.context = context;
        this.layout = layout;
        this.img = img;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { // 보여줄 데이터의 총 개수 - 꼭 작성해야 함
        return img.length;
    }

    @Override
    public Object getItem(int position) { // 해당행의 데이터- 안해도 됨
        return null;
    }

    @Override
    public long getItemId(int position) { // 해당행의 유니크한 id - 안해도 됨
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 보여줄 해당행의 row xml 파일의 데이터를 셋팅해서 뷰를 완성하는 작업
        if (convertView == null) {
            convertView = inf.inflate(layout, null);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView1);
        iv.setImageResource(img[position]);
        return convertView;
    }
}