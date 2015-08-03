package com.carlosjunior.mypong.entities;

/**
 * Created by Carlos on 30/07/2015.
 */
public enum BallStatus {

    STARTING(0, "STARTING"),
    MOVING(1, "MOVING"),
    REBATED(2, "REBATED"),
    LOST(3, "LOST"),
    STOPPED(4, "STOP");

    private int code;
    private String description;

    BallStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
