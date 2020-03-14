package invadem;

import java.util.HashMap;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.util.ArrayList;

/**
 * Armoured Invader class makes up the top row of the Invader Swarm class (10 invaders) and extends from the Invader superclass. The Armoured Invader differs from the regular Invader because it has
 * three health and adds 250 to the player's current score when killed.
 */
public class ArmouredInvader extends Invader {
	public ArmouredInvader(int projectileWidth, int projectileHeight, PImage projectile, PImage[] allSprites, int x, int y, int width, int height, int[] velocity) {
		super(projectileWidth, projectileHeight, projectile, allSprites, x, y, width, height, velocity);
		health = 3;
	}
}