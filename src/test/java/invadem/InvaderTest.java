package invadem;

import java.util.ArrayList;
import processing.core.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class InvaderTest extends App {
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
     * Tests the construction of a regular Invader class.
     */
    public void testInvaderConstruction() {
        PImage[] regularInvader = new PImage[] {loadImage("src/main/resources/invader1.png"), loadImage("src/main/resources/invader2.png")};
        Invader newInvader = new Invader(1, 3, getProjectileImage(), regularInvader, 250, 250, 16, 16, new int[] {1,0});
        for (int i=0;i<newInvader.allSprites.length;i++) {
            assertEquals(newInvader.allSprites[i], regularInvader[i]);
        }
        assertNotNull(newInvader.invaderImage);
        assertEquals(newInvader.getHealth(), 1);
        assertEquals(newInvader.projectileHeight, 3);
        assertEquals(newInvader.projectileWidth, 1);
    }

    @Test
    /**
     * Tests the constructor of the Power Invader class to ensure that an object of type Power Invader is created. It also tests that the attributes are correct.
     */
    public void testPowerInvaderConstruction() {
        PImage[] powerInvader = new PImage[] {loadImage("src/main/resources/invader1_power.png"), loadImage("src/main/resources/invader2_power.png")};
        Invader newInvader = new Invader(2, 5, getProjectileImage(), powerInvader, 250, 250, 16, 16, new int[] {1,0});
        for (int i=0;i<newInvader.allSprites.length;i++) {
            assertEquals(newInvader.allSprites[i], powerInvader[i]);
        }
        assertNotNull(newInvader.invaderImage);
        assertEquals(newInvader.getHealth(), 1);
        assertEquals(newInvader.projectileHeight, 5);
        assertEquals(newInvader.projectileWidth, 2);
    }

    @Test
    /**
     * Tests the tick function of the invader and ensures that the Invader is moving in the direction, at the right velocity and is in the correct position on the screen.
     */
    public void testTick() {
        PImage[] powerInvader = new PImage[] {loadImage("src/main/resources/invader1_power.png"), loadImage("src/main/resources/invader2_power.png")};
        Invader newInvader = new Invader(2, 5, getProjectileImage(), powerInvader, 250, 250, 16, 16, new int[] {1,0});
        int xPosition = newInvader.getX();
        int yPosition = newInvader.getY();
        newInvader.tick();
        assertEquals(newInvader.getX(), 251);
        assertEquals(newInvader.getY(), 250);
    }

    @Test
    /**
     * Tests whether the invader changes sprites as they are supposed to. This occurs every time an Invader has taken 30 steps in either direction or has completed a downward transition.
     */
    public void testChangeSprites() {
        PImage[] powerInvader = new PImage[] {loadImage("src/main/resources/invader1_power.png"), loadImage("src/main/resources/invader2_power.png")};
        Invader newInvader = new Invader(2, 5, getProjectileImage(), powerInvader, 250, 250, 16, 16, new int[] {1,0});
        PImage currentImage = newInvader.invaderImage;
        newInvader.changeSprites();
        assertNotEquals(newInvader.invaderImage, currentImage);
    }

    @Test
    /**
     * Tests whether or not the fireProjectile() method works for the Invader class. The Projectile object fired should correspond to the correct
     * Invader class that fired it. A PowerInvader for example should fire a different Projectile from a Regular Invader.
     */
    public void testFireProjectile() {
        PImage[] powerInvader = new PImage[] {loadImage("src/main/resources/invader1_power.png"), loadImage("src/main/resources/invader2_power.png")};
        Invader newInvader = new Invader(2, 5, getProjectileImage(), powerInvader, 250, 250, 16, 16, new int[] {1,0});
        InvaderSwarm friendly = getSwarm();
        Projectile returnProjectile = newInvader.fireProjectile(friendly);
        assertNotNull(returnProjectile);
        assertTrue(returnProjectile instanceof Projectile);
        assertEquals(returnProjectile.getDamage(), 3);
        PImage[] regularInvader = new PImage[] {loadImage("src/main/resources/invader1.png"), loadImage("src/main/resources/invader2.png")};
        Invader regInvader = new Invader(1, 3, getProjectileImage(), regularInvader, 250, 250, 16, 16, new int[] {1,0});assertNotNull(returnProjectile);
        Projectile regProjectile = regInvader.fireProjectile(friendly);
        assertNotNull(regProjectile);
        assertTrue(regProjectile instanceof Projectile);
        assertEquals(regProjectile.getDamage(), 1);

    }
}
