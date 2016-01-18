package edu.upc.eetac.dsa.videostore;

public interface VideostoreMediaType {
    public final static String VIDEOSTORE_AUTH_TOKEN = "application/vnd.dsa.videostore.auth-token+json";
    public final static String VIDEOSTORE_USER = "application/vnd.dsa.videostore.user+json";
    public final static String VIDEOSTORE_MOVIE = "application/vnd.dsa.videostore.movie+json";
    public final static String VIDEOSTORE_MOVIES_COLLECTION = "application/vnd.dsa.videostore.moviescollection+json";
    public final static String VIDEOSTORE_ROOT = "application/vnd.dsa.videostore.root+json";
    public final static String VIDEOSTORE_ERROR = "application/vnd.dsa.videostore.error+json";
    public final static String VIDEOSTORE_BUY = "application/vnd.dsa.videostore.buy+json";
    public final static String VIDEOSTORE_RENT = "application/vnd.dsa.videostore.rent+json";
    public final static String VIDEOSTORE_MOVIES_USER = "application/vnd.dsa.videostore.moviesuser+json";
    public final static String VIDEOSTORE_RESOURCES = "application/vnd.dsa.videostore.resources+json";
}
