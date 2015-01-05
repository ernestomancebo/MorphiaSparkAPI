package com.flectosystems.morphiasparkapi.models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.utils.IndexDirection;

import java.util.Arrays;

/**
 * Entity that represents a Venue.
 * As attributes, it has a name and a location (2D index)
 * <p/>
 * Created by ernesto on 1/4/15.
 */
@Entity
public class Venue extends BaseEntity {

    protected String name;

    @Indexed(IndexDirection.GEO2D)
    protected double[] loc = new double[2];

    public Venue() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getLoc() {
        return loc;
    }

    public void setLoc(double[] loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", loc=" + Arrays.toString(loc) +
                '}';
    }
}
