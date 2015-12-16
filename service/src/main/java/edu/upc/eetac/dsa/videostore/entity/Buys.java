package edu.upc.eetac.dsa.videostore.entity;

import org.glassfish.jersey.linking.InjectLinks;

/**
 * Created by marc on 16/12/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Buys {
    @InjectLinks({})
    private String userid;
    private String movieid;
    private long datebuy;
    private int dowmloadedtimes;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getDatebuy() {
        return datebuy;
    }

    public void setDatebuy(long datebuy) {
        this.datebuy = datebuy;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public int getDowmloadedtimes() {
        return dowmloadedtimes;
    }

    public void setDowmloadedtimes(int dowmloadedtimes) {
        this.dowmloadedtimes = dowmloadedtimes;
    }
}

