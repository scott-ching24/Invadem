package invadem;

import java.util.HashMap;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.util.ArrayList;


/**
 * This is the Invader class that forms the front two rows of the InvaderSwarm. The PowerInvader and ArmouredInvader class also inherit the functionality
 * of the Invader class.
 */
public class Invader extends GameObject {
	protected PImage[] allSprites;
	protected PImage invaderImage;
	protected int ticks;
	protected int nextVelocity;
	protected PImage projectile;
	protected int projectileWidth;
	protected int projectileHeight;


	public Invader(int projectileWidth, int projectileHeight, PImage projectile, PImage[] allSprites, int x, int y, int width, int height, int[] velocity) {
		super(x, y, width, height, new int[] {velocity[0], velocity[1]});
		this.projectile = projectile;
		health = 1;
		invaderImage = allSprites[0];
		this.allSprites = new PImage[allSprites.length];
		ticks = 30;

		this.projectileWidth = projectileWidth;
		this.projectileHeight = projectileHeight;

		for (int i=0;i<allSprites.length;i++) {
			this.allSprites[i] = allSprites[i];
		}

		nextVelocity=1;
	}

	/**
	 * Adds the appropriate x and y value to the existing x and y value to move the invader on the screen each frame.
	 */
	public void tick() {
		this.x += velocity[0];
		this.y += velocity[1];
		ticks++;
	}

	/**
	 * Draws the Invader object on to the screen.
	 * @param app The app surface on which to draw a PImage.
	 */
	public void draw(PApplet app) {
		app.image(invaderImage, x, y, width, height);

		if (app.frameCount%2==0) {
			tick();
			checkTicks();
		}

	}

	/**
	 * The changeSprites() function is called whenever the Invader has walked 30 steps in a single direction or completes a downward movement.
	 */
	public void changeSprites() {
		if (invaderImage == allSprites[0]) {
			invaderImage = allSprites[1];
		} else if (invaderImage == allSprites[1]) {
			invaderImage = allSprites[0];
		}
	}

	/**
	 * Checks the Invader class for ticks which represent the number of steps an Invader has moved in a single direction.
	 */
	public void checkTicks() {
		if (ticks == 60) {
			ticks=0;
			nextVelocity= -1*velocity[0];
			velocity[0] = 0;
			velocity[1] = 1;
			invaderImage = allSprites[1];
		} else if (ticks == 16 && velocity[1] == 1) {
			ticks=0;
			velocity[0] = nextVelocity;
			velocity[1] = 0;
			invaderImage = allSprites[0];
		}
	}

	/**
	 * Fires and returns a Projectile to be added to the Projectile list in the App class.
	 * @param swarm This is the friendly GameObject that should not be hit.
	 * @return The return Projectile is the projectile that must be added to the Projectile list in the App class.
	 */
	public Projectile fireProjectile(InvaderSwarm swarm) {
		int projectileDamage;
		if (projectileWidth == 1) {
			projectileDamage = 1;
		} else {
			projectileDamage = 3;
		}
		return new Projectile(swarm, projectile, x+8, y, projectileWidth, projectileHeight, new int[] {0,1}, projectileDamage);
	}

}