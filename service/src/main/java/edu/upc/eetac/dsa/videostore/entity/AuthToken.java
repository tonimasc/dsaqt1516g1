package edu.upc.eetac.dsa.videostore.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthToken {
    @InjectLinks({})
    private List<Link> links;
    private String userid;
    private String token;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getUsarioid() {
        return userid;
    }

    public void setUsarioid(String usarioid) {
        this.userid = usarioid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}



