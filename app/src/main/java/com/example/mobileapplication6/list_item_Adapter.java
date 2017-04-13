package com.example.mobileapplication6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 박남주 on 2017-04-13.
 */

public class list_item_Adapter extends BaseAdapter{
    Context c;
    ArrayList<Store> data_store;

    public list_item_Adapter(Context c, ArrayList<Store> data_store){
        this.c = c;
        this.data_store = data_store;
    }

    @Override
    public int getCount() {
        return data_store.size();
    }

    @Override
    public Object getItem(int i) {
        return data_store.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(c);
        if(view == null){
            view = inflater.inflate((R.layout.layout_list_item1),null);
        }
        TextView tvName = (TextView)view.findViewById(R.id.tvName);
        TextView tvTel = (TextView)view.findViewById(R.id.tvTel);
        ImageView ivMenu = (ImageView)view.findViewById(R.id.ivMenu);

        tvName.setText(data_store.get(i).name);
        tvTel.setText(data_store.get(i).tel);
        if(data_store.get(i).num_category == 1){
            ivMenu.setImageResource(R.drawable.food1);
        }
        else if(data_store.get(i).num_category == 2){
            ivMenu.setImageResource(R.drawable.pizza);
        }
        else if(data_store.get(i).num_category == 3){
            ivMenu.setImageResource(R.drawable.hamburger);
        }
        return view;
    }

    public void add(Store store) {
        data_store.add(store);
    }
}
