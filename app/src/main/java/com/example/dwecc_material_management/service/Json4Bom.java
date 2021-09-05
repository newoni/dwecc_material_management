package com.example.dwecc_material_management.service;
import android.util.Log;
import com.example.dwecc_material_management.model.Product;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class Json4Bom {

    public static String POST(String url, Product product){

        InputStream is = null;
        String result = "";

        try {
            URL urlCon = new URL(url);
            Log.i("URL is", urlCon.toString());
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

            String json = "";

            // build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("search_string", product.getSearchString());

            // convert JSONObject to JSON to String
            json = jsonObject.toString();

            System.out.println(json); //--check.
            System.out.println(json); //--check.
            System.out.println(json); //--check.
            System.out.println(json); //--check.
            System.out.println(json); //--check.
            System.out.println(json); //--check.
            System.out.println(json); //--check.
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // Set some headers to inform server about the type of the content
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestProperty("Content-type", "application/json");

            // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
            httpCon.setDoOutput(true);
            // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
            httpCon.setDoInput(true);

            OutputStream os = httpCon.getOutputStream();
            os.write(json.getBytes("euc-kr"));
            os.flush();

            // receive response as inputStream
            try {
                is = httpCon.getInputStream();
                // convert inputstream to string
                if(is != null)
                    result = convertStreamToString(is);
                else
                    result = "Did not work!";

                Log.i("value is", is.toString()); //is 값 출력
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                httpCon.disconnect();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
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
