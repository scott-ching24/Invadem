package invadem;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Tank class is the main class that the user can control. This GameObject can either move to the right or to the left. The attributes
 * tankImage refers to the PImage of the player's tank and the canFire attribute relates to whether or not the tank can fire. This changes each time
 * a key is pressed or released by the user.
 */
public class Tank extends GameObject {
	private PImage tankImage;
	private Boolean canFire;

	/**
	 * Constructor for the Tank class.
	 * @param tankImage PImage of the tank.
	 * @param x Starting x co-ordinate of the tank.
	 * @param y Starting y co-ordinate of the tank.
	 * @param width Width of the tank.
	 * @param height Height of the tank.
	 * @param velocity Velocity of the tank which moves it either left of right.
	 */
	public Tank(PImage tankImage, int x, int y, int width, int height, int[] velocity) {
		super(x, y, width, height, velocity);
		this.tankImage = tankImage;
		health = 3;
		canFire = true;
	}

	/**
	 * Moves the tank's x position according to the given velocity and ensures that the tank is within the game's boundaries.
	 */
	public void tick() {
		x += velocity[0];
		if (x > 460-22) {
			x = 460-width;
		} else if (x < 180) {
			x = 180;
		}
	}

	/**
	 * Draws the tank to the screen.
	 * @param app The app surface on which to draw a PImage.
	 */
	public void draw(PApplet app) {
		app.image(tankImage, x, y, width, height);
		tick();
	}

	/**
	 * Adjusts the veloicty of the tank according to which keys are being pressed.
	 * @param adjustment The new velocity of the Tank object.
	 */
	public void adjustVelocity(int adjustment) {
		velocity[0] = adjustment;
	}


	public int getHealth() {
		return health;
	}

	public Boolean getFire() {
		return canFire;
	}

	public void setFire() {
		canFire = !canFire;
	}

	public PImage getTankImage() {
		return tankImage;
	}
}