package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.dwecc_material_management_application.service.TransService;

import java.util.ArrayList;
import java.util.HashMap;

public class MaterialCheckList extends AppCompatActivity {
    //      id값에 따른 component들을 변수로 생성
    ImageButton backButton;
    ListView innerNameListView;
    ListView innerCodeListView;
    ListView innerQtyListview;

    //        arrayAdapter 생성
    ArrayAdapter<String> arrayNameAdapter;
    ArrayAdapter<String> arrayCodeAdapter;
    ArrayAdapter<String> arrayQtyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_check_list);

        //      id값에 따른 component들을 변수로 생성
        backButton = (ImageButton) findViewById(R.id.backToMainInMaterialCheckList);
        innerNameListView = (ListView) findViewById(R.id.innerNameListView);
        innerCodeListView = (ListView) findViewById(R.id.innerCodeListView);
        innerQtyListview = (ListView) findViewById(R.id.innerQtyListview);

//      각 버튼에 onClick listener setting
//      뒤로가기 버튼 onClick listener setting
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish(); //현재 activity 종료
            }
        });

//      검색 결과 전처리
//        넘겨줬던 검색 결과 받아오기
        String searchResult = getIntent().getStringExtra("materials");

//        결과로 사용할 arrayList 사용
        ArrayList<HashMap<String,String>> searchResultArrayList = new ArrayList<HashMap<String,String>>();

        Log.i("materialCheckList, searchResult: ",searchResult);

//        결과값을 형변환
        searchResultArrayList = TransService.chagneString2LineMaterial(searchResult);

//      결과 출력용 arrayList 생성(name, code, qty)
        ArrayList<String> nameArrayList = new ArrayList<String>();
        ArrayList<String> codeArrayList = new ArrayList<String>();
        ArrayList<String> qtyArrayList = new ArrayList<String>();

//        for을 활용하여 arrayList에 결과값 대입
        for(int i=0; i<searchResultArrayList.size();i++){
            nameArrayList.add(searchResultArrayList.get(i).get("name"));
            codeArrayList.add(searchResultArrayList.get(i).get("code"));
            qtyArrayList.add(searchResultArrayList.get(i).get("qty"));
        }

//      결과 출력 연결용 arrayAdapter 생성
        arrayNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameArrayList);
        arrayCodeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, codeArrayList);
        arrayQtyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, qtyArrayList);

//      검색 결과 ArrayAdapter 연결
        innerNameListView.setAdapter(arrayNameAdapter);
        innerCodeListView.setAdapter(arrayCodeAdapter);
        innerQtyListview.setAdapter(arrayQtyAdapter);
    }
}