package com.carlosjunior.mypong.entities;

import android.graphics.Rect;

import com.carlosjunior.mypong.constants.PongConstants;

/**
 * Created by Carlos on 12/07/2015.
 */
public class Racket extends Moveable {

    private int width;
    private int height;

    public Racket() {
        super(new Position(PongConstants.RACKET_INITIAL_X_POSITION, PongConstants.RACKET_INITIAL_Y_POSITION), 10, 0);
        width = PongConstants.RACKET_WIDTH;
        height = PongConstants.RACKET_HEIGHT;
    }

    protected void checkDirection(Rect bounds) {

        if (getLeft() <= bounds.left) {
            position.setX(bounds.left + 1);
            stopDirectionX();
        }

        if (getRight() >= bounds.right) {
            position.setX(bounds.right - width - 1);
            stopDirectionX();
            //changeDirectionX();
        }
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