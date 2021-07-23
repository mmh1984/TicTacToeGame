package com.ttt.tictactoegame;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class settings extends AppCompatActivity {
    public MediaPlayer buttonclick;
    String username,password;
    ProgressDialog maindialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonclick=MediaPlayer.create(this,R.raw.buttonclick);
        Button square= (Button) findViewById(R.id.square);
        Button circle= (Button) findViewById(R.id.circle);
       username=getIntent().getExtras().getString("username");
        password=getIntent().getExtras().getString("password");


        maindialog=new ProgressDialog(settings.this);
        maindialog.setMessage("Logging in to server");
        maindialog.setTitle("Tic Tac Toe");
        maindialog.setProgressStyle(R.style.progress);
        maindialog.show();
        check_login();


        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),tilecolor.class);
                i.putExtra("shape","square");
                i.putExtra("user",username);
                finish();
                startActivity(i);

            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),tilecolor.class);
                i.putExtra("shape","circle");
                i.putExtra("user",username);
                finish();
                startActivity(i);
            }
        });

    }

    public void check_login() {


       final Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(login_ok(username,password)==1){
                    maindialog.cancel();
                    display_dialog("Login successful");



                }
                else {
                    maindialog.cancel();
                    display_dialog1("Login Failed");
                }
            }
        },2000);


    }
    public int login_ok(String u,String p){
        int x=0;
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

    public void display_dialog(String message){


        AlertDialog.Builder alert=new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_Dialog);
        alert.setMessage(message);
        alert.setCancelable(false);
        alert.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                }
        );

        AlertDialog a=alert.create();
        a.show();
    }

    public void display_dialog1(String message){


        AlertDialog.Builder alert=new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_Dialog);
        alert.setMessage(message);
        alert.setCancelable(false);

        alert.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        finish();
                    }
                }
        );

        AlertDialog a=alert.create();
        a.show();
    }
@Override
    public void onBackPressed() {
        super.onBackPressed();
    finish();
    startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
