package edu.upc.eetac.dsa.videostore;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;


public class VideostoreResourceConfig extends ResourceConfig {
    public VideostoreResourceConfig () {
        packages("edu.upc.eetac.dsa.videostore");
        packages("edu.upc.eetac.dsa.videostore.auth");
        packages("edu.upc.eetac.dsa.beeter.cors");
        register(RolesAllowedDynamicFeature.class);
        //register(DeclarativeLinkingFeature.class);

    }
}
