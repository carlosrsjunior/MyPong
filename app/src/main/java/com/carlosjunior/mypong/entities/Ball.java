package com.carlosjunior.mypong.entities;

import android.graphics.Rect;
import android.util.Log;

import com.carlosjunior.mypong.constants.PongConstants;

/**
 * Created by Carlos on 05/07/2015.
 */
public class Ball {

    private int radius;
    private Position position;
    private int incrementX = -19;
    private int incrementY = -15;
    private int direcaoX = -1;  // positivo para direita, negativo para esquerda
    private int direcaoY = -1;  // positivo para cima, negativo para baixo

    public Ball() {
        position = new Position(PongConstants.BALL_INITIAL_X_POSITION, PongConstants.BALL_INITIAL_Y_POSITION);
        radius = PongConstants.BALL_RADIUS;
    }

    public void move(Rect bounds) {

        int x = position.getX();
        int y = position.getY();

        if ((x - radius) <= bounds.left || (x + radius) >= bounds.right) {
            direcaoX *= -1;
            incrementX *= -1;
        }
        if ((y - radius)<= bounds.top || (y + radius) >= bounds.bottom) {
            direcaoY *= -1;
            incrementY *= -1;
        }

   //     Log.i("crsj", bounds.left + "," + bounds.right + "," + bounds.top + "," + bounds.bottom);
        position.setX(x + incrementX);
        position.setY(y + incrementY);
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
}
