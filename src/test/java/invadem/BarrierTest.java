package invadem;

import java.util.ArrayList;
import processing.core.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class BarrierTest extends App {
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
     * Tests the construction of the Barrier object by making sure that there are 7 BarrierPart components.
     */
    public void testBarrierConstruction() {
        Barrier newBarrier = getBarrier();
        assertTrue(newBarrier.getAllBarriers().size() == 7);
        for (int i=0;i<newBarrier.getAllBarriers().size();i++) {
            assertTrue(newBarrier.getAllBarriers().get(i) instanceof BarrierPart);
        }
    }

    @Test
    /**
     * A barrier is made out of seven BarrierPart objects. If all seven BarrierPart objects are destroyed, then the health of this Barrier becomes zero and it is deleted
     * from the ArrayList of game objects in the App class.
     */
    public void testNotDestroyed() {
        Barrier newBarrier = getBarrier();
        assertNotEquals(newBarrier.getHealth(), 0);
        newBarrier.setAllBarriers(new ArrayList<BarrierPart>());
        assertEquals(newBarrier.getHealth(), 0);
    }

    @Test
    /**
     * Tests to see whether a Barrier is hit or not when a projectile overlaps its location. True should be returned from checkHit if the entire barricade is destroyed.
     */
    public void testCheckHit() {
        Barrier newBarrier = getBarrier();
        int barrierSize = newBarrier.getAllBarriers().size();
        Projectile hitProjectile = new Projectile(getGameTank(), getProjectileImage(), newBarrier.getX(), newBarrier.getY(), 1, 3, new int[] {0, -1}, 1);
        Boolean whetherDestroyed = newBarrier.checkHit(hitProjectile);
        assertTrue(whetherDestroyed); // Assert whether the barrier was hit or not.
        assertEquals(newBarrier.getAllBarriers().size(), barrierSize);
        Projectile powerProjectile = new Projectile(getGameTank(), getPowerProjectile(), newBarrier.getX(), newBarrier.getY(), 2, 5, new int[] {0, -1}, 3);
        Boolean whetherDestroyedNow = newBarrier.checkHit(powerProjectile);
        assertTrue(whetherDestroyedNow);
        assertEquals(newBarrier.getAllBarriers().size(), barrierSize-1);
    }
}
