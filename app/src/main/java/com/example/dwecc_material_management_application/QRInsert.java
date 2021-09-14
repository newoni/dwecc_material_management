package com.example.dwecc_material_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dwecc_material_management_application.model.MaterialRequest;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRInsert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("자재 QR을 입력하세요");     // 옆에 뜨는 문구를 바꿀 수 있다.
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "3라인 입고를 종료하려면 뒤로 버튼을 2번 누르세요", Toast.LENGTH_LONG).show();
                // todo
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                // todo
                Log.i("QRInsert, result.getContents", result.getContents());
                String scannedString = result.getContents();
                String code = result.getContents().split(",")[0];
                String qty = result.getContents().split(",")[1];

                String last = result.getContents().split(",")[2];
                String lot = last.substring(0,last.length()-3);
                String seq = last.substring(last.length()-3, last.length());

                Log.i("QRInsert, scannedString", scannedString);
                Log.i("QRInsert, code", code);
                Log.i("QRInsert, seq", seq);
                Log.i("QRInsert, lot", lot);
                Log.i("QRInsert, qty", qty);

                MaterialRequest materialRequest = new MaterialRequest();
                materialRequest.setCode(code);
                materialRequest.setLot(lot);
                materialRequest.setSeq(Long.parseLong(seq));
                materialRequest.setQty(Long.parseLong(qty));

                Log.i("QRInsert, materialRequest.getCode", materialRequest.getCode());
                Log.i("QRInsert, materialRequest.getSeq", ""+materialRequest.getSeq());
                Log.i("QRInsert, materialRequest.getLot", materialRequest.getLot());
                Log.i("QRInsert, materialRequest.getQty", ""+materialRequest.getQty());

                //다시 scanenr 실행
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setOrientationLocked(false);
                integrator.setPrompt("자재 QR을 입력하세요");     // 옆에 뜨는 문구를 바꿀 수 있다.
                integrator.initiateScan();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}