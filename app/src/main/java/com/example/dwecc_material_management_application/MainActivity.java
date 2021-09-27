package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dwecc_material_management_application.service.PostService;
import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {
//    static public String URL = "http://183.90.137.186:8081";
    static public String URL = "http://10.0.2.2:8081";

//  결과값 입력을 위한 변수 선언
    String searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        각각의 버튼 연결
        Button button_bomCheck = findViewById(R.id.bomCheck);
        Button button_insertMaterial2DB = findViewById(R.id.insertMaterial2DB);
        Button button_deleteMaterial2DB = findViewById(R.id.deleteMaterial2DB);
        Button button_readMaterial = findViewById(R.id.readMaterial);

//        각각의 버튼에 OnClickListener 연결
//        button_bomCheck에 OnClickListener 연결
        button_bomCheck.setOnClickListener(new View.OnClickListener() { // 클릭리스터 생성
            @Override // 부모 메소드 재정의
            public void onClick(View v) { // 클릭 이벤트 처리
                Intent intent = new Intent(MainActivity.this, BomCheck.class);
                startActivity(intent);
            }
        });

//        button_insertMaterial2DB에 OnClickListener 연결
        button_insertMaterial2DB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QRInsert.class);
                startActivity(intent);
            }
        });

//        button_deleteMaterial2DB에 OnClickListener 연결
        button_deleteMaterial2DB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QRDelete.class);
                startActivity(intent);
            }
        });

//        button_readMaterial에 OnClickListener 연결
        button_readMaterial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                URL 설정
                String url4reaAllMaterial = MainActivity.URL + "/material/readAll";


//                POST 함수 실행
//                thread를 활용해서 실행(메인 스레드에서 실행 시 NetworkOnMainThreadException 발생
//                source: https://caileb.tistory.com/173
                Thread startThread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        PostService postService = new PostService();
                        searchResult = postService.post4readAllMaterial(url4reaAllMaterial);
                        Log.i("MainActivity, readMaterial", "searchResult value is:" +searchResult);
                    }
                };
                startThread.start();

//                통신 결과 대기
                try {
                    startThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                새창 열기 및 새 Intent에 결과 데이터 넘기기
                Intent intent = new Intent(MainActivity.this, MaterialCheckList.class);
                intent.putExtra("materials",searchResult);
                startActivity(intent);
            }
        });
    }
}