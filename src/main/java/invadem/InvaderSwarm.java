package invadem;

import java.util.HashMap;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * The InvaderSwarm that consists of a back row of 10 ArmouredInvaders, 10 PowerInvaders and two front rows of regular Invader objects.
 */
public class InvaderSwarm extends GameObject {
	private ArrayList<Invader> allInvaders;
	private Random randomNumber;
	private int frameScore;

	/**
	 * Constructor for the InvaderSwarm
	 * @param projectiles The two different types of Projectile images that can be used for the PowerInvaders as opposed to the regular Invaders and ArmouredInvader.
	 * @param regularInvaders PImage array of regular Invader images.
	 * @param armouredInvaders PImage array of ArmouredInvader images.
	 * @param powerInvaders PImage array of PowerInvader images.
	 * @param x The beginning x co-ordinate of the swarm.
	 * @param y The beginning y co-ordinate of the swarm.
	 * @param width Width of the swarm because it is a GameObject.
	 * @param height Height of the swarm because it is a GameObject.
	 * @param velocity Velocity of the InvaderSwarm.
	 */
	public InvaderSwarm(PImage[] projectiles, PImage[] regularInvaders,PImage[] armouredInvaders,PImage[] powerInvaders,int x, int y,int width, int height, int[] velocity) {
		super(x, y, width, height, velocity);
		allInvaders = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 10; k++) {
				Invader newInvader;
				if (i == 0) {
					newInvader = new ArmouredInvader(1, 3, projectiles[0], armouredInvaders, x + 16 * k + 9 * k, i * 16 + 9 * i, width, height, velocity);
				} else if (i == 1) {
					newInvader = new PowerInvader(2, 5, projectiles[1], powerInvaders, x + 16 * k + 9 * k, i * 16 + 9 * i, width, height, velocity);
				} else {
					newInvader = new Invader(1, 3, projectiles[0], regularInvaders, x + 16 * k + 9 * k, i * 16 + 9 * i, width, height, velocity);
				}
				allInvaders.add(newInvader);
			}

			randomNumber = new Random();
			health = allInvaders.size();
		}
	}


	/**
	 * Retrieves the health of the InvaderSwarm. If the swarm's size is zero, a zero is returned to signify to the App class that this GameObject can be removed.
	 */
	@Override
	public int getHealth() {
		if (allInvaders.size() == 0) {
			return 0;
		} else {
			return health;
		}
	}

		/**
		 * Checks whether the InvaderSwarm is hit. If an ArmouredInvader or PowerInvader is destroyed, the score is increased by 250 before the Invader is removed
		 * from the ArrayList of Invaders. Regular Invaders that are destroyed increase the score by 100.
		 */
	@Override
	public Boolean checkHit(Projectile hitObject) {
		for (Invader eachInvader : allInvaders) {
			Boolean result = eachInvader.checkHit(hitObject);

			if (result) {
				if (eachInvader.getHealth() == 0) {
					if (eachInvader instanceof ArmouredInvader || eachInvader instanceof PowerInvader) {
						frameScore += 250;
					} else {
						frameScore += 100;
					}
					allInvaders.remove(eachInvader);
				}
				return true;
			}
		}
		return false;
	}

		/**
		 * Returns an integer to the App class the amount of points earnt in this frame which have to be added to the game's current score.
		 */
	public int getFrameScore() {
		int additionalScore = frameScore;
		frameScore = 0;
		return additionalScore;
	}

		/**
		 * Draws the InvaderSwarm on to the app class surface.
		 */
	public void draw(PApplet app) {
		for (Invader eachInvader : allInvaders) {
			eachInvader.draw(app);
		}	
	}

		/**
		 * Creates and returns a projectile that is fired from a random Invader object in the InvaderSwarm.
		 */
	public Projectile fireProjectile() {
		return allInvaders.get(randomNumber.nextInt(allInvaders.size())).fireProjectile(this);
	}

		/**
		 * Checks whether any of the Invader objects in the InvaderSwarm class have reached within 10 pixels of a Barrier object. This would
		 * trigger a GameOver event.
		 */
	public Boolean checkEnd() {
		for (int i=0;i<allInvaders.size();i++) {
			if (allInvaders.get(i).getY() >= 480-26-8*3-20) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Invader> getAllInvaders() {
		return allInvaders;
	}
}
