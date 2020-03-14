package invadem;

import java.util.HashMap;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;

/**
 * Barrier objects extends from the GameObject superclass. Each barrier consists of 7 components (BarrierPart objects) that have three health each. When each component is destroyed, they are
 * removed from the allBarriers ArrayList. If the size of the allBarriers ArrayList is zero, the Barrier's health becomes zero and it will be removed from the allObjects ArrayList by the App class.
 */
public class Barrier extends GameObject {
	private ArrayList<BarrierPart> allBarriers;

	/**
	 * Constructor for the Barrier Object that will consist of 7 BarrierPart objects.
	 * @param leftSprites An array of PImage objects that form the sprites for the top left of the barricade.
	 * @param topSprites An array of PImage objects that form the sprites for the top of the barricade.
	 * @param rightSprites An array of PImage objects that form the sprites for the top right of the barricade.
	 * @param solidSprites An array of PImage objects that form the sprites for everything that is not a top, left or right sprite.
	 * @param x The x co-ordinate of the beginning of the Barrier object.
	 * @param y The y co-ordinate of the beginning of the Barrier object.
	 * @param width The width of the Barrier.
	 * @param height The height of the Barrier.
	 * @param velocity Velocity of the Barrier object which should be zero.
	 */
	public Barrier(PImage[] leftSprites, PImage[] topSprites, PImage[] rightSprites, PImage[] solidSprites, int x, int y, int width, int height, int[] velocity) {
		super(x, y, width, height, velocity);
		allBarriers = new ArrayList<>();
		allBarriers.add(new BarrierPart(leftSprites, x, y, width, height, new int[] {0, 0}));
		allBarriers.add(new BarrierPart(topSprites, x + 8, y, width, height, new int[] {0, 0}));
		allBarriers.add(new BarrierPart(rightSprites, x + 16, y, width, height, new int[] {0, 0}));

		//Run a nested for loop to create Barrier Part objects for each of the three Barrier objects.
		for (int j = 0; j < 2; j++) {
        	for (int k = 0; k < 3; k++) {
	       		if (k ==0 || k == 2) {
	       			int newX = x + k * 8;
	       			int newY = y + (j + 1) * 8;
	       			allBarriers.add(new BarrierPart(solidSprites, x + k * 8, y + (j + 1) * 8, width, height, new int[] {0,0}));
	       		}
        	}
        }

        health = allBarriers.size();
	}

	/**
	 * Return the health of the Barrier object. If there are no more BarrierPart objects in the allBarriers ArrayList, return a zero to the App in order to remove this Barrier object.
	 */
	@Override
	public int getHealth() {
		if (allBarriers.size() == 0) {
			return 0;
		} else {
			return health;
		}
	}

	/**
	 * Draws the Barrier by calling a draw method on BarrierPart objects and passing the PApplet parameter.
	 * @param app App passed by the App class to this class to draw on.
	 */
	public void draw(PApplet app) {
		for (BarrierPart barrierComponent: allBarriers) {
			barrierComponent.draw(app);
		}	
	}

	/**
	 * Checks whether this any of the BarrierPart objects have been hit.
	 * @param hitObject Projectile object that is checked for its safety class.
	 * @return Returns true if a BarrierPart object is hit.
	 */
	@Override
	public Boolean checkHit(Projectile hitObject) {
		for (BarrierPart eachPart : allBarriers) {
			Boolean result = eachPart.checkHit(hitObject);

			if (result) {
				if (eachPart.getHealth() == 0) {
					allBarriers.remove(eachPart);
				}
				return true;
			}
		}
		return false;
	}	

	public ArrayList<BarrierPart> getAllBarriers() {
		return allBarriers;
	}

	public void setAllBarriers(ArrayList<BarrierPart> allBarriers) {
		this.allBarriers = allBarriers;
	}

} 
