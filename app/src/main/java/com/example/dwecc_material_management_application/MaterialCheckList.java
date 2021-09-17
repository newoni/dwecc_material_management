package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_check_list);

//      id값에 따른 component들을 변수로 생성
        ImageButton backButton = (ImageButton) findViewById(R.id.backToMain);
        ListView lineMaterialListView = (ListView) findViewById(R.id.lineMaterialListView);
        RecyclerView lineMaterialComponentRecyclerView = (RecyclerView) findViewById(R.id.lineMaterialComponentRecyclerView);

//      각 버튼에 onClick listener setting
//      뒤로가기 버튼 onClick listener setting
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish(); //현재 activity 종료
            }
        });

//      검색 결과 전처리
        String searchResult = getIntent().getStringExtra("materials");

        ArrayList<HashMap<String,String>> searchResultArrayList = new ArrayList<HashMap<String,String>>();
        searchResultArrayList = TransService.chagneString2LineMaterial()

//      검색 결과 ArrayAdapter 연결


    }
}