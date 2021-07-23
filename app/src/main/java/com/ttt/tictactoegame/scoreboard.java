package com.ttt.tictactoegame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class scoreboard extends AppCompatActivity {
    ArrayList<String> leaders;
    ProgressDialog d;
    TextView player,win,loss,games,percent;
    String splayer,swin,sloss,sgames,spercent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        ImageView logo= (ImageView) findViewById(R.id.imghighscore);
        Animation logoanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim1);
        logoanim.setRepeatCount(Animation.INFINITE);
        logo.startAnimation(logoanim);

       player= (TextView) findViewById(R.id.txtname);
        win= (TextView) findViewById(R.id.txtwins);
        loss= (TextView) findViewById(R.id.txtloss);
        games= (TextView) findViewById(R.id.txtgames);
        percent= (TextView) findViewById(R.id.txtpercent);


        splayer=player.getText().toString() + "\n \n";
        swin=win.getText().toString() + "\n \n";
        sloss=loss.getText().toString() + "\n \n";
        sgames=games.getText().toString() + "\n \n";
        spercent=percent.getText().toString() + "\n \n";


        d=new ProgressDialog(scoreboard.this);
        d.setMessage("Retrieving scores");
        d.setTitle("Tic Tac Toe");
        d.show();

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

scoreleaders();

                d.cancel();
            }
        },3000);





    }

    public void scoreleaders() {
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
            String query="select top 10 * from scoreleaders order by winningpercent DESC ";
            Statement st=conn.createStatement();
            rs=st.executeQuery(query);
            int x=0;

            while (rs.next()){
                splayer+=rs.getString(2) + "\n";
                swin+=rs.getString(3) + "\n";
                sloss+=rs.getString(4) + "\n";
                sgames+=rs.getString(5) + "\n";
                spercent+=String.format("%.0f",Double.valueOf(rs.getString(6)) )+ "\n";

            }
            rs.close();

            player.setText(splayer);
            win.setText(swin);
            loss.setText(sloss);
            games.setText(sgames);
            percent.setText(spercent);



        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(scoreboard.this,MainActivity.class));
    }
}
