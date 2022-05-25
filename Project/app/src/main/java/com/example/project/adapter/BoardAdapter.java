package com.example.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.BoardContent;
import com.example.project.BoardContentUser;
import com.example.project.R;
import com.example.project.manager.DbConect;
import com.example.project.manager.SessionManager;
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

        SessionManager sessionManager= new SessionManager(viewGroup.getContext());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbConect conect = new DbConect();
                String result = "";
                try{
                    result = conect.execute("selectId","board",dto.getId()+"").get();
                }catch (Exception e){
                    e.printStackTrace();
                }
                String nickname = result.split(",")[1];
                System.out.println("id : "+sessionManager.getNickName());
                System.out.println("id : "+nickname);
                if(sessionManager.getNickName().equals(nickname)){
                    Intent intent = new Intent(viewGroup.getContext(), BoardContentUser.class);
                    intent.putExtra("id",dto.getId());
                    viewGroup.getContext().startActivity(intent);
                }
                else{
                    Intent intent = new Intent(viewGroup.getContext(), BoardContent.class);
                    intent.putExtra("id",dto.getId());
                    viewGroup.getContext().startActivity(intent);
                }
            }
        });
        return view;
    }
    public void addItem(BoardDto dto){
        dtos.add(dto);
    }
}
