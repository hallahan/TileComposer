/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.tilecomposer.composite;

import edu.oregonstate.carto.tilecomposer.composite.Map;
import edu.oregonstate.carto.tilecomposer.composite.Layer;
import edu.oregonstate.carto.tilemanager.FileTileSet;
import edu.oregonstate.carto.tilemanager.HTTPTileSet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
public class MapTest {

    public MapTest() {
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
     * Test of generateTile method, of class Map.
     */
    @Test
    public void testGenerateTileOSM() {
        System.out.println("testGenerateTileOSM");
        URL url = null;
        Map map = new Map();
        Layer layer1 = new Layer();

        HTTPTileSet imageTileSet = new HTTPTileSet("http://tile.openstreetmap.org/{z}/{x}/{y}.png");
        layer1.setImageTileSet(imageTileSet);
        map.addLayer(layer1);

        int z = 12;
        int x = 663;
        int y = 1467;
        BufferedImage result = map.generateTile(z, x, y);
        try {
            ImageIO.write(result, "png", new File("test-output/testGenerateTileOSM.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testGenerateTileMapBox() {
        System.out.println("testGenerateTileMapBox");
        URL url = null;
        Map map = new Map();
        Layer layer1 = new Layer();

        HTTPTileSet imageTileSet = new HTTPTileSet("http://b.tiles.mapbox.com/v3/tmcw.map-j5fsp01s/{z}/{x}/{y}.png");
        layer1.setImageTileSet(imageTileSet);
        map.addLayer(layer1);

        int z = 12;
        int x = 663;
        int y = 1467;
        BufferedImage result = map.generateTile(z, x, y);
        try {
            ImageIO.write(result, "png", new File("test-output/testGenerateTileMapBox.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testGenerateTileEsriSatellite() {
        System.out.println("testGenerateTileEsriSatellite");
        URL url = null;
        Map map = new Map();
        Layer layer1 = new Layer();

        HTTPTileSet imageTileSet = new HTTPTileSet("http://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}");
        layer1.setImageTileSet(imageTileSet);
        map.addLayer(layer1);

        int z = 12;
        int x = 663;
        int y = 1467;
        BufferedImage result = map.generateTile(z, x, y);
        try {
            ImageIO.write(result, "png", new File("test-output/testGenerateTileEsriSatellite.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testGenerateTileTwoLayers() {
        System.out.println("testGenerateTileTwoLayers");
        URL url = null;
        Map map = new Map();
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();

        HTTPTileSet imageTileSet1 = new HTTPTileSet("http://tile.openstreetmap.org/{z}/{x}/{y}.png");
        HTTPTileSet imageTileSet2 = new HTTPTileSet("http://services.arcgisonline.com/ArcGIS/rest/services/Ocean_Basemap/MapServer/tile/{z}/{y}/{x}");
        layer1.setImageTileSet(imageTileSet1);
        layer2.setImageTileSet(imageTileSet2);
        layer2.setOpacity(0.9f);
        map.addLayer(layer1);
        map.addLayer(layer2);

        int z = 12;
        int x = 663;
        int y = 1467;
        BufferedImage result = map.generateTile(z, x, y);
        try {
            ImageIO.write(result, "png", new File("test-output/testGenerateTileTwoLayers.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGenerateTileSeveralLayers() throws MalformedURLException {
        System.out.println("testGenerateTileSeveralLayers");
        URL url = null;
        Map map = new Map();
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();

        HTTPTileSet esriSatelliteSet = new HTTPTileSet("http://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}");
        HTTPTileSet stamenWatercolorSet = new HTTPTileSet("http://tile.stamen.com/watercolor/{z}/{x}/{y}.png");
        FileTileSet glacierMask;

        glacierMask = new FileTileSet("data/TMS_tiles_MountHood/glacierMask", true);

        layer1.setImageTileSet(esriSatelliteSet);
        layer2.setImageTileSet(stamenWatercolorSet);
        layer2.setMaskTileSet(glacierMask);
        map.addLayer(layer1);
        map.addLayer(layer2);

        int z = 12;
        int x = 663;
        int y = 1467;
        BufferedImage result = map.generateTile(z, x, y);
        try {
            ImageIO.write(result, "png", new File("test-output/testGenerateTileSeveralLayers.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}