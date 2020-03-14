
package invadem;

import java.util.HashMap;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.util.ArrayList;

/**
 * PowerInvader class that extends from the Invader class. This class has a different sprite to a regular Invader and also fires a different projectile.
 * This is reflected in the PImage array.
 */
public class PowerInvader extends Invader {
	public PowerInvader(int projectileWidth, int projectileHeight, PImage projectile, PImage[] allSprites, int x, int y, int width, int height, int[] velocity) {
		super(projectileWidth, projectileHeight, projectile, allSprites, x, y, width, height, velocity);
	}
}