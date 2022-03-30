package com.example.week05;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems = new ArrayList<ListViewItem>();



    @Override
    public int getCount() { return listViewItems.size(); }

    @Override
    public Object getItem(int i) {
        return listViewItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();
        System.out.println("=========================================");
        System.out.println("i : "+i);
        System.out.println("view : "+view);
        System.out.println("viewGroup : "+viewGroup);
        System.out.println("=========================================");


        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem, viewGroup, false);
        }

        ImageView iconImageView = (ImageView) view.findViewById(R.id.imageView);
        TextView titleTextView = (TextView) view.findViewById(R.id.textView1);
        TextView descTextView = (TextView) view.findViewById(R.id.textView2);

        ListViewItem listViewItem = listViewItems.get(pos);

        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());
        descTextView.setText(listViewItem.getDescription());

        return view;
    }

    public void addItem(Drawable icon, String tile, String desc){
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(tile);
        item.setDescription(desc);

        listViewItems.add(item);
    }
}
