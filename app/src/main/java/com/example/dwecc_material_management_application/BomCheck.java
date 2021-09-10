package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.dwecc_material_management_application.model.BomSearchRequest;
import com.example.dwecc_material_management_application.model.Product;
import com.example.dwecc_material_management_application.service.PostService;
import com.example.dwecc_material_management_application.service.TransService;

import java.util.ArrayList;

public class BomCheck extends AppCompatActivity {

    String searchResult;
    static ArrayList<Product> resultArray;
//    static Product product;
//    static BomSearchRequest bomSearchRequest = new BomSearchRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bom_check);

//      id값에 따른 component들을 변수로 생성
        ImageButton button_back = (ImageButton) findViewById(R.id.bomCheck_start);
        EditText editText = (EditText)findViewById(R.id.editText);
        Button searchButton = (Button)findViewById(R.id.search_button);

//      각 버튼에 onClick listener setting
//      뒤로가기 버튼 onClick listener setting
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish(); //현재 activity 종료
            }
        });

//      검색 버튼 onClick listener setting
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                보낼 메세지 값 받아오기
                String sendMessage = editText.getText().toString();

                BomSearchRequest bomSearchRequest = new BomSearchRequest();
                System.out.println("sendMessage: "+ sendMessage);
//                전달 객체 product 생성 및 필드값 채우기
//                product.setSearchString(sendMessage);
                bomSearchRequest.setProduct(sendMessage);  //--check. new address test <21.09.07>

//                URL 설정 --check. URL 설정 필요, POST 사용 가능한지 확인해보기
//                String URL = "http://192.168.7.245:8081/material/readAll";
                String URL = "http://192.168.205.245:8081/bomSearch/product"; //--check. new address test <21.09.07>

//                POST 함수 실행
//                thread를 활용해서 실행(메인 스레드에서 실행 시 NetworkOnMainThreadException 발생
//                source: https://caileb.tistory.com/173

                Thread startThread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
//                        searchResult = PostService.POST(URL, product);
                        PostService postService = new PostService();
                        searchResult = postService.POST(URL, bomSearchRequest);
                    }
                };

                startThread.start();

                try {
                    startThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.i("searchResult",searchResult);
                resultArray = TransService.changeString2Product(searchResult);

                Log.i("result with searchResult", "resultArray.toString() is");
                for(int i=0; i<resultArray.size(); i++){
                    System.out.println(resultArray.get(i));
                }

                Intent intent = new Intent(BomCheck.this, BomCheckResult.class);
                startActivity(intent);
            }
        });
    }
}