package edu.upc.eetac.dsa.videostore;

import edu.upc.eetac.dsa.videostore.entity.VideostoreRootAPI;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@InjectLinks({
        @InjectLink(resource = VideostoreAPIResource.class, style = InjectLink.Style.ABSOLUTE,
                rel = "self bookmark home", title = "Videostore Root API")
})
@Path("/")
public class VideostoreAPIResource {
    @GET
    @Produces(VideostoreMediaType.VIDEOSTORE_ROOT)
    public VideostoreRootAPI getRootAPI(){
        VideostoreRootAPI videoStoreRootAPI = new VideostoreRootAPI();

        return videoStoreRootAPI;
    }
}
