package edu.upc.eetac.dsa.videostore.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Movie {
    @InjectLinks({})
    private List<Link> links;
    private String id;
    private String title;
    private String genre;
    private int year;
    private String director;
    private String description;
    private int votes;
    private int nummaxdownloads;
    private int maxtimeshow;
    private int rentcost;
    private int buycost;
    private long timeadded;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getNummaxdownloads() {
        return nummaxdownloads;
    }

    public void setNummaxdownloads(int nummaxdownloads) {
        this.nummaxdownloads = nummaxdownloads;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRentcost() {
        return rentcost;
    }

    public void setRentcost(int rentcost) {
        this.rentcost = rentcost;
    }

    public int getMaxtimeshow() {
        return maxtimeshow;
    }

    public void setMaxtimeshow(int maxtimeshow) {
        this.maxtimeshow = maxtimeshow;
    }

    public int getBuycost() {
        return buycost;
    }

    public void setBuycost(int buycost) {
        this.buycost = buycost;
    }

    public long getTimeadded() {
        return timeadded;
    }

    public void setTimeadded(long timeadded) {
        this.timeadded = timeadded;
    }

}
