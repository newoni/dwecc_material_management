package com.example.dwecc_material_management_application.service;

import android.util.Log;

import com.example.dwecc_material_management_application.model.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class TransService {
    public static ArrayList<Product> changeString2Product(String input){

        ArrayList<Product> result = new ArrayList<Product>();

        //최초 입력값 출력
        Log.i("TransService, input_value_check",input);
        System.out.println("TransService, input value is: " + input);

        //첫번째 중괄호 제거
        String input_substring = input.substring(1,input.lastIndexOf(']'));

        System.out.println("TransService, input substringed reuslt");
        System.out.println(input_substring);

        Log.i("TransService","substringed input_array is");

        // 중괄호 기준으로 모두 나누기
        String[] input_array = input_substring.split("\\{");

        Log.i("String[] length","input_array");
        System.out.println(input_array.length);

        //--check. 다음에 사용할 것.
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for(int i=1; i< input_array.length; i++) {
            System.out.println("check input array["+i+"] length");
            System.out.println("input array["+i+"] length: " + input_array[i].length());
            System.out.println("check input array["+i+"] 's } index");
            System.out.println("input array["+i+"] 's } index: " + input_array[i].lastIndexOf("}"));
            input_array[i] = input_array[i].substring(0,input_array[i].lastIndexOf("}"));

            System.out.println(input_array[i]);

            String[] componentList = input_array[i].split(",");
            Product tmp_product = new Product();

            String productName = componentList[0].split(":")[1];
            String modelName = componentList[1].split(":")[1];
            String specName = componentList[2].split(":")[1];

            tmp_product.setProduct(productName.split("\"")[1]);
            tmp_product.setModel(modelName.split("\"")[1]);
            tmp_product.setSpec(specName.split("\"")[1]);

            result.add(tmp_product);
        }
        return result;
    }

//    public static ArrayList<HashMap<String,String>> chagneString2Material(String input){
//
//    }
}
