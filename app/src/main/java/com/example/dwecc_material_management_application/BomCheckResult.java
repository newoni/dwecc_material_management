package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BomCheckResult extends AppCompatActivity {
//  ListView 생성
    private ListView productListView;

//        arrayAdapt 생성
    ArrayAdapter<String> arryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bom_check_result);

        //      id값에 따른 component들을 변수로 생성
        ImageButton button_back = (ImageButton) findViewById(R.id.backToBomCheck);

        //      각 버튼에 onClick listener setting
//      뒤로가기 버튼 onClick listener setting
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish(); //현재 activity 종료
            }
        });

//        ListView 객체를 ID를 기준으로 가져옴
        productListView = (ListView) findViewById(R.id.productListView);

//        arrayList 생성
        ArrayList<String> productNameArraylist= new ArrayList<String>();

//       BomCheck의 result Array 길이 만큼 ArrayList 를 String type으로 채웝줌
        for(int i=0; i<BomCheck.resultArray.size();i++){
            String str = BomCheck.resultArray.get(i).getProduct() + " " +BomCheck.resultArray.get(i).getModel() + " " +BomCheck.resultArray.get(i).getSpec();
            productNameArraylist.add(str);
        }

//        //        arrayAdapt 객체 생성
        arryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productNameArraylist);
//
//        ListView를 ArrayAdapter에 연결
        productListView.setAdapter(arryAdapter);

//      source:  https://kitesoft.tistory.com/67
//      ListView OnClick Listener 생성
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(BomCheckResult.this, BomCheckMaterialResult.class);
                startActivity(intent);
            }
        });
    }
}