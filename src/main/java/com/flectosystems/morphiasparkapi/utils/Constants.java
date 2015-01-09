package com.flectosystems.morphiasparkapi.utils;

/**
 * Created by Ernesto Mancebo T on 1/8/15.
 */
public final class Constants {

    public static final String API_URL = "/api/";
    public static final String CONCERT_API_URL = API_URL.concat("concert/");
    public static final String ARTIST_API_URL = API_URL.concat("artist/");
    public static final String VENUE_API_URL = API_URL.concat("venue/");

    public static final String JSON_MIME = "application/json";

    public static final int BAD_REQUEST = 400;

    private Constants() {
    }
}
