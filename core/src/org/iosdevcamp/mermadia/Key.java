package org.iosdevcamp.mermadia;

import java.util.Random;

/**
 * Created by matto_000 on 7/24/2016.
 */
public class Key {
    private boolean found;
    private int x, y;
    Random r;

    public Key (){
        found = false;
        r = new Random(0);
        x = r.nextInt(MermaniaGame.WORLD_WIDTH);
        y = r.nextInt(MermaniaGame.WORLD_HEIGHT);
    }

    public Key (int x, int y){
        this.found = false;
        this.x = x;
        this.y = y;
        r = new Random(0);
    }

    public boolean isFound(){
        return found;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setFound(){
        found = true;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public void relocate(){
        setX(r.nextInt(MermaniaGame.WORLD_WIDTH));
        setY(r.nextInt(MermaniaGame.WORLD_HEIGHT));
    }
}
