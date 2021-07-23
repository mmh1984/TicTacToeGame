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

public class stats extends AppCompatActivity {
int score,gamesplayed,aiscore;
    TextView win,loss,games,percent;
    EditText playername;
    Double winpercent;
    Button submit;
    ProgressDialog d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        String user=getIntent().getExtras().getString("user");
        score=getIntent().getExtras().getInt("playerscore");
        aiscore=getIntent().getExtras().getInt("aiscore");
        gamesplayed=getIntent().getExtras().getInt("gamesplayed");

        win= (TextView) findViewById(R.id.wins);
        loss= (TextView) findViewById(R.id.loss);
        games=(TextView) findViewById(R.id.gamesplayed);
        percent = (TextView) findViewById(R.id.winpercent);
        submit= (Button) findViewById(R.id.btnsubmit);
        playername= (EditText) findViewById(R.id.playername);
        playername.setText(user.toString());
        playername.setEnabled(false);
        winpercent= Double.valueOf(score)/ Double.valueOf(gamesplayed) * 100;


        win.setText(String.valueOf(score));
        loss.setText(String.valueOf(aiscore));
        games.setText(String.valueOf(gamesplayed));
        percent.setText(String.format("%.2f",winpercent));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(playername.getText().toString().isEmpty()){
                   playername.setError("Please enter your name");
               }
                else {
                show_loading();


                Handler h=new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        submit_stats();
                        d.cancel();
                    }
                },3000);
               }
            }
        });

    }
    public void submit_stats() {
        try {


            String count="";
            String pname="";
            String pscore="";

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
            String query="INSERT INTO scoreleaders (playername,gameswon,gamesloss,gamesplayed,winningpercent) ";
            query+="VALUES ('" + playername.getText().toString() +"'," + score +"," + aiscore +"," + gamesplayed +","+ winpercent+")";
            Statement st=conn.createStatement();
           st.executeUpdate(query);

            Toast.makeText(this,"Score submitted",Toast.LENGTH_SHORT).show();
            Handler h=new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                   startActivity(new Intent(stats.this,scoreboard.class));
                }
            },1000);






        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
    public void show_loading() {

        try {
            d=new ProgressDialog(stats.this);
            d.setMessage("Submitting your score...");
            d.setTitle("Tic Tac Toe");
            d.show();
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(stats.this,MainActivity.class));
    }
}
