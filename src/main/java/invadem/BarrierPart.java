package invadem;

import java.util.List;
import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.Arrays;
import invadem.Tank;

/**
 * This is the BarrierPart class which makes up the Barrier class. There are four different types of BarrierPart objects that can be created
 * depending on whether the BarrierPart object is the left, top, right or side of the Barrier object. Each type of BarrierPart object has a distinct
 * set of PImages that change depending on whether or not the BarrierPart is hit.
 */
public class BarrierPart extends GameObject {
	private int indexSprite;
	private PImage[] allSprites;

	/**
	 * The construction of the BarrierPart object which extends from the abstract GameObject class. The instantiation of this class is done
	 * in the App class via the Barrier class.
	 * @param allSprites An ArrayList of all the different sprites that a BarrierPart object can take.
	 * @param x The x co-ordinate of the BarrierPart.
	 * @param y The y co-ordinate of the BarrerPart.
	 * @param width The width of the BarrierPart object.
	 * @param height The height of the BarrierPart object.
	 * @param velocity The velocity of the BarrierPart object which is required since it extends the GameObject class.
	 */
	public BarrierPart(PImage[] allSprites, int x, int y, int width, int height, int[] velocity) {
		super(x, y, width, height, velocity);
		health = 3;
		this.allSprites = allSprites;

		indexSprite = 0;
	}

	/**
	 * Tick is called each time a new frame is recorded.
	 */
	public void tick() {
		this.x += velocity[0];
		this.y += velocity[1];
	}

	/**
	 * The draw() function draws the current image of the BarrierPart object.
	 * @param app The app on which to draw the image.
	 */
	public void draw(PApplet app) {
		app.image(allSprites[indexSprite], x, y, width, height);
		tick();
	}

	/**
	 * This function is called each time the BarrierPart is hit and instructs the BarrierPart to change sprites and decrease its health.
	 * @param amount
	 */
	@Override
	public void changeHealth(int amount) {
		health -= amount;
		indexSprite += amount;

		if (health < 0) {
			health = 0;
		}
	}
}