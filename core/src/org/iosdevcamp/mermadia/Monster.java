package org.iosdevcamp.mermadia;

/**
 * Created by matto_000 on 7/24/2016.
 */
public class Monster extends NPC {
    Key key;
    Chest chest;
    public Monster(int width, int height, Key key, Chest chest){
        super(width, height);
        this.key = key;
        this.chest = chest;
    }

    @Override
    public double move(){
        double turn = super.move();
        if(key.getX() == this.getX() && key.getY() == this.getY())
            key.relocate();
        if(chest.getX() == this.getX() && chest.getY() == this.getY())
            chest.relocate();
        return turn;
    }
}
