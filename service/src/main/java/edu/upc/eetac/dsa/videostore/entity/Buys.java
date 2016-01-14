package edu.upc.eetac.dsa.videostore.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Buys {
    @InjectLinks({})
    private List<Link> links;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}

