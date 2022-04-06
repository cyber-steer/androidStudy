package com.example.week05tel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelListAdapter extends BaseAdapter {

    ArrayList<TelList> telListArrayList = new ArrayList<TelList>();

    @Override
    public int getCount() {
        return telListArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return telListArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.telitem, viewGroup, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView name = (TextView) view.findViewById(R.id.txtName);
        TextView tel = (TextView) view.findViewById(R.id.txtTel);

        TelList telList = telListArrayList.get(i);

        imageView.setImageDrawable(telList.getImage());
        name.setText(telList.getName());
        tel.setText(telList.getTel());

        return view;
    }

    public void addItem(Drawable image, String name, String tel){
        TelList telList = new TelList();

        telList.setImage(image);
        telList.setName(name);
        telList.setTel(tel);

        telListArrayList.add(telList);
    }
}
