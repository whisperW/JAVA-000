package org.wannajob.nio.homework;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.httpclient.NameValuePair;

import java.io.IOException;
import java.util.List;

public class OkHttpUtil {

    //全局使用一个OkHttpClient
    private static final OkHttpClient client=new OkHttpClient();

    public static String get(String url) {
        String result = null;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String post(String url, List<NameValuePair> paramList) {
        String result = null;
        FormBody.Builder builder = new FormBody.Builder();
        for (NameValuePair param : paramList) {
            builder.add(param.getName(), param.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String url = "http://127.0.0.1:8088/api/hello";
        String response = get(url);
        System.out.println(response);
    }




}
