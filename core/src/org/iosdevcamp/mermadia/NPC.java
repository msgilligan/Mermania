package org.iosdevcamp.mermadia;

import java.util.Random;

/**
 * Created by matto_000 on 7/24/2016.
 */
public class NPC {
    private final int RANGE = 10;
    private int x, y, width, height;
    private double angle;
    private Random r;

    public NPC (int width, int height){
        this.width = width;
        this.height = height;
        r = new Random();
        x = r.nextInt(MermaniaGame.WORLD_WIDTH);
        y = r.nextInt(MermaniaGame.WORLD_HEIGHT);
        angle = Math.PI;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


    public double move(){
        double turn;
        turn = r.nextDouble() - .5;
        angle += turn;
        x += (Math.cos(angle) * RANGE);
        y += (Math.sin(angle) * RANGE);
        if(x < 0)
            x = 0;
        if(x > MermaniaGame.WORLD_WIDTH - width)
            x = MermaniaGame.WORLD_WIDTH - width;
        if(y < 0)
            y = 0;
        if(y > MermaniaGame.WORLD_HEIGHT/2 - height)
            y = MermaniaGame.WORLD_HEIGHT/2 - height;
        return turn;
    }
}
