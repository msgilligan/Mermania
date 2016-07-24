package org.iosdevcamp.mermadia;

import java.util.Random;

/**
 * Created by matto_000 on 7/24/2016.
 */
public class Key {
    private boolean found;
    private int x, y, width, height;
    Random r;

    public Key (int width, int height){
        found = false;
        r = new Random();
        x = r.nextInt(MermaniaGame.WORLD_WIDTH);
        y = r.nextInt(MermaniaGame.WORLD_HEIGHT)- MermaniaGame.WORLD_HEIGHT / 2 - height;
        this.width = width;
        this.height = height;
    }

    public Key (int x, int y, int width, int height){
        this.found = false;
        this.x = x;
        this.y = y;
        r = new Random();
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
