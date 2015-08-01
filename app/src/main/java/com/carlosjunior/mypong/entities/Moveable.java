package com.carlosjunior.mypong.entities;

import android.graphics.Rect;

/**
 * Created by Carlos on 05/07/2015.
 */
public abstract class Moveable {

    private Position initialPosition;
    protected Position position;
    private int incrementX;
    private int incrementY;
    private int direcaoX = -1;  // positivo para direita, negativo para esquerda
    private int direcaoY = -1;  // positivo para cima, negativo para baixo

    public Moveable(Position initialPosition, int incrementX, int incrementY) {
        this.initialPosition = initialPosition;
        this.incrementX = incrementX;
        this.incrementY = incrementY;
        reset();
    }

    public void move(Rect bounds) {

        checkDirection(bounds);

        position.setX(position.getX() + (incrementX * direcaoX));
        position.setY(position.getY() + (incrementY * direcaoY));
    }

    public void reset() {
        position = new Position(initialPosition.getX(), initialPosition.getY());
        setDirectionXToLeft();
        setDirectionYToUp();
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

    public void setDirectionYToUp() {
        direcaoY = -1;
    }

    public void setDirectionXToDown() {
        direcaoY = 1;
    }

    protected abstract void checkDirection(Rect bounds);

    public abstract int getLeft();

    public abstract int getTop();

    public abstract int getRight();

    public abstract int getBottom();

}
