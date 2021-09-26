package com.bh1ofp.week02;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OkHttpDemo {
    OkHttpClient client = new OkHttpClient();

   public  String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args){
        OkHttpDemo ohr = new OkHttpDemo();
        try {
            System.out.println(ohr.run("http://localhost:8804"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
