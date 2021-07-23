package com.ttt.tictactoegame;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
public MediaPlayer bgmusic,buttonclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bgmusic=MediaPlayer.create(this,R.raw.soundbg_01);
        buttonclick=MediaPlayer.create(this,R.raw.buttonclick);
        bgmusic.start();
        bgmusic.setLooping(true);

        ImageView logo= (ImageView) findViewById(R.id.imagelogo);
        Animation logoanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim1);
        logoanim.setRepeatCount(Animation.INFINITE);
        logo.startAnimation(logoanim);

        Button start= (Button) findViewById(R.id.btnstart);
       Button how= (Button) findViewById(R.id.btnhowtoplay);
       Button register= (Button) findViewById(R.id.btnregister);
        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgmusic.stop();
                buttonclick.start();
                finish();
                startActivity(new Intent(getApplicationContext(),instructions.class));

            }
        });

        Button about= (Button) findViewById(R.id.btnabout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgmusic.stop();
                buttonclick.start();
                finish();
                startActivity(new Intent(getApplicationContext(),aboutgame.class));
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog login=new Dialog(MainActivity.this);
                login.setTitle("Login to start game");
                login.setContentView(R.layout.activity_dialog1);
                login.show();

                final TextView user= (TextView) login.findViewById(R.id.username);
                final TextView pass= (TextView) login.findViewById(R.id.password);
                Button loginbtn= (Button) login.findViewById(R.id.btnCreate);
                Button cancelbtn= (Button) login.findViewById(R.id.cancelbutton);

                cancelbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login.cancel();
                    }

                });

                loginbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bgmusic.stop();
                        buttonclick.start();

                        Intent set=new Intent(new Intent(getApplicationContext(),settings.class));
                        set.putExtra("username",user.getText().toString());
                        set.putExtra("password",pass.getText().toString());

                        startActivity(set);
                    }
                });


            }
        });

        Button scores= (Button) findViewById(R.id.highscore);
        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgmusic.stop();
                buttonclick.start();
                finish();
                startActivity(new Intent(getApplicationContext(),scoreboard.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgmusic.stop();
                buttonclick.start();
                finish();
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        bgmusic.stop();
    }



}
