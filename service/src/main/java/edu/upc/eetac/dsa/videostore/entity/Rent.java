package edu.upc.eetac.dsa.videostore.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.glassfish.jersey.linking.InjectLinks;

/**
 * Created by marc on 16/12/15.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Rent {
    @InjectLinks({})
    private String userid;
    private String movieid;
    private long daterent;
    private int viewtimes;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public long getDaterent() {
        return daterent;
    }

    public void setDaterent(long daterent) {
        this.daterent = daterent;
    }

    public int getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(int viewtimes) {
        this.viewtimes = viewtimes;
    }
}
