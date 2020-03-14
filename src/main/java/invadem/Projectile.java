package invadem;

import java.util.HashMap;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;


/**
 * Projectile is a class that extends from the GameObject abstract class. The attributes of this class are a friendly object that it cannot destroy,
 * a sprite depending on the Invader that fired it and the damage it will cause to the GameObject that it collides with.
 */
public class Projectile extends GameObject {
	private Object friendly;
	private PImage currentSprite;
	private int damage;

	/**
	 * Projectile constructor that takes in 8 arguments.
	 * @param friendly The object which should not be destroyed when it collides with the Projectile.
	 * @param currentSprite The sprite that the projectile should have which depends on the Invader that fires the Projectile.
	 * @param x The starting x co-ordinate of the Projectile.
	 * @param y The starting y co-ordinate of the Projectile.
	 * @param width The width of the Projectile class.
	 * @param height The height of the Projectile class.
	 * @param velocity The velocity of the Projectile.
	 * @param damage The damage to the GameObject that it will cause when it collides.
	 */
	public Projectile(GameObject friendly, PImage currentSprite, int x, int y, int width, int height, int[] velocity, int damage) {
		super(x, y, width, height, velocity);
		this.currentSprite = currentSprite;
		health = 1;
		this.damage = damage;

		this.friendly = friendly.getClass();
	}

	/**
	 * Called each frame to move the projectile forward.
	 */
	public void tick() {
		this.x += velocity[0];
		this.y += velocity[1];
	}

	public void draw(PApplet app) {
		app.image(currentSprite, x, y, width, height);
		tick();
	}	

	public int getDamage() {
		return damage;
	}

	/**
	 * Tests whether the projectile has collided with any GameObjects.
	 * @param hitObject The GameObject that the projectile has collided with.
	 * @return Returns true if the GameObject that it has hit is not the same as the friendly class that is stored as the Projectile's attribute.
	 */
	public Boolean gotHit(GameObject hitObject) {
		if (hitObject.getClass() != friendly) {
			return true;
		} else {
			return false;
		}
	}

	public Object getSafetyClass() {
		return friendly;
	}

	/**
	 * Checks for the collision between this projectile and all of the game's objects.
	 * @param allObjects All the objects that are currently in the game.
	 * @return Returns true if the Projectile has hit any other GameObject.
	 */
	public Boolean collisionCheck(List<GameObject> allObjects) {

		for (GameObject newObject : allObjects) {
			if (newObject.getClass() != friendly && newObject.checkHit(this)) {
				return true;

			}
		}
		return false;
	}
}