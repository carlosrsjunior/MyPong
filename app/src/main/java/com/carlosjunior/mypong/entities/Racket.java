package com.carlosjunior.mypong.entities;

import android.graphics.Rect;

import com.carlosjunior.mypong.constants.PongConstants;

/**
 * Created by Carlos on 12/07/2015.
 */
public class Racket extends Moveable {

    private int width;
    private int height;

    public Racket(int initialXPosition, int initialYPosition) {
        super(new Position(initialXPosition, initialYPosition), PongConstants.RACKET_MOV_X_INCREMENT, PongConstants.RACKET_MOV_Y_INCREMENT);
        width = PongConstants.RACKET_WIDTH;
        height = PongConstants.RACKET_HEIGHT;

    }

    public void reset() {
        super.reset();
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