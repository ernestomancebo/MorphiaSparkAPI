package com.flectosystems.morphiasparkapi.models;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Represents prices of a concert
 * <p/>
 * Created by ernesto on 1/5/15.
 */
@Embedded
public class ConcertPrice {

    protected double regular;
    protected double vip;

    public double getRegular() {
        return regular;
    }

    public void setRegular(double regular) {
        this.regular = regular;
    }

    public double getVip() {
        return vip;
    }

    public void setVip(double vip) {
        this.vip = vip;
    }
}
