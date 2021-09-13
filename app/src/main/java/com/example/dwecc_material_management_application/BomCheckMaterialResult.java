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

public class BomCheckMaterialResult extends AppCompatActivity {

    //검색 결과값 받아올 변수 선언
    String searchResult;

    //검색을 위한 객체 생성
    BomSearchRequest bomSearchRequest;

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

        //검색용 product 객체 생성
        bomSearchRequest.setProduct(getIntent().getStringExtra("name").split(" ")[0]);
        bomSearchRequest.setModel(getIntent().getStringExtra("name").split(" ")[1]);
        bomSearchRequest.setSpec(getIntent().getStringExtra("name").split(" ")[2]);

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
    }
}