/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.tilecomposer.server;

import edu.oregonstate.carto.tilecomposer.composite.Layer;
import edu.oregonstate.carto.tilecomposer.composite.Map;
import edu.oregonstate.carto.tilemanager.FileTileSet;
import edu.oregonstate.carto.tilemanager.HTTPTileSet;
import edu.oregonstate.carto.tilemanager.TileSetTest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nick
 */
public class RestAPITestTest {
    
    public RestAPITestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generateTile method, of class RestAPITest.
     */
//    @Test
//    public void testGenerateTile() {
//        System.out.println("generateTile");
//        int z = 12;
//        int x = 663;
//        int y = 1467;
//        RestAPITest instance = new RestAPITest();
//        Response expResult = null;
//        Response result = instance.generateTile(z, x, y);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    @Test
    public void testGetMtHoodTile() {
        System.out.println("===testGetMtHoodTile===");
        
        String PROJ_DIR = "C:/Users/nick/Google Drive/algo-geo/pseudo-natural/TileComposer/";
        
        int z = 12;
        int x = 663;
        int y = 1467;
                
        Map map;
        HTTPTileSet esriSatelliteSet, watercolorSet;
        FileTileSet glacierMask = null;
        Layer layer1, layer2;
        
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
        
        BufferedImage img = map.generateTile(z, x, y);
        
        File f = new File("test-output/testGetMtHoodTile.png");
        try {
            ImageIO.write(img, "png", f);
            System.out.println("Wrote: test-output/testGetMtHoodTile.png");
        } catch (IOException ex) {
            Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}