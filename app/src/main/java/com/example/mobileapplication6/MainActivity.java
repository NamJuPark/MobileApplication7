package com.example.mobileapplication6;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    final int _REQ = 100;
    final int RESULT_STORE = 0;
    final int RESULT_CANCLED = 50;

    ListView lv;
    ArrayList<Store> data_store = new ArrayList<Store>();
    list_item_Adapter adapter;
    Intent intent;
    EditText eSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListView();
        eSearch= (EditText)findViewById(R.id.eSearch);
        eSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();
                if (search.length() > 0) {
                   lv.setFilterText(search);
                }
                else {lv.clearTextFilter();}
        }});
    }
    public void setListView(){
        lv = (ListView)findViewById(R.id.listview);
        adapter = new list_item_Adapter(this, data_store);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("store_main3", data_store.get(i));
                startActivity(intent);
            }
        });

    }

    public void onClick(View v){
        if(v.getId() == R.id.addStore){//맛집추가 - Main2Activity로 intent해서 data받기
            intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(intent, _REQ);
        }
        else if( v.getId() == R.id.bOrderName){
            Collections.sort(data_store, orderName);
            adapter.notifyDataSetChanged();

        }
        else if(v.getId() == R.id.bOrderKind){
            Collections.sort(data_store, orderKind);
            adapter.notifyDataSetChanged();

        }
        else if(v.getId() == R.id.bChoose){
            Button bChoose = (Button)findViewById(R.id.bChoose);
            if(bChoose.getText().toString().equals("선택")){
                bChoose.setText("삭제");
                adapter.visibleCheckBox();
            }
            else if(bChoose.getText().toString().equals("삭제")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("삭제확인")
                        .setIcon(R.drawable.plate)
                        .setMessage("선택한 맛집을 삭제 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.remove();
                                Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                bChoose.setText("선택");
                adapter.invisibleCheckBox();
             }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data_) {
        super.onActivityResult(requestCode, resultCode, data_);
        if (requestCode == _REQ) {
            if (resultCode == RESULT_STORE) {
                Store store = data_.getParcelableExtra("store");
                adapter.add(store);
                adapter.notifyDataSetChanged();
            } else if (resultCode == RESULT_CANCLED){
            }
        }
    }

    Comparator<Store> orderName  = new Comparator<Store>() {
        @Override
        public int compare(Store store, Store t1) {
            String s = store.name;
            String s1 = t1.name;
            return  s.compareToIgnoreCase(s1);
        }
    };

    Comparator<Store> orderKind  = new Comparator<Store>() {
        @Override
        public int compare(Store store, Store t1) {
            if(store.num_category < t1.num_category) return -1;
            else if(store.num_category == t1.num_category) return 0;
            else return 1;
        }
    };


}
