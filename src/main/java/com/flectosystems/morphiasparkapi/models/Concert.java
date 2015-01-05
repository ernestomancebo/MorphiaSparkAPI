package com.flectosystems.morphiasparkapi.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

import java.util.Date;

/**
 * Represents a concert. As every concert, it has an {@link Artist} in a {@link Venue}.
 * <p/>
 * Created by ernesto on 1/4/15.
 */
public class Concert extends BaseEntity {

    protected String name;
    protected Date concertDate;
    @Embedded
    protected Venue venue;
    @Reference
    protected Artist artist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Date getConcertDate() {
        return concertDate;
    }

    public void setConcertDate(Date concertDate) {
        this.concertDate = concertDate;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "name='" + name + '\'' +
                ", venue=" + venue +
                ", artist=" + artist +
                ", concerDate=" + concertDate +
                '}';
    }
}
