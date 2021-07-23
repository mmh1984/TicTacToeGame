package com.ttt.tictactoegame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class register extends AppCompatActivity {
EditText username,password;
    TextView title;
    Button reg,cancel;
    ProgressDialog d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);

        title= (TextView) findViewById(R.id.textView4);
        reg= (Button) findViewById(R.id.btnCreate);
        cancel= (Button) findViewById(R.id.cancelbutton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(register.this,MainActivity.class));
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()){
                    username.setError("Input a valid username");

                }
                else {
                    if(password.getText().toString().isEmpty()){
                        password.setError("Input a valid password");
                    }
                    else {
                        d=new ProgressDialog(register.this);
                        d.setMessage("Creating new user");
                        d.setTitle("Tic Tac Toe");
                        d.show();

                        Handler h=new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                if(check_user(username.getText().toString(),password.getText().toString())==0){
                                    register_user(username.getText().toString(),password.getText().toString());
                                    d.cancel();
                                }
                                else {
                                    d.cancel();
                                    title.setText("Error,this username is already exists");
                                }
                            }
                        },3000);
                    }
                }
            }
        });

    }

    public void register_user(String u,String p) {
        try {
            Connection conn;
            ResultSet rs;

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String url = "jdbc:jtds:sqlserver://194.82.34.247;instance=SQLEXPRESS;DatabaseName=db_1430692_co5025_game";
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            String userName = "user_db_1430692_co5025_game";
            String password = "Amber2015";
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
            String query = "INSERT INTO users (username,userpass) VALUES ('" +u+"','" +p+"')";
            Statement st = conn.createStatement();
            st.executeUpdate(query);


            Handler h=new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    display_toast();
                }
            },3000);

            finish();
            startActivity(new Intent(register.this,MainActivity.class));


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public void display_toast() {
        Toast.makeText(this,"User Created",Toast.LENGTH_SHORT).show();
    }

    public int check_user(String u,String p) {
        int x = 0;
        try {
            Connection conn;
            ResultSet rs;

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String url = "jdbc:jtds:sqlserver://194.82.34.247;instance=SQLEXPRESS;DatabaseName=db_1430692_co5025_game";
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            String userName = "user_db_1430692_co5025_game";
            String password = "Amber2015";
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
            String query = "select * from users WHERE username='" + u +"' AND userpass='" + p +"'";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);


            while (rs.next()) {
                x++;

            }
            rs.close();


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        return x;
    }

}
