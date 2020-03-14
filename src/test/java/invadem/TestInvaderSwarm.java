package invadem;

import java.util.ArrayList;
import processing.core.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestInvaderSwarm extends App {
    @Before
    /**
     * Runs the App.java sketch for the rest of the test cases because this operation can be costly if re-written.
     */
    public void run() {
        PApplet.runSketch(new String[] {"--location=0,0", " "}, this);
        PImage[] regularInvader = new PImage[] {loadImage("src/main/resources/invader1.png"), loadImage("src/main/resources/invader2.png")};
        PImage[] armouredInvader = new PImage[] {loadImage("src/main/resources/invader1_armoured.png"), loadImage("src/main/resources/invader2_armoured.png")};
        PImage[] powerInvader = new PImage[] {loadImage("src/main/resources/invader1_power.png"), loadImage("src/main/resources/invader2_power.png")};
        InvaderSwarm newSwarm = new InvaderSwarm(new PImage[] {getProjectileImage(), getPowerProjectile()}, regularInvader, armouredInvader, powerInvader, 205, 0, 16, 16, new int[] {1, 0});
        delay(2000);
    }

    @Test
    /**
     * Tests the constructor of the InvaderSwarm object. There should be 40 invaders in the InvaderSwarm which should bring its health to 40 (its size).
     */
    public void testSwarmConstruction() {
        PImage[] regularInvader = new PImage[] {loadImage("src/main/resources/invader1.png"), loadImage("src/main/resources/invader2.png")};
        PImage[] armouredInvader = new PImage[] {loadImage("src/main/resources/invader1_armoured.png"), loadImage("src/main/resources/invader2_armoured.png")};
        PImage[] powerInvader = new PImage[] {loadImage("src/main/resources/invader1_power.png"), loadImage("src/main/resources/invader2_power.png")};
        InvaderSwarm newSwarm = new InvaderSwarm(new PImage[] {getProjectileImage(), getPowerProjectile()}, regularInvader, armouredInvader, powerInvader, 205, 0, 16, 16, new int[] {1, 0});
        assertNotNull(newSwarm.getAllInvaders());
        assertEquals(newSwarm.getAllInvaders().size(), 40);
        assertEquals(newSwarm.getHealth(), 40);
    }

    @Test
    /**
     * Tests the return method of getHealth and whether this changes when the InvaderSwarm has no Invaders in its ArrayList.
     */
    public void testGetHealth() {
        InvaderSwarm testSwarm = getSwarm();
        testSwarm.setHealth(5);
        assertEquals(testSwarm.getHealth(), 5);
        testSwarm.setHealth(0);
        ArrayList<Invader> testInvaders = testSwarm.getAllInvaders();
        for (int i=0;i<testInvaders.size();i++) {
            testInvaders.remove(i);
            i--;
        }
        assertEquals(testSwarm.getHealth(), 0);
    }

    @Test
    /**
     * Tests the getHit() method of the InvaderSwarm. The test was done by testing whether an Armoured Invader would increase the frameScore of the
     * InvaderSwarm by 250. This frameScore would be collected by the App class each round and added to the current score before the frameScore is reset
     * each frame.
     */
    public void testGetHit(){
        PImage[] regularInvader = new PImage[] {loadImage("src/main/resources/invader1.png"), loadImage("src/main/resources/invader2.png")};
        PImage[] armouredInvader = new PImage[] {loadImage("src/main/resources/invader1_armoured.png"), loadImage("src/main/resources/invader2_armoured.png")};
        PImage[] powerInvader = new PImage[] {loadImage("src/main/resources/invader1_power.png"), loadImage("src/main/resources/invader2_power.png")};
        InvaderSwarm testSwarm = new InvaderSwarm(new PImage[] {getProjectileImage(), getPowerProjectile()}, regularInvader, armouredInvader, powerInvader, 205, 0, 16, 16, new int[] {1, 0});
        Projectile testProjectile = new Projectile(getGameTank(), getProjectileImage(), testSwarm.getAllInvaders().get(0).getX(), testSwarm.getAllInvaders().get(0).getY(), 1, 3, new int[] {0, -1}, 3);
        Boolean gotHit = testSwarm.checkHit(testProjectile);
        assertTrue(gotHit);
        assertEquals(testSwarm.getFrameScore(), 250);
    }
}
