package com.montecarlo.util;

/**
 * This class holds different portfolio that are used in application
 * Created by lmahabaleshwara on 2/4/17.
 */
public enum PortfolioType {

    CONSERVATIVE("Conservative"),
    AGGRESSIVE("Aggressive");

    PortfolioType(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

}
