package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BomCheckResult extends AppCompatActivity {

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
    }
}