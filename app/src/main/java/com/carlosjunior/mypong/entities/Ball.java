package com.carlosjunior.mypong.entities;

import android.graphics.Rect;
import android.util.Log;

import com.carlosjunior.mypong.constants.PongConstants;

/**
 * Created by Carlos on 05/07/2015.
 */
public class Ball extends Moveable{

    private int radius;
    private BallStatus ballStatus;

    public Ball(int initialXPosition, int initialYPosition, int radius, int level) {
        super(new Position(initialXPosition, initialYPosition), defineIncrementX(radius, level), defineIncrementY(radius, level));
        this.radius = radius;
        ballStatus = BallStatus.MOVING;
    }

    private static int defineIncrementX(int radius, int level) {
        return (int) (radius * PongConstants.BALL_MOV_X_INCREMENT_PERCENT / level);
    }

    private static int defineIncrementY(int radius, int level) {
        return (int) (radius * PongConstants.BALL_MOV_Y_INCREMENT_PERCENT / level);
    }

    public void reset() {
        super.reset();
        ballStatus = BallStatus.MOVING;
    }

    protected void checkDirection(Rect bounds) {

        if (getLeft() <= bounds.left || getRight() >= bounds.right) {
            changeDirectionX();
        }
        if (getTop() <= bounds.top) {
            changeDirectionY();
        }
        if (getBottom() >= bounds.bottom) {
            ballStatus = BallStatus.STOPPED;
        }

        if (BallStatus.REBATED.equals(ballStatus)) {
            changeDirectionY();
            ballStatus = BallStatus.MOVING;
        }

    }

    public BallStatus checkMovement(Racket racket) {

        if (!BallStatus.LOST.equals(ballStatus) && !BallStatus.STOPPED.equals(ballStatus)) {
            ballStatus = BallStatus.MOVING;
            if (getBottom() >= racket.getTop()) {
                if (getRight() > racket.getLeft() && getLeft() < racket.getRight()) {
                    ballStatus = BallStatus.REBATED;
                    correctPositionAfterRebate(racket);
                } else {
                    ballStatus = BallStatus.LOST;
                }
            }
        }
        return ballStatus;
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
