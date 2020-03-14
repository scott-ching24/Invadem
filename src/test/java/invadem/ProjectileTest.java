package invadem;

import java.util.ArrayList;
import processing.core.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectileTest extends App {


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
     * Test for the Projectile's constructor. The Projectile should have a PImage with dimensions according to the type of Projectile that it is.
     */
    public void testProjectileConstructor() {
        Projectile testProjectile = new Projectile(getGameTank(), getProjectileImage(), 50, 50, 1, 3, new int[] {0, -1}, 1);
        assertEquals(testProjectile.getX(), 50);
        assertEquals(testProjectile.getY(), 50);
        assertEquals(testProjectile.getDamage(), 1);
        Projectile secondProjectile = new Projectile(getGameTank(), getProjectileImage(), 50, 50, 2, 5, new int[] {0, -1}, 3);
        assertEquals(secondProjectile.getX(), 50);
        assertEquals(secondProjectile.getY(), 50);
        assertEquals(secondProjectile.getDamage(), 3);
    }

    @Test
    /**
     * Tests whether the projectile reacts to a friendly object differently to when it interacts with a non-friendly object. This is important so that invaders do not
     * hit each other with friendly fire.
     */
    public void testProjectileCollision() {
        Tank testTank = getGameTank();
        InvaderSwarm testSwarm = getSwarm();
        Projectile testProjectile = new Projectile(testTank, getProjectileImage(), 50, 50, 1, 3, new int[] {0, -1}, 1);
        assertFalse(testProjectile.gotHit(testTank));
        assertTrue(testProjectile.gotHit(testSwarm));
    }

    @Test
    /**
     * Test whether the test projectile moves in accordance with the velocity that it is given when it is initiated.
     */
    public void testTick() {
        Projectile testProjectile = new Projectile(getSwarm(), getProjectileImage(), 50, 50, 1, 3, new int[] {0, -1}, 1);
        Integer beforeX = testProjectile.getX();
        Integer beforeY = testProjectile.getY();
        testProjectile.tick();
        assertEquals(testProjectile.getX(), 50);
        assertEquals(testProjectile.getY(), 49);
    }


}
