package edu.upc.eetac.dsa.videostore.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)


public class Resources {
    @InjectLinks({})
    private List<Link> links;
    private String idmovie;
    private String resourcesmovie;
    private String resourcescover;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(String idmovie) {
        this.idmovie = idmovie;
    }

    public String getResourcesmovie() {
        return resourcesmovie;
    }

    public void setResourcesmovie(String resourcesmovie) {
        this.resourcesmovie = resourcesmovie;
    }

    public String getResourcescover() {
        return resourcescover;
    }

    public void setResourcescover(String resourcescover) {
        this.resourcescover = resourcescover;
    }
}
