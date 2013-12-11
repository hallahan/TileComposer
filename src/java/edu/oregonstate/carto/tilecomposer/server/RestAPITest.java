/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.tilecomposer.server;

import edu.oregonstate.carto.tilecomposer.composite.Layer;
import edu.oregonstate.carto.tilecomposer.composite.Map;
import edu.oregonstate.carto.tilemanager.FileTileSet;
import edu.oregonstate.carto.tilemanager.HTTPTileSet;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author nick
 */
@Path("test/{z}/{x}/{y}.png")
public class RestAPITest {

    /*
     * The application actually gets loaded into GlassFish and executes in a
     * different directory than the project itself. This means that we need to
     * specify a root directory for our test imagery.
     */
    private static final String PROJ_DIR = "C:/Users/nick/Google Drive/algo-geo/pseudo-natural/TileComposer/";
    
    @Context
    private UriInfo context;
    
    private Map map;
    private HTTPTileSet esriSatelliteSet, watercolorSet;
    private FileTileSet glacierMask;
    private Layer layer1, layer2;

    /**
     * Creates a new instance of RestAPITest
     */
    public RestAPITest() {
        map = new Map();
        esriSatelliteSet = new HTTPTileSet("http://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}");
        watercolorSet = new HTTPTileSet("http://tile.stamen.com/watercolor/{z}/{x}/{y}.png");
        try {
            glacierMask = new FileTileSet(PROJ_DIR + "data/TMS_tiles_MountHood/glacierMask", true);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RestAPITest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        layer1 = new Layer();
        layer2 = new Layer();
        layer1.setImageTileSet(esriSatelliteSet);
        layer2.setImageTileSet(watercolorSet);
        layer2.setMaskTileSet(glacierMask);
        map.addLayer(layer1);
        map.addLayer(layer2);
    }

    /**
     * This will generate a demo map.
     * 
     * If you are having problems, maybe try getting just one tile for starters:
     * http://localhost:8080/tiles/test/12/663/1467.png
     * 
     * @param z
     * @param x
     * @param y
     * @return 
     */
    @GET
    @Produces("image/png")
    public Response generateTile(
            @PathParam("z") int z,
            @PathParam("x") int x,
            @PathParam("y") int y ) {

        BufferedImage img = map.generateTile(z, x, y);

        return Response.ok(img).build();
    }
}
