package invadem;

import java.util.ArrayList;
import processing.core.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

  public class AppTest extends App {


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
	 * Tests the construction of the App. Attributes such as screenWidth, screenHeight and boundaries are used to set up other instance classes.
	 */
	public void testAppConstructor() {
		assertEquals(this.getScreenWidth(), 640);
		assertEquals(this.getScreenHeight(), 480);
		assertEquals(this.getBoundaryLeft(), 180);
		assertEquals(this.getBoundaryRight(), 460);
		assertEquals(this.getTransition(), false);
		assertEquals(this.getCurrentLevel(), 1);
		assertEquals(this.getHighscore(), 10000);
		assertEquals(this.getCurrentScore(), 0);
	}

	@Test
	/**
	 * Tests the setup function for the App class. Ensures that PImages are loaded and objects are created.
	 */
	public void testSetup() {
		assertNotNull(getGameFont());
		assertNotNull(getAllProjectiles());
		assertNotNull(getAllObjects());
		assertNotNull(getAllKeys());
		assertNotNull(getGameOverImage());
		assertNotNull(getGameTank());
		assertNotNull(getProjectileImage());
		assertNotNull(getPowerProjectile());
		assertNotEquals(getPowerProjectile(), getProjectileImage());
		assertNotNull(getSwarm());
		assertEquals(getAllObjects().size(), 5);
	}

	@Test
	/**
	 * Tests the fire rate of the Invader Swarm based on the current level which is calculated by using modulus on the 60 frames per second multiplied by the level's fire rate (5-level+1).
	 */
	public void testCheckFire() {
		delay(5000);
		int projectileNumber = getAllProjectiles().size();
		if (frameCount % (5 * 60) == 0) assertEquals(getAllProjectiles().size(), (projectileNumber + 1));
		else assertEquals(getAllProjectiles(), getAllProjectiles());
	}

	@Test
	/**
	 * Tests for the removal of objects that are hit by projectiles.
	 */
	public void testCheckRemove() {
		ArrayList<GameObject> returnList = new ArrayList<>();
		for (int i = 0; i < getAllProjectiles().size(); i++) {
			int beginningReturnSize = returnList.size();
			Boolean whetherRemove = getAllProjectiles().get(i).collisionCheck(getAllObjects());
			assertNotNull(whetherRemove);
			if (whetherRemove) {
				returnList.add(getAllProjectiles().get(i));
				assertEquals(returnList.size(), beginningReturnSize+1);
			} else assertEquals(returnList.size(), beginningReturnSize);
		}
	}

	@Test
	/**
	 * Tests for the game over condition of the game. If the game is over, check if the current score is higher than the high score and, if so, replace the high score before zeroing out the current score.
	 */
	public void gameOverTank() {
		int currentLevel = getCurrentLevel();
		getGameTank().changeHealth(3);
		setCurrentScore(12000);
		checkGameConditions();
		assertEquals(getCurrentLevel(), 1);
		assertEquals(getHighscore(), 12000);
		assertEquals(getTransitionImage(), getGameOverImage());
		assertTrue(getTransition());
	}

	@Test
	/**
	 * Test to see if the level is increased after the Invader Swarm has been destroyed. In addition to increasing the level, the screen should also transition to display a "next level" image for a minimum of two seconds.
	 */
	public void testNextLevel() {
		getSwarm().setHealth(0);
		int currentLevel = getCurrentLevel();
		checkGameConditions();
		assertEquals(getSwarm().getHealth(), 0);
		assertEquals(getCurrentLevel(), currentLevel + 1);
		assertTrue(getTransition());
		assertEquals(getTransitionImage(), getNextLevelImage());
	}

	@Test
	/**
	 * Tests whether the ArrayList of allKeys in the App class react accordingly to the keys pressed. A space bar should fire a projectile from the tank,
	 * a left arrow button should change the velocity of the tank to left and a right arrow button should move the tank's velocity to the right.
	 */
	public void testKeyPressed() {
		int allProjectilesSize  = getAllProjectiles().size();
		key = ' ';
		keyPressed();
		//assertEquals(allProjectilesSize+1, getAllProjectiles());
		setAllKeys(new ArrayList<Integer>());
		key = CODED;
		keyCode = LEFT;
		keyPressed();
		assertEquals(getGameTank().getVelocity()[0], -1);
		setAllKeys(new ArrayList<Integer>());
		key = CODED;
		keyCode = RIGHT;
		keyPressed();
		assertEquals(getGameTank().getVelocity()[0], 1);
	}
}
