package com.ttt.tictactoegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class tilecolor extends AppCompatActivity implements View.OnClickListener{
Button b1,b2,b3,b4,b5,b6;
    String shape;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilecolor);

        shape=getIntent().getExtras().getString("shape");
        user=getIntent().getExtras().getString("user");
        load_shapes(shape);
        add_listeners();
    }

    public void add_listeners() {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);

    }

    public void load_shapes(String s){
        try{
            b1= (Button) findViewById(R.id.button1);
            b2= (Button) findViewById(R.id.button2);
            b3= (Button) findViewById(R.id.button3);
            b4= (Button) findViewById(R.id.button4);
            b5= (Button) findViewById(R.id.button5);
            b6= (Button) findViewById(R.id.button6);


            switch(s){
                case "square":
                    b1.setBackgroundResource(R.drawable.soblue);
                    b2.setBackgroundResource(R.drawable.sored);
                    b3.setBackgroundResource(R.drawable.soyellow);

                    b4.setBackgroundResource(R.drawable.sxblue);
                    b5.setBackgroundResource(R.drawable.sxred);
                    b6.setBackgroundResource(R.drawable.sxyellow);
                    break;
                case "circle":
                    b1.setBackgroundResource(R.drawable.oblue);
                    b2.setBackgroundResource(R.drawable.ored);
                    b3.setBackgroundResource(R.drawable.oyellow);

                    b4.setBackgroundResource(R.drawable.xblue);
                    b5.setBackgroundResource(R.drawable.xred);
                    b6.setBackgroundResource(R.drawable.xyellow);

                    break;

            }

        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
@Override
    public void onBackPressed() {
        super.onBackPressed();
    finish();
    startActivity(new Intent(getApplicationContext(),settings.class));

    }

    @Override
    public void onClick(View v) {
        String opponent="";
        String color="";
        String tile="";
        Intent i=new Intent(getApplicationContext(),startgame.class);
        try {
            switch(v.getId()){
                case R.id.button1:
                    color="blue";
                    opponent="yellow";
                    tile="O";
                    break;
                case R.id.button2:
                    color="red";
                    opponent="blue";
                    tile="O";
                    break;
                case R.id.button3:
                    color="yellow";
                    opponent="red";
                    tile="O";
                    break;
                case R.id.button4:
                    color="blue";
                    opponent="yellow";
                    tile="X";
                    break;
                case R.id.button5:
                    color="red";
                    opponent="blue";
                    tile="X";
                    break;
                case R.id.button6:
                    color="yellow";
                    opponent="red";
                    tile="X";
                    break;
            }

            i.putExtra("shape",shape);
            i.putExtra("tile",tile);
            i.putExtra("pcolor",color);
            i.putExtra("ocolor",opponent);
            i.putExtra("user",user);
            finish();
            startActivity(i);
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
