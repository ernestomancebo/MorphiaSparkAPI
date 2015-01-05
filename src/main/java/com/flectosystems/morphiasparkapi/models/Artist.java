package com.flectosystems.morphiasparkapi.models;

import org.mongodb.morphia.annotations.Entity;

import java.util.Arrays;

/**
 * Represents an Artist and it's genres.
 * <p/>
 * Created by ernesto on 1/4/15.
 */
@Entity("artists")
public class Artist extends BaseEntity {

    protected String Name;
    protected String[] genres;

    public Artist() {
        super();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "Name='" + Name + '\'' +
                ", genres=" + Arrays.toString(genres) +
                '}';
    }
}
