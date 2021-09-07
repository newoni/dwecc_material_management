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

        String input_substring = input.substring(1,input.lastIndexOf(']'));

        System.out.println("input substringed reuslt");
        System.out.println(input_substring);

        Log.i("TransService","substringed input_array is");

        String[] input_array = input_substring.split("\\{");

        Log.i("String[] length","input_array");
        System.out.println(input_array.length);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for(int i=1; i< input_array.length; i++) {
            System.out.println("check input array["+i+"] length");
            System.out.println("input array["+i+"] length: " + input_array[i].length());
            System.out.println("check input array["+i+"] 's } index");
            System.out.println("input array["+i+"] 's } index: " + input_array[i].lastIndexOf("}"));
            input_array[i] = input_array[i].substring(0,input_array[i].lastIndexOf("}"));

            System.out.println(input_array[i]);


            String[] componentList = input_array[i].split(",");
            Product tmp_product = new Product();

            System.out.println(componentList[0]);

            tmp_product.setId(Integer.parseInt(componentList[0].split(":")[1]));
            tmp_product.setMaterialInfo(Integer.parseInt(componentList[1].split(":")[1]));
            tmp_product.setLot(componentList[2].split(":")[1]);
            tmp_product.setSeq(Integer.parseInt(componentList[3].split(":")[1]));

            String date = componentList[4].substring(componentList[4].indexOf(":")+2,componentList[4].lastIndexOf("T"));
            tmp_product.setExpDate(LocalDate.parse(date, formatter));

            result.add(tmp_product);
        }
        return result;
    }
}
