package com.ttt.tictactoegame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class startgame extends AppCompatActivity implements View.OnClickListener{
String humancolor,aicolor,shape,tile,humantile,aitile;
    String user;
    Animation anim1;
    Button[] gamebuttons;
    Button exit;
    String [] buttonsymbols;
    String human;
    int humanbg,aibg;
    GridLayout maingrid;
    TextView playerscore,aiscore;
    ArrayList<String> listiles;
    int pscore,ascore,gamesplayed;
    MediaPlayer winner,loser,buttonsound;
    AlertDialog.Builder draw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);

        humancolor=getIntent().getExtras().getString("pcolor");
        user=getIntent().getExtras().getString("user");
        aicolor=getIntent().getExtras().getString("ocolor");
        shape=getIntent().getExtras().getString("shape");

        tile=getIntent().getExtras().getString("tile");

        anim1=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim2);
        maingrid= (GridLayout) findViewById(R.id.gamegrid);
        playerscore= (TextView) findViewById(R.id.txtcore);
        aiscore= (TextView) findViewById(R.id.txthighscore);
        buttonsound=MediaPlayer.create(getApplicationContext(),R.raw.buttonclick);
        winner= MediaPlayer.create(getApplicationContext(),R.raw.winner);
        loser=MediaPlayer.create(getApplicationContext(),R.raw.loser);


        draw=new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_Dialog);
        pscore=0;
        ascore=0;
        gamesplayed=0;
        set_buttons();
        set_shape_background();
        count_tiles();
        first_move();




    }

    public void set_shape_background() {
        try {

            if(shape.equals("square") && tile.equals("X")) {

                humantile="X";
                aitile="O";
                if(humancolor.equals("red")){
                    humanbg=R.drawable.sxred;
                    aibg=R.drawable.soyellow;
                }
                else if(humancolor.equals("yellow")){
                    humanbg=R.drawable.sxyellow;
                    aibg=R.drawable.soblue;
                }
                else if(humancolor.equals("blue")){
                    humanbg=R.drawable.sxblue;
                    aibg=R.drawable.sored;
                }
            }
            else if(shape.equals("square") && tile.equals("O")) {
                humantile="O";
                aitile="X";
                if(humancolor.equals("red")){
                    humanbg=R.drawable.sored;
                    aibg=R.drawable.sxyellow;
                }
                else if(humancolor.equals("yellow")){
                    humanbg=R.drawable.soyellow;
                    aibg=R.drawable.sxblue;
                }
                else if(humancolor.equals("blue")){
                    humanbg=R.drawable.soblue;
                    aibg=R.drawable.sxred;
                }
            }

            if(shape.equals("circle") && tile.equals("O")) {
                humantile="O";
                aitile="X";
                if(humancolor.equals("red")){
                    humanbg=R.drawable.ored;
                    aibg=R.drawable.xyellow;
                }
                else if(humancolor.equals("yellow")){
                    humanbg=R.drawable.oyellow;
                    aibg=R.drawable.xblue;
                }
                else if(humancolor.equals("blue")){
                    humanbg=R.drawable.oblue;
                    aibg=R.drawable.xred;
                }
            }
            else if(shape.equals("circle") && tile.equals("X")) {
                humantile="X";
                aitile="O";
                if(humancolor.equals("red")){
                    humanbg=R.drawable.xred;
                    aibg=R.drawable.oyellow;
                }
                else if(humancolor.equals("yellow")){
                    humanbg=R.drawable.xyellow;
                    aibg=R.drawable.oblue;
                }
                else if(humancolor.equals("blue")){
                    humanbg=R.drawable.xblue;
                    aibg=R.drawable.ored;
                }
            }








        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void set_buttons() {
        try {
gamesplayed++;
            buttonsymbols=new String[9];
            gamebuttons=new Button[9];
            gamebuttons[0]= (Button) findViewById(R.id.tile1);
            gamebuttons[1]= (Button) findViewById(R.id.tile2);
            gamebuttons[2]= (Button) findViewById(R.id.tile3);
            gamebuttons[3]= (Button) findViewById(R.id.tile4);
            gamebuttons[4]= (Button) findViewById(R.id.tile5);
            gamebuttons[5]= (Button) findViewById(R.id.tile6);
            gamebuttons[6]= (Button) findViewById(R.id.tile7);
            gamebuttons[7]= (Button) findViewById(R.id.tile8);
            gamebuttons[8]= (Button) findViewById(R.id.tile9);

            for(int x=0;x<9;x++){
                buttonsymbols[x]=" ";
                gamebuttons[x].startAnimation(anim1);
                gamebuttons[x].setOnClickListener(this);
                gamebuttons[x].setBackgroundResource(R.drawable.squarebg);
                gamebuttons[x].setEnabled(true);

            }


        }
        catch (Exception e){
            Toast.makeText(null,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {

        Button clicked = (Button) v;
buttonsound.start();
        if (count_tiles() > 0) {
            for (int a = 0; a < 9; a++) {
                if (clicked == gamebuttons[a] && buttonsymbols[a].equals(" ")) {
                    buttonsymbols[a] = humantile;
                    gamebuttons[a].setBackgroundResource(humanbg);
                    gamebuttons[a].setEnabled(true);

                    if (check_winner(humantile)) {
                        winner.seekTo(0);
                        winner.start();

                        Handler ht=new Handler();
                        ht.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                draw.setTitle("You WON!!!");
                                draw.setMessage("Congratulations!");
                                draw.setIcon(R.drawable.winner);
                                game_draw();

                            }
                        },3000);

                        lock_buttons();
                        pscore++;
                        playerscore.setText(String.valueOf(pscore));

                    }
                    else {
                        if (count_tiles() > 1) {
                            generate_move();
                            if (check_winner(aitile)) {
                                loser.seekTo(0);
                                loser.start();
                                draw.setTitle("You LOSE...");
                                draw.setMessage("Better luck next time");
                                draw.setIcon(R.drawable.loser);
                                game_draw();

                                lock_buttons();
                                ascore++;
                                aiscore.setText(String.valueOf(ascore));
                            }
                        }
                        else {

                            Handler hr=new Handler();
                            hr.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    draw.setMessage("Ooops, No winner yet. Play again?");
                                    draw.setTitle("DRAW");

                                    draw.setIcon(R.drawable.draw);
                                    game_draw();
                                }
                            },3000);

                        }

                    }


                }
            }


        }
        else {
            draw.setMessage("Ooops, No winner yet. Play again?");
            draw.setTitle("DRAW");

            draw.setIcon(R.drawable.draw);
         game_draw();
        }
    }

    public void lock_buttons() {
        for (int x=0;x<9;x++){
            gamebuttons[x].setEnabled(false);
        }
    }
    public void winning_line(int b1, int b2,int b3, int line){
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.blink);
        gamebuttons[b1].setBackgroundResource(line);
        gamebuttons[b2].setBackgroundResource(line);
        gamebuttons[b3].setBackgroundResource(line);
        gamebuttons[b1].startAnimation(anim);
        gamebuttons[b2].startAnimation(anim);
        gamebuttons[b3].startAnimation(anim);


    }

    public boolean check_winner(String symbol) {
        int line;
        boolean b=false;
        try {
            int y=count_tiles();
            aimoves mv=new aimoves(listiles);
//straight horizontal
            if(mv.check_winner(symbol,0,1,2)){

                line=R.drawable.horiz_line;
                winning_line(0,1,2,line);
                b=true;
            }

            else if(mv.check_winner(symbol,3,4,5)){

                line=R.drawable.horiz_line;
                winning_line(3,4,5,line);
                b=true;
            }

            else if(mv.check_winner(symbol,6,7,8)){

                line=R.drawable.horiz_line;
                winning_line(6,7,8,line);
                b=true;
            }
//straight vertical
            else if(mv.check_winner(symbol,0,3,6)){

                line=R.drawable.vert_line;
                winning_line(0,3,6,line);
                b=true;

            }
            else if(mv.check_winner(symbol,1,4,7)){

                line=R.drawable.vert_line;
                winning_line(1,4,7,line);
                b=true;
            }
            else if(mv.check_winner(symbol,2,5,8)){

                line=R.drawable.vert_line;
                winning_line(2,5,8,line);
                b=true;
            }

            //two diagonal
            else if(mv.check_winner(symbol,0,4,8)){

                line=R.drawable.down;
                winning_line(0,4,8,line);
                b=true;

            }
            else if(mv.check_winner(symbol,2,4,6)){

                line=R.drawable.up;
                winning_line(2,4,6,line);
                b=true;
            }
        }
        catch (Exception e){
            Toast.makeText(null,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
        return b;
    }



    public void generate_move() {
        try {
            int index=0;
             aimoves mv=new aimoves(listiles);
            index=mv.get_index();
            gamebuttons[index].setBackgroundResource(aibg);
            buttonsymbols[index]=aitile;
        }
        catch (Exception e){
            Toast.makeText(null,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }



    public void game_draw() {


        draw.setCancelable(false);
        draw.setPositiveButton(
                "Play Again",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        set_buttons();
                        dialog.cancel();

                    }
                }

        );

        draw.setNegativeButton(
                "Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        finish();
                        finish_game();

                    }
                }

        );



        AlertDialog showmessage=draw.create();
        showmessage.show();

    }

    public void finish_game() {
        Intent i=new Intent(startgame.this,stats.class);
        i.putExtra("playerscore",pscore);
        i.putExtra("aiscore",ascore);
        i.putExtra("gamesplayed",gamesplayed);
        i.putExtra("user",user);
        startActivity(i);

    }


    public void first_move() {
        AlertDialog.Builder draw=new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_Dialog);
        draw.setMessage("Who will start?");
        draw.setTitle("Select Option");
        draw.setCancelable(false);
        draw.setIcon(R.drawable.question);
        draw.setPositiveButton(
                "Player",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                }

        );

        draw.setNegativeButton(
                "Opponent",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
generate_move();
                        dialog.cancel();
                    }
                }

        );



        AlertDialog showmessage=draw.create();
        showmessage.show();

    }

    public int count_tiles() {
        int count=0;
        listiles=new ArrayList<>();

        try {
            for(int a=0;a<9;a++){
                if(buttonsymbols[a].equals(" ")){
                    count++;

                }
                listiles.add(buttonsymbols[a]);


            }


        }

        catch (Exception e){
            Toast.makeText(null,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
        return count;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(startgame.this,MainActivity.class));
    }
}
