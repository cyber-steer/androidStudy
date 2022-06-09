package com.example.temp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Item> items = new ArrayList<>();
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            Context context = viewGroup.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout,viewGroup, false);
        }
        TextView title = view.findViewById(R.id.tvtitle);
        TextView genre = view.findViewById(R.id.tvGenre);

        Item item = items.get(i);

        title.setText(item.getTitle());
        genre.setText(item.getGenre());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext().getApplicationContext(), Content.class);
                intent.putExtra("title",item.getTitle());
                intent.putExtra("content",item.getContent());
                intent.putExtra("genre",item.getGenre());
                viewGroup.getContext().startActivity(intent);
            }
        });
        return view;
    }

    public void addItem(Item item){
        items.add(item);
    }
}
