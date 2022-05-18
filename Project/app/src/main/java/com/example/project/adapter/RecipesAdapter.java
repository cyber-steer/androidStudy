package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.RecipesDto;

import java.util.ArrayList;

public class RecipesAdapter extends BaseAdapter {
    ArrayList<RecipesDto> dtos = new ArrayList<>();
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
        if (view == null){
            Context context =viewGroup.getContext();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.recipes_item, viewGroup,false);
        }
        TextView name = (TextView) view.findViewById(R.id.tvName);
        TextView proof = (TextView) view.findViewById(R.id.tvProof);

        RecipesDto dto = dtos.get(i);

        name.setText(dto.getName());
        proof.setText(dto.getProof()+"%");

        return view;
    }
    public void addItem(RecipesDto dto){
        dtos.add(dto);
    }
}
