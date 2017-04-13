package com.example.mobileapplication6;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int _REQ = 100;
    final int RESULT_STORE = 0;
    final int RESULT_CANCLED = 50;

    ListView lv;
    ArrayList<Store> data_store = new ArrayList<Store>();
    list_item_Adapter adapter;
    Store store;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListView();


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
//
//        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int num, long l) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//
//                builder.setTitle("삭제확인")
//                        .setIcon(R.drawable.plate)
//                        .setMessage("선택한 맛집을 삭제 하시겠습니까?")
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                data_store.remove(num);
//                                adapter.notifyDataSetChanged();
//                                Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT)
//                                .show();
//                            }
//                        })
//                        .setNegativeButton("취소", null)
//                        .show();
//
//                return true;
//            }
//        });
    }

    public void onClick(View v){
        if(v.getId() == R.id.addStore){//맛집추가 - Main2Activity로 intent해서 data받기
            intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(intent, _REQ);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data_) {
        super.onActivityResult(requestCode, resultCode, data_);
        if(requestCode == _REQ){
            if(resultCode ==  RESULT_STORE){
                Store store = data_.getParcelableExtra("store");
                adapter.add(store);
                adapter.notifyDataSetChanged();
            }
            else if(resultCode == RESULT_CANCLED){
            }
        }
    }


}
