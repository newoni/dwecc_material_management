package com.example.dwecc_material_management;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.dwecc_material_management.model.Product;
import com.example.dwecc_material_management.service.Json4Bom;

public class BomCheck extends AppCompatActivity {

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

//                전달 객체 product 생성 및 필드값 채우기
                Product product = new Product();
                product.setSearchString(sendMessage);

//                URL 설정 --check. URL 설정 필요, POST 사용 가능한지 확인해보기
                String URL = "192.169.0.0";

                System.out.println(URL);
                System.out.println(URL);
                System.out.println(URL);
                System.out.println(URL);
                System.out.println(URL);

//                POST 함수 실행
                Json4Bom.POST(URL, product);
            }
        });
    }
}