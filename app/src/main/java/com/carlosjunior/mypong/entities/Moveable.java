package com.carlosjunior.mypong.entities;

import android.graphics.Rect;

/**
 * Created by Carlos on 05/07/2015.
 */
public abstract class Moveable {

    protected Position position;
    private int incrementX;
    private int incrementY;
    private int direcaoX = -1;  // positivo para direita, negativo para esquerda
    private int direcaoY = -1;  // positivo para cima, negativo para baixo

    public Moveable(Position position, int incrementX, int incrementY) {
        this.position = position;
        this.incrementX = incrementX;
        this.incrementY = incrementY;
    }

    public void move(Rect bounds) {

        checkDirection(bounds);

        position.setX(position.getX() + (incrementX * direcaoX));
        position.setY(position.getY() + (incrementY * direcaoY));
    }

    protected void changeDirectionX() {
        direcaoX *= -1;
    }

    protected void changeDirectionY() {
        direcaoY *= -1;
    }

    protected void stopDirectionX() {
        direcaoX = 0;
    }

    public void setDirectionXToLeft() {
        direcaoX = -1;
    }

    public void setDirectionXToRight() {
        direcaoX = 1;
    }

    protected abstract void checkDirection(Rect bounds);

    public abstract int getLeft();

    public abstract int getTop();

    public abstract int getRight();

    public abstract int getBottom();

}
