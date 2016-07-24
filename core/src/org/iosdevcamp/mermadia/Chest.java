package org.iosdevcamp.mermadia;

import java.util.Random;

/**
 * Created by matto_000 on 7/24/2016.
 */
public class Chest {
    private boolean found;
    private boolean keyFound;
    private int x, y, width, height;
    Random r;

    public Chest(int width, int height){
        found = false;
        keyFound = false;
        r = new Random();
        x = r.nextInt(MermaniaGame.WORLD_WIDTH);
        y = r.nextInt(MermaniaGame.WORLD_HEIGHT) - MermaniaGame.WORLD_HEIGHT / 2 - height;
        this.width = width;
        this.height = height;
    }
    public Chest(int x, int y, int width, int height){
        found = false;
        keyFound = false;
        r = new Random();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
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
