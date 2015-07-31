package com.carlosjunior.mypong.entities;

/**
 * Created by Carlos on 30/07/2015.
 */
public enum BallStatus {

    MOVING(0, "MOVING"),
    REBATED(1, "REBATED"),
    LOST(2, "LOST"),
    STOPPED(3, "STOP");

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
