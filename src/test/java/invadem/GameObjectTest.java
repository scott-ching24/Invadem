package invadem;

import java.util.ArrayList;
import processing.core.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameObjectTest extends App {
    @Before
    /**
     * Run the sketch for the App class in order to test the Barrier class.
     */
    public void run() {
        PApplet.runSketch(new String[] {"--location=0,0", " "}, this);
        delay(5000);
    }

    @Test
    /**
     * Tests all the getter methods of the GameObject class.
     */
    public void testGetters() {
        GameObject testTank = new Tank(loadImage("src/main/resources/tank1.png"), 250, 255, 22, 16, new int[] {0, 1});
        assertEquals(testTank.getX(), 250);
        assertEquals(testTank.getY(), 255);
        assertEquals(testTank.getWidth(), 22);
        assertEquals(testTank.getHeight(), 16);
        assertEquals(testTank.getVelocity()[0], 0);
        assertEquals(testTank.getVelocity()[1], 1);
        assertEquals(testTank.getHealth(), 3);
    }

    @Test
    /**
     * Test all the setter methods of the GameObject class.
     */
    public void testSetters() {
        GameObject testTank = new Tank(loadImage("src/main/resources/tank1.png"), 250, 255, 22, 16, new int[] {0, 1});
        testTank.setX(0);
        assertEquals(testTank.getX(), 0);
        testTank.setY(1);
        assertEquals(testTank.getY(), 1);
        testTank.setHealth(40);
        assertEquals(testTank.getHealth(), 40);
    }

    @Test
    /**
     * Tests the changeHealth() method of the GameObject class which should decrease the Tank's health in accordance with the damage parameter used.
     */
    public void testChangeHealth() {
        GameObject testTank = new Tank(loadImage("src/main/resources/tank1.png"), 250, 255, 22, 16, new int[] {0, 1});
        testTank.setHealth(50);
        testTank.changeHealth(30);
        assertEquals(testTank.getHealth(), 20);
        testTank.changeHealth(100);
        assertEquals(testTank.getHealth(), 0);
    }
}
