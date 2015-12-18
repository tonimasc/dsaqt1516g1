package edu.upc.eetac.dsa.videostore.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public enum Role {
    registered,
    admin
}
