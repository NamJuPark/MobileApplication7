package com.example.mobileapplication6;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 박남주 on 2017-04-13.
 */

public class list_item_Adapter extends BaseAdapter implements Filterable {
    Context c;
    ArrayList<Store> data_store;
    ArrayList<Store> data_filter;
    Filter listFilter;
    CheckBox checkBox;


    public list_item_Adapter(Context c, ArrayList<Store> data_store) {
        this.c = c;
        this.data_store = data_store;
        this.data_filter = data_store;
    }

    @Override
    public int getCount() {return data_filter.size();}

    @Override
    public Object getItem(int i) {return data_filter.get(i);}

    @Override
    public long getItemId(int i) {return i;}

    public CheckBox getCheckBox(int i){
        return data_filter.get(i).checkBox;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(c);
        if (view == null) {
            view = inflater.inflate((R.layout.layout_list_item1), null);
        }
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvTel = (TextView) view.findViewById(R.id.tvTel);
        ImageView ivMenu = (ImageView) view.findViewById(R.id.ivMenu);
        CheckBox c1 = (CheckBox)view.findViewById(R.id.checkBox);

        Store one = data_filter.get(i);
        one.checkBox = c1;

        tvName.setText(data_filter.get(i).name);
        tvTel.setText(data_filter.get(i).tel);
        if (data_filter.get(i).num_category == 1) {
            ivMenu.setImageResource(R.drawable.food1);
        } else if (data_filter.get(i).num_category == 2) {
            ivMenu.setImageResource(R.drawable.pizza);
        } else if (data_filter.get(i).num_category == 3) {
            ivMenu.setImageResource(R.drawable.hamburger);
        }
        return view;
    }

    public void add(Store store) {data_store.add(store);}

    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }
        return listFilter ;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = data_store ;
                results.count = data_store.size() ;
            } else {
                ArrayList<Store> itemList = new ArrayList<Store>() ;

                for (Store item : data_store) {
                    if (item.name.toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        itemList.add(item);
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data_filter = (ArrayList<Store>) results.values ;
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }

    public void visibleCheckBox(){
        for(int i = 0; i < data_filter.size(); i++){
            data_filter.get(i).checkBox.setVisibility(View.VISIBLE);
        }
        this.notifyDataSetChanged();
    }

    public void invisibleCheckBox(){
        for(int i = 0; i < data_filter.size(); i++){
            data_filter.get(i).checkBox.setVisibility(View.INVISIBLE);
        }
        this.notifyDataSetChanged();
    }
    public void remove(){
        for(int i = 0; i < data_filter.size(); i++){
            if(data_filter.get(i).checkBox.isChecked()){
                data_filter.remove(i);
            }
        }
        this.notifyDataSetChanged();
    }
}
