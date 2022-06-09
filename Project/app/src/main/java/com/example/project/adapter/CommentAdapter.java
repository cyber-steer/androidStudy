package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.CommentDto;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {
    ArrayList<CommentDto> dtos = new ArrayList<>();
    @Override
    public int getCount() {
        return dtos.size();
    }

    @Override
    public Object getItem(int i) {
        return dtos.get(i);
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
            view = inflater.inflate(R.layout.comment, viewGroup, false);
        }
        TextView nickName = view.findViewById(R.id.tvNickName);
        TextView content = view.findViewById(R.id.tvContent);
        TextView date = view.findViewById(R.id.tvDate);

        CommentDto dto = dtos.get(i);

        nickName.setText(dto.getNickName());
        content.setText(dto.getContent());
        date.setText(dto.getDate());

        return view;
    }
    public void addItem(CommentDto dto){
        dtos.add(dto);
    }
}
