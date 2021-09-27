package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.dwecc_material_management_application.model.request.BomSearchRequest;
import com.example.dwecc_material_management_application.model.Product;
import com.example.dwecc_material_management_application.service.PostService;
import com.example.dwecc_material_management_application.service.TransService;

import java.util.ArrayList;

public class BomCheck extends AppCompatActivity {

    String searchResult;
    static ArrayList<Product> resultArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bom_check);

//      id값에 따른 component들을 변수로 생성
        ImageButton backButton = (ImageButton) findViewById(R.id.backToMain);
        EditText editText = (EditText)findViewById(R.id.editText);
        Button searchButton = (Button)findViewById(R.id.search_button);

//      각 버튼에 onClick listener setting
//      뒤로가기 버튼 onClick listener setting
        backButton.setOnClickListener(new View.OnClickListener(){
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

//                전달 객체 product 생성 및 필드값 채우기
                BomSearchRequest bomSearchRequest = new BomSearchRequest();
                System.out.println("BomCheck Class, sendMessage: "+ sendMessage);
                bomSearchRequest.setProduct(sendMessage);  //--check. new address test <21.09.07>

//                URL 설정
                String URL = MainActivity.URL + "/bomSearch/product"; //--check. new address test <21.09.07>

//                POST 함수 실행
//                thread를 활용해서 실행(메인 스레드에서 실행 시 NetworkOnMainThreadException 발생
//                source: https://caileb.tistory.com/173
                Thread startThread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        PostService postService = new PostService();
                        searchResult = postService.post4bom(URL, bomSearchRequest);
                        Log.i("BomSearch", "searchResult value is:" +searchResult);
                    }
                };
                startThread.start();

//                통신 결과 대기
                try {
                    startThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                결과값 ArrayList<product> type으로 형변환
                Log.i("BomCheck, searchResult",searchResult);
                resultArray = TransService.changeString2Product(searchResult);

//                결과값 출력
                Log.i("BomCheck, result with searchResult", "resultArray.toString() is");
                for(int i=0; i<resultArray.size(); i++){
                    System.out.println(resultArray.get(i));
                }

//                결과값 출력 페이지로 이동
                Intent intent = new Intent(BomCheck.this, BomCheckResult.class);
                startActivity(intent);
            }
        });
    }
}