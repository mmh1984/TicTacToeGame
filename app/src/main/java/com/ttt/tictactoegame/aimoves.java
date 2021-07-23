package com.ttt.tictactoegame;

import java.util.ArrayList;

/**
 * Created by MSI-PC on 04-Nov-16.
 */

public class aimoves {
    ArrayList<String> tiles;
    public aimoves(ArrayList<String> t){
       tiles=new ArrayList<>();
        tiles=t;


    }
    public int get_size() {
        return tiles.size();
    }

    public int get_index() {

        int size= tiles.size();
        ArrayList<Integer> available=new ArrayList<>();
        for(int x=0;x<size;x++){
            if(tiles.get(x).equals(" ")){
               available.add(x);
            }
        }

        int random= (int) (Math.random()* available.size());
        return available.get(random);

    }

    public boolean check_winner(String symbol,int x,int y,int z) {
       Boolean flag=false;
        if (tiles.get(x).toString().equals(symbol) && tiles.get(y).toString().equals(symbol) && tiles.get(z).toString().equals(symbol)) {
            flag=true;

        }

        return flag;
    }

}
