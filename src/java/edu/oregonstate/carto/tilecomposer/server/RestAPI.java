/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.tilecomposer.server;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
@Path("tiles")
public class RestAPI {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestAPI
     */
    public RestAPI() {
    }

    /**
     * Retrieves representation of an instance of edu.oregonstate.carto.tilecomposer.server.RestAPI
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getXml() {
        return "do something!";
    }

    /**
     * PUT method for updating or creating an instance of RestAPI
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
