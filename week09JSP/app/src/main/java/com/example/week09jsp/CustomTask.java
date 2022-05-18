package com.example.week09jsp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomTask extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;
            URL url = new URL("http://10.0.2.2:8080/android/Android/androidDB.jsp");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            con.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(),"UTF-8");

            if(strings[0].equals("insert")) {
                sendMsg = "type=" + strings[0] + "&name=" + strings[1] + "&telno=" + strings[2] + "&email=" + strings[3];
                System.out.println(sendMsg);
            }
            else if(strings[0].equals("select"))
                sendMsg = "type="+strings[0]+"&name="+strings[1];

            osw.write(sendMsg);
            osw.flush();
            if(con.getResponseCode() == con.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(con.getInputStream(),"UTF-8");
                System.out.println("tmp : "+tmp.toString());
                BufferedReader reader = new BufferedReader(tmp);
                System.out.println("reader : "+reader.toString());
                StringBuffer buffer = new StringBuffer();
                while((str = reader.readLine())!=null){
                    System.out.println("str : "+str);
                    buffer.append(str);
                    System.out.println("buffer : "+buffer.toString());
                }
                receiveMsg = buffer.toString();
            }else{
                Log.i("통신 결과", con.getResponseCode()+"에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}
