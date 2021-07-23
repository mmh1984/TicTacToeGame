package com.ttt.tictactoegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class instruction2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction2);


        Button next= (Button) findViewById(R.id.btnnext);




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),instruction3.class));
            }
        });
    }
@Override
    public void onBackPressed() {
        super.onBackPressed();

    startActivity(new Intent(instruction2.this,MainActivity.class));
    }
}
