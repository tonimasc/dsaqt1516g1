package edu.upc.eetac.dsa.videostore;

import org.glassfish.jersey.server.ResourceConfig;


public class VideostoreResourceConfig extends ResourceConfig {
    public VideostoreResourceConfig () {
        packages("edu.upc.eetac.dsa.videostore");
        packages("edu.upc.eetac.dsa.videostore.auth");

    }
}
