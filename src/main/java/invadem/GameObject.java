package invadem;

import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameObject class is an abstract class that is used to provide functionality and also decrease the amount of code that has to be copied across
 * the different subclasses.
 */
public abstract class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int[] velocity;
	protected int health;

	/**
	 * Constructor of the GameObject class that takes in 5 parameters.
	 * @param x This refers to the x co-ordinate of the Game Object.
	 * @param y This refers to the y co-ordinate of the Game Object.
	 * @param width This refers to the width of the Game Object.
	 * @param height This refers to the height of the Game Object.
	 * @param velocity This refers to the velocity of the Game Object.
	 */
	public GameObject(int x, int y, int width, int height, int[] velocity) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velocity = velocity;
	}

	/**
	 * Each of the GameObject classes must be able to be drawn on to an app surface in order to inherit this class.
	 * @param app The app surface on which to draw a PImage.
	 */
	public abstract void draw(PApplet app);

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	} 

	public void setY(int y) {
		this.y = y;
	}

	public int[] getVelocity() {
		return velocity;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setXVelocity(int newVelocity) {
		velocity[0] = newVelocity;
	}

	public void setYVelocity(int newVelocity) {
		velocity[1] = newVelocity;
	}

	/**
	 * Changing the health is a method that decreases the amount of health a GameObject has and is usually used in conjunction with the collision
	 * function.
	 * @param amount This is the amount of damage that the GameObject is hit with. This depends on whether the projectile is Regular of Power.
	 */
	public void changeHealth(int amount) {
		health -= amount;

		if (health < 0) {
			health = 0;
		}
	}

	/**
	 * This is a collision detection algorithm that incorporates the method shown in the assignment description. If any of the Game Objects cross paths
	 * with a projectile, this function is called and the object is damaged. If the objects health is zero, the function removes true and the Game Object
	 * is removed from the object list in the App class.
	 * @param hitObject
	 * @return
	 */
	public Boolean checkHit(Projectile hitObject) {
		if ((hitObject.getX() < (x + width)) &&
			   ((hitObject.getX() + hitObject.getWidth()) > x) &&
			   (hitObject.getY() < (y+height)) &&
			   ((hitObject.getHeight() + hitObject.getY()) > y) && 
			   hitObject.getClass() != this.getClass()) {
					this.changeHealth(hitObject.getDamage());

					return true;
			} else {
				return false;
			}
	}	
}