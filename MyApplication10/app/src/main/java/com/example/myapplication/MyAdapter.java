package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
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
        if (view == null) {
            Context context = viewGroup.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout, viewGroup, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.listImage);
        TextView name = (TextView) view.findViewById(R.id.listName);
        TextView count = (TextView) view.findViewById(R.id.listCount);

        Item item = items.get(i);

        imageView.setImageResource(item.getImgSorce());
        name.setText(item.getImgName());
        count.setText(item.getCount()+"");

        return view;
    }
    public void addItem (int imgSorce, int count, String imgName) {
        Item item = new Item(imgSorce,count,imgName );
        items.add(item);

    }
}
