package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.BoardDto;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {
    ArrayList<BoardDto> dtos = new ArrayList<>();
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
            view = inflater.inflate(R.layout.board_item, viewGroup, false);
        }
        TextView title = view.findViewById(R.id.tvTitle);
        TextView nickname = view.findViewById(R.id.tvNickName);
        TextView date = view.findViewById(R.id.tvDate);

        BoardDto dto = dtos.get(i);

        title.setText(dto.getTitle());
        nickname.setText(dto.getNickName());
        date.setText(dto.getDate().replace("/"," "));

        return view;
    }
    public void addItem(BoardDto dto){
        dtos.add(dto);
    }
}
