package com.carlosjunior.mypong.entities;

import android.graphics.Rect;
import android.util.Log;

import com.carlosjunior.mypong.constants.PongConstants;

/**
 * Created by Carlos on 05/07/2015.
 */
public class Ball extends Moveable{

    private int radius;
    private boolean ballRebated;

    public Ball() {
        super(new Position(PongConstants.BALL_INITIAL_X_POSITION, PongConstants.BALL_INITIAL_Y_POSITION), 15, 12);
        radius = PongConstants.BALL_RADIUS;
        ballRebated = false;
    }

    protected void checkDirection(Rect bounds) {

        if (getLeft() <= bounds.left || getRight() >= bounds.right) {
            changeDirectionX();
        }
        if (getTop() <= bounds.top || getBottom() >= bounds.bottom) {
            changeDirectionY();
        }
        if (ballRebated) {
            changeDirectionY();
            ballRebated = false;
        }

    }

    public boolean checkRebate(Racket racket) {

        ballRebated = false;
        if (getLeft() >= racket.getLeft() &&
                getRight() <= racket.getRight() &&
                getBottom() >= racket.getTop()) {
            ballRebated = true;
            correctPositionAfterRebate(racket);
        }
        return ballRebated;
    }

    private void correctPositionAfterRebate(Racket racket) {
        position.setY(position.getY() - (2 * (getBottom() - racket.getTop())));
    }

    public int getXPosition() {
        return position.getX();
    }

    public int getYPosition() {
        return position.getY();
    }

    public int getRadius() {
        return radius;
    }

    public int getLeft() {
        return position.getX() - radius;
    }

    public int getTop() {
        return position.getY() - radius;
    }

    public int getRight() {
        return position.getX() + radius;
    }

    public int getBottom() {
        return position.getY() + radius;
    }

}
