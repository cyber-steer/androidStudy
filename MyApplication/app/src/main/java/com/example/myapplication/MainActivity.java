package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    TextView id, tel, name, email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (TextView) findViewById(R.id.id);
        tel = (TextView) findViewById(R.id.telNo);
        name = (TextView) findViewById(R.id.eMail);
        email = (TextView) findViewById(R.id.eMail);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
        Room
    }
    class Task extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");

                //2. 연결 객체 생성
                String url = "jdbc:mariadb://localhost:3333/androiddb";
                String user = "android";
                String pwd= "1111";
                Connection con = DriverManager.getConnection(url, user, pwd);
                Statement stmt = con.createStatement();

                String sql = "select * from customer;";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    id.setText(rs.getInt("id")+"");
                    name.setText(rs.getString("name"));
                    email.setText(rs.getString("email"));
                    tel.setText(rs.getString("telno"));
                }
            }catch (Exception e){
                System.out.println("=========================================================================");
                System.out.println(e);
                System.out.println("=========================================================================");
            }
            return null;
        }
    }

}