package com.example.project.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.project.R;
import com.example.project.manager.DbConect;
import com.example.project.model.RecipesDto;

import java.util.ArrayList;

public class FavoriteAdapter extends BaseAdapter {
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
        ImageView favorite = (ImageView) view.findViewById(R.id.favorite);
        RecipesDto dto = dtos.get(i);

        name.setText(dto.getName());
        proof.setText(dto.getProof()+"%");
        Drawable star =  ContextCompat.getDrawable(viewGroup.getContext(),R.drawable.ic_star);
        Drawable unstar =  ContextCompat.getDrawable(viewGroup.getContext(),R.drawable.ic_unstar);


        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbConect conect = new DbConect();
                String result="";
                try{
                } catch (Exception e){
                    e.printStackTrace();
                }
                if(result.equals("false")){
                    Toast.makeText(viewGroup.getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    public void addItem(RecipesDto dto){
        dtos.add(dto);
    }
}
