package com.flectosystems.morphiasparkapi.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.Date;
import java.util.List;

/**
 * Represents a concert. As every concert, it has an {@link Artist} in a {@link Venue}.
 * <p/>
 * Created by ernesto on 1/4/15.
 */
@Entity("concerts")
public class Concert extends BaseEntity {

    protected String name;
    protected String description;
    protected Date concertDate;
    @Embedded
    protected ConcertPrice prices;
    @Reference
    protected Venue venue;
    @Reference("concert_artists")
    protected List<Artist> artist;

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

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public Date getConcertDate() {
        return concertDate;
    }

    public void setConcertDate(Date concertDate) {
        this.concertDate = concertDate;
    }

    public ConcertPrice getPrices() {
        return prices;
    }

    public void setPrices(ConcertPrice prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "name='" + name + '\'' +
                ", concertDate=" + concertDate +
                ", prices=" + prices +
                ", venue=" + venue +
                ", artist=" + artist +
                '}';
    }
}
