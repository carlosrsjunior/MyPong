package com.carlosjunior.mypong.entities;

import android.graphics.Rect;

import com.carlosjunior.mypong.constants.PongConstants;

/**
 * Created by Carlos on 12/07/2015.
 */
public class Racket {

    private Position position;
    private int width;
    private int height;


    public Racket() {
        position = new Position(PongConstants.RACKET_INITIAL_X_POSITION, PongConstants.RACKET_INITIAL_Y_POSITION);
        width = PongConstants.RACKET_WIDTH;
        height= PongConstants.RACKET_HEIGHT;
    }

    public void move(Rect bounds) {

        // TODO
    }

    public int getLeft() {
        return position.getX();
    }

    public int getTop() {
        return position.getY();
    }

    public int getRight() {
        return position.getX() + width;
    }

    public int getBottom() {
        return position.getY() + height;
    }


}
