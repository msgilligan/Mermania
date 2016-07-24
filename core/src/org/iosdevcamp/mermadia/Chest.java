package org.iosdevcamp.mermadia;

import java.util.Random;

/**
 * Created by matto_000 on 7/24/2016.
 */
public class Chest {
    private boolean found;
    private boolean keyFound;
    private int x, y;
    Random r;

    public Chest(){
        found = false;
        keyFound = false;
        r = new Random(0);
        x = r.nextInt(MermaniaGame.WORLD_WIDTH);
        y = r.nextInt(MermaniaGame.WORLD_HEIGHT);
    }
    public Chest(int x, int y){
        found = false;
        keyFound = false;
        r = new Random(0);
        this.x = x;
        this.y = y;
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

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setFound(){
        if(keyFound)
            found = true;
    }

    public void relocate(){
        x = r.nextInt(MermaniaGame.WORLD_WIDTH);
        y = r.nextInt(MermaniaGame.WORLD_HEIGHT);
    }
}
