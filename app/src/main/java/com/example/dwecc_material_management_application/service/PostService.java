package com.example.dwecc_material_management_application.service;
import android.util.Log;

import com.example.dwecc_material_management_application.model.request.BomSearchRequest;
import com.example.dwecc_material_management_application.model.request.MaterialRequest;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

// source: https://m.blog.naver.com/beodeulpiri/220730560270
public class PostService {

        public InputStream is = null;
        public String result = "";

/*
description: BOM 확인 기능. (
input: (String) url, (BomSearchRequest) product, model, spec 이 들어감.
output: (String), BomCheckResult 에서 호출될 경우, 제품 리스트. BomCheckMaterialResult 에서 호출될 경우 자재 리스트 출력
*/
    public String post4bom(String url, BomSearchRequest bomSearchRequest){ // --check. new class test <21.09.07>
        Log.i("PostService, post4bom", "post4bom method is started");
        try {
            URL urlCon = new URL(url);
            Log.i("URL is", urlCon.toString());
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

            Log.i("PostService, post4bom httpCon value is", httpCon.toString());

            httpCon.setRequestMethod("POST");

            String json = "";

            // build jsonObject
            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("search_string", product.getSearchString());
            jsonObject.accumulate("product", bomSearchRequest.getProduct()); //--check. new class test <21.09.07>
            jsonObject.accumulate("model", bomSearchRequest.getModel()); //--check. new class test <21.09.07>
            jsonObject.accumulate("spec", bomSearchRequest.getSpec()); //--check. new class test <21.09.07>

            Log.i("PostService, post4bom jsonObejct product", jsonObject.getString("product"));

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
            e.printStackTrace();
        }catch (Exception e) {
            Log.i("trying2set_inputstream", "exception occured2");
//            Log.d("InputStream", e.getLocalizedMessage());
            e.printStackTrace();
        }

        Log.i("result", result); //결과 출력
        return result;
    }

/*
    description: QR code data 송신, url에 따라 server가 다르게 동작할 것
    input: (String) url, (MaterialRequest) code, lot, seq, qty
    output: (String), 아마 없을 듯.
 */
    public String post4material(String url, MaterialRequest request){
        try {
            URL urlCon = new URL(url);
            Log.i("URL is", urlCon.toString());
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

            httpCon.setRequestMethod("POST");

            String json = "";

            // build jsonObject
            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("search_string", product.getSearchString());
            jsonObject.accumulate("code", request.getCode());
            jsonObject.accumulate("lot", request.getLot()); 
            jsonObject.accumulate("qty", request.getQty()); 
            jsonObject.accumulate("seq", request.getSeq()); 


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

            /*
            --check. 바로 윗줄에서 연결 문제 일어남.<21.09.30>
            (HTTPLog)-Static: isSBSettingEnabled false 가 일어났었음. 
            thread 활용해서 수행해보기
            -> 메인스레드에서 너무 많은 작업 수행중
             */
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
            e.printStackTrace();
        }catch (Exception e) {
            Log.i("trying2set_inputstream", "exception occured2");
//            Log.d("InputStream", e.getLocalizedMessage());
            e.printStackTrace();
        }

        Log.i("result", result); //결과 출력
        return result;
    }

/*
    description: 모든 자재 정보 요청 method
    input: (String) url
    output: (String) 자재 정보 리스트
 */
    public String post4readAllMaterial(String url){
        try {
            URL urlCon = new URL(url);
            Log.i("URL is", urlCon.toString());
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

            httpCon.setRequestMethod("POST");

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

            Log.i("test", "maybe this sentence will not be runned");
//            os.write(json.getBytes("euc-kr"));
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
    //받아온 결과값을 String type으로 받아옴
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
