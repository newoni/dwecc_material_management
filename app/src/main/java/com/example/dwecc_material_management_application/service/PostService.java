package com.example.dwecc_material_management_application.service;
import android.util.Log;

import com.example.dwecc_material_management_application.model.BomSearchRequest;
import com.example.dwecc_material_management_application.model.Product;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

// source: https://m.blog.naver.com/beodeulpiri/220730560270
public class PostService {

        public InputStream is = null;
        public String result = "";
//    public static String POST(String url, Product product){
    public String POST(String url, BomSearchRequest bomSearchRequest){ // --check. new class test <21.09.07>

        try {
            URL urlCon = new URL(url);
            Log.i("URL is", urlCon.toString());
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

            httpCon.setRequestMethod("POST");

            String json = "";

            // build jsonObject
            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("search_string", product.getSearchString());
            jsonObject.accumulate("product", bomSearchRequest.getProduct()); //--check. new class test <21.09.07>
            jsonObject.accumulate("model", bomSearchRequest.getModel()); //--check. new class test <21.09.07>
            jsonObject.accumulate("spec", bomSearchRequest.getSpec()); //--check. new class test <21.09.07>


            // convert JSONObject to JSON to String
            json = jsonObject.toString();

            System.out.println("PostService, json: " + json);

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // Set some headers to inform server about the type of the content
            Log.i("PostSerice, set_request_property", "setting request property");
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestProperty("Content-type", "application/json");

            // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
            httpCon.setDoOutput(true);
            // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
            httpCon.setDoInput(true);

            Log.i("PostSerice,set_output_stream", "setting output stream");
            OutputStream os = httpCon.getOutputStream();

            //--check. 바로 윗줄에서 연결 문제 일어남.
            Log.i("test", "maybe this sentence will not be runned");
            Log.i("before_os_write","before os.wrtie, json value: " + json);
//            os.write(json.getBytes("euc-kr"));
            os.write(json.getBytes("UTF-8"));
            os.flush();

            // receive response as inputStream
            try {
                Log.i("trying2set_inputstream", "trying inputstream setting");
                is = httpCon.getInputStream();

                Log.i("value_i_check", "value_i_check result");
                System.out.println(is);

                // convert inputstream to string
                if(is != null)
                    result = convertStreamToString(is);
                else
                    result = "Did not work!";

                Log.i("value is", is.toString()); //is 값 출력
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                Log.i("trying2set_inputstream", "exception occured(finally)");
                httpCon.disconnect();
            }
        }catch (IOException e) {
//            e.printStackTrace();
        }catch (Exception e) {
            Log.i("trying2set_inputstream", "exception occured2");
//            Log.d("InputStream", e.getLocalizedMessage());
//            e.printStackTrace();
        }

        Log.i("result", result); //결과 출력
        return result;
    }

//    출처: https://unikys.tistory.com/196 [All-round programmer]
    private static String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try{
            while ((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
