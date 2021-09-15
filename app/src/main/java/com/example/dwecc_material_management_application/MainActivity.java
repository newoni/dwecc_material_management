package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {
    static public String URL = "http://183.90.137.186:8081";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_bomCheck = findViewById(R.id.bomCheck);
        Button button_insertMaterial2DB = findViewById(R.id.insertMaterial2DB);
        Button button_deleteMaterial2DB = findViewById(R.id.deleteMaterial2DB);
        Button button_readMaterial = findViewById(R.id.readMaterial);

        button_bomCheck.setOnClickListener(new View.OnClickListener() { // 클릭리스터 생성
            @Override // 부모 메소드 재정의
            public void onClick(View v) { // 클릭 이벤트 처리
                Intent intent = new Intent(MainActivity.this, BomCheck.class);
                startActivity(intent);
            }
        });

        button_insertMaterial2DB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QRInsert.class);
                startActivity(intent);
            }
        });

        button_deleteMaterial2DB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QRDelete.class);
                startActivity(intent);
            }
        });

        button_readMaterial.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MaterialCheckList.class);
                startActivity(intent);
            }
        });
    }
}