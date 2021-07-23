package com.ttt.tictactoegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class aboutgame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutgame);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
