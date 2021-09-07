package com.example.dwecc_material_management_application.service;

import android.util.Log;

import com.example.dwecc_material_management_application.model.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransService {
    public static ArrayList<Product> changeString2Product(String input){

        ArrayList<Product> result = new ArrayList<Product>();

        Log.i("input_value_check",input);

        String input_substring = input.substring(2,input.lastIndexOf('}'));

        System.out.println("input first");
        System.out.println(input_substring);

        Log.i("TransService","input_array is");

        String[] input_array = input_substring.split("},\\{");
        Log.i("standard is", "},{");
        System.out.println(input_array[0]);
//        System.out.println(input_array[1]);
//        System.out.println(input_array[2]);
//        System.out.println(input_array[3]);
//        System.out.println(input_array[4]);


        for(int i=0; i< input_array.length; i++) {
            String[] componentList = input_array[i].split(",");
            Product tmp_product = new Product();

            tmp_product.setId(Integer.parseInt(componentList[0].split(":")[1]));
            tmp_product.setMaterialInfo(Integer.parseInt(componentList[1].split(":")[1]));
            tmp_product.setLot(componentList[2].split(":")[1]);
            tmp_product.setSeq(Integer.parseInt(componentList[3].split(":")[1]));
            tmp_product.setExpDate(LocalDate.parse(componentList[4].split(":")[1], DateTimeFormatter.ISO_DATE));

            result.add(tmp_product);
        }
        return result;
    }
}
