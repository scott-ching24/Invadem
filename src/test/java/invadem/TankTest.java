package invadem;

import java.util.ArrayList;
import processing.core.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TankTest extends App{

   @Before
	/**
     * Runs the App.java sketch for the rest of the test cases because this operation can be costly if re-written.
	 */
	public void run() {
		PApplet.runSketch(new String[] {"--location=0,0", " "}, this);
		delay(2000);
	}

	@Test
    /**
     * Tests the Tank object's constructor. The PImage should not be null, the Tank's starting health should be 3 and it canFire should be set to true.
     */
	public void testTankConstruction() {
	    Tank testTank = new Tank(loadImage("src/main/resources/tank1.png"), 250, 250, 22, 16, new int[] {0, 0});
	    assertEquals(testTank.getHealth(), 3);
	    assertTrue(testTank.getFire());
	    assertNotNull(testTank.getTankImage());
    }

    @Test
    /**
     * Tests whether a tank is positioned correctly in the game. This also tests whether the Tank is repositioned to fit the game boundaries
     * even if methods force it to go outside.
     */
    public void testTick() {
        Tank testTank = new Tank(loadImage("src/main/resources/tank1.png"), 250, 250, 22, 16, new int[] {0, 0});
        testTank.tick();
        assertEquals(testTank.getX(), 250);
        testTank.setX(700);
        testTank.tick();
        assertEquals(testTank.getX(), 438);
        testTank.setX(0);
        testTank.tick();
        assertEquals(testTank.getX(), 180);
    }

    @Test
    /**
     * Tests the adjustment of the Tank's velocity using the adjustVelocity method.
     */
    public void testAdjustVelocity() {
        Tank testTank = new Tank(loadImage("src/main/resources/tank1.png"), 250, 250, 22, 16, new int[] {0, 0});
        testTank.adjustVelocity(1);
        int[] tankVelocity = testTank.getVelocity();
        assertEquals(tankVelocity[0], 1);
        assertEquals(tankVelocity[1], 0);
    }

    @Test
    /**
     * Tests the canFire and getFire method of the tank to ensure that the getters and setters work.
     */
    public void testFire() {
        Tank testTank = new Tank(loadImage("src/main/resources/tank1.png"), 250, 250, 22, 16, new int[] {0, 0});
        Boolean canFire = testTank.getFire();
        testTank.setFire();
        assertNotEquals(canFire, testTank.getFire());
    }

}
