package com.carlosjunior.mypong.entities;

import android.graphics.Rect;
import android.util.Log;

import com.carlosjunior.mypong.constants.PongConstants;

/**
 * Created by Carlos on 05/07/2015.
 */
public class Ball {

    private Position position;

    public Ball() {
        position = new Position(PongConstants.INITIAL_X_POSITION, PongConstants.INITIAL_Y_POSITION);
    }

    int i = 1;
    int increment = -5;
    int direcao = -1;
    public void move() {

        if (i == 1 || i == 200) {
            direcao = direcao * (-1);
            increment = increment * (-1);
        }

        i = i + direcao;

        position.setX(position.getX() + increment);
        position.setY(position.getY() + increment);
    }


    int incrementX = -15;
    int incrementY = -6;
    int direcaoX = -1;
    int direcaoY = -1;

    public void move(Rect bounds) {

        int x = position.getX();
        int y = position.getY();

        if (x <= bounds.left || x >= bounds.right) {
            direcaoX *= -1;
            incrementX *= -1;
        }
        if (y <= bounds.top || y >= bounds.bottom) {
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

}
