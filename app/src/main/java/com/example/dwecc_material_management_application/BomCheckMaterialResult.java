package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.dwecc_material_management_application.model.BomSearchRequest;
import com.example.dwecc_material_management_application.model.Product;
import com.example.dwecc_material_management_application.service.PostService;
import com.example.dwecc_material_management_application.service.TransService;

import java.util.ArrayList;
import java.util.HashMap;

public class BomCheckMaterialResult extends AppCompatActivity {

    //검색 결과값 받아올 변수 선언
    String searchResult;

    //검색을 위한 객체 생성
    BomSearchRequest bomSearchRequest = new BomSearchRequest();

    //  ListView 생성
    ListView materialNameListView;
    ListView materialCodeListView;

    //  arrayAdapter 생성
    ArrayAdapter<String> arrayMaterialNameAdapter;
    ArrayAdapter<String> arrayMaterialCodeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bom_check_material_result);

        ImageButton backButton = (ImageButton)findViewById(R.id.closeBomCheckMaterialResult);

        //      각 버튼에 onClick listener setting
//      뒤로가기 버튼 onClick listener setting
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish(); //현재 activity 종료
            }
        });

        //      리스트 뷰 id 값과 연결
        materialNameListView = (ListView)findViewById(R.id.materialName);
        materialCodeListView = (ListView)findViewById(R.id.materialCode);

        String URL = MainActivity.URL + "/bomSearch/material";

        Log.i("BomCheckMaterialResult, getIntent", ""+getIntent());
        Log.i("BomCheckMaterialResult, getIntent.geStringExtra", ""+getIntent().getStringExtra("name"));
        Log.i("BomCheckMaterialResult, getIntent.geStringExtra.split()[0]" , ""+getIntent().getStringExtra("name").split(" ")[0]);
        Log.i("BomCheckMaterialResult, getIntent.geStringExtra.split()[1]" , ""+getIntent().getStringExtra("name").split(" ")[1]);
        Log.i("BomCheckMaterialResult, getIntent.geStringExtra.split()[2]" , ""+getIntent().getStringExtra("name").split(" ")[2]);

        //검색용 product 객체 생성
        bomSearchRequest.setProduct(getIntent().getStringExtra("name").split(" ")[0]);
        bomSearchRequest.setModel(getIntent().getStringExtra("name").split(" ")[1]);
        bomSearchRequest.setSpec(getIntent().getStringExtra("name").split(" ")[2]);

        Log.i("BomCheckMaterialResult, bomSearchRequest.getProduct()",bomSearchRequest.getProduct());
        Log.i("BomCheckMaterialResult, bomSearchRequest.getModel()",bomSearchRequest.getModel());
        Log.i("BomCheckMaterialResult, bomSearchRequest.getSpec()",bomSearchRequest.getSpec());

//                POST 함수 실행
//                thread를 활용해서 실행(메인 스레드에서 실행 시 NetworkOnMainThreadException 발생
//                source: https://caileb.tistory.com/173

        Thread startThread = new Thread(){
            @Override
            public void run() {
                super.run();
                PostService postService = new PostService();
                searchResult = postService.POST(URL, bomSearchRequest);
                Log.i("BomSearch material", "searchResult value is:" +searchResult);
            }
        };

        startThread.start();

        try {
            startThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("BomCheck, searchResult",searchResult);
        ArrayList<HashMap<String,String>> resultArraylist = TransService.chagneString2Material(searchResult);

        ArrayList<String> materialNameArrayList = new ArrayList<String>();
        ArrayList<String> materialCodeArrayList = new ArrayList<String>();

        for(int i=0; i< resultArraylist.size(); i++){
            materialNameArrayList.add(resultArraylist.get(i).get("name"));
            materialCodeArrayList.add(resultArraylist.get(i).get("code"));
        }

        arrayMaterialNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, materialNameArrayList);
        arrayMaterialCodeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, materialCodeArrayList);

        materialNameListView.setAdapter(arrayMaterialNameAdapter);
        materialCodeListView.setAdapter(arrayMaterialCodeAdapter);
    }
}