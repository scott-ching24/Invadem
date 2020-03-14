package invadem;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;


/**
 * The main class of the Invadem App game. Variables that will be used throughout the game are initialised in the App() constructor and are used several times throughout the program. Images are loaded
 * in the setup() function, the draw() function runs the game loop and other class functions have been separated from the draw() function to provide modularity and improve readability of the code.
 */
public class App extends PApplet {
	private int boundaryLeft;
	private int boundaryRight;
	private int screenWidth;
	private int screenHeight;
	private Tank gameTank;
    private Barrier barrierObject;
	private ArrayList<Integer> allKeys;
   	private ArrayList<Projectile> allProjectiles;
	private ArrayList<GameObject> allObjects;
	private PImage projectileImage;
	private PImage powerProjectile;
	private PImage nextLevelImage;
	private PImage gameOverImage;
	private InvaderSwarm swarm;
	private Boolean transition;
	private PImage transitionImage;
	private int currentLevel;
    private int highScore;
    private int currentScore;
    private PFont gameFont;
    private PImage explosionImage;
    private PImage tankLives;

    /**
     * Constructor for the App class of Invadem. The attributes created in the App() constructor are either regularly used, in which they provide a basis for consistent values or, they should not be
     * included in the setup when the game resets or the next level phase is reached.
     */
    public App() {
    	screenWidth = 640;
    	screenHeight = 480;
    	boundaryLeft = 180;
    	boundaryRight = 460;
    	transition = false;
    	currentLevel = 1;
        highScore = 10000; //Listed here so that this is not reset when frameCount=-1 is called.
        currentScore = 0;
    }

    /**
     * Setup function of the App class. Images and classes are loaded in this function and prepared for the draw() function. When frameRate is set to -1 to signal a restart of the game
     * or the next level, the setup function is called. This creates a new set of Game Objects on the screen for the user. Classes created by this function include the InvaderSwarm, Barriers,
     * Tank and the initialisation of a projectile. All of these objects are added to a GameObject ArrayList which provides ease when testing whether a projectile hits in the draw() function.
     */
    public void setup() {

        frameRate(60);
        gameFont = createFont("PressStart2p-Regular.ttf", 4, true);

        //Initialise ArrayLists attributes and transition images for the App class.
        allProjectiles = new ArrayList<>();
        allObjects = new ArrayList<>();
    	allKeys = new ArrayList<>();
    	gameOverImage = loadImage("src/main/resources/gameover.png");
    	nextLevelImage = loadImage("src/main/resources/nextlevel.png");

    	//Create the game tank and add it to the allObjects ArrayList.
        gameTank = new Tank(loadImage("src/main/resources/tank1.png"), screenWidth/2-11, screenHeight-16, 22, 16, new int[] {0, 0});
        allObjects.add(gameTank);

        //Initialise PImage arrays for the different types of invaders and projectiles that the invader can shoot. This will provide a foundation for easier class construction and was done by design.
        PImage[] regularInvader = new PImage[] {loadImage("src/main/resources/invader1.png"), loadImage("src/main/resources/invader2.png")};
        PImage[] armouredInvader = new PImage[] {loadImage("src/main/resources/invader1_armoured.png"), loadImage("src/main/resources/invader2_armoured.png")};
        PImage[] powerInvader = new PImage[] {loadImage("src/main/resources/invader1_power.png"), loadImage("src/main/resources/invader2_power.png")};
        projectileImage = loadImage("src/main/resources/projectile.png");
        powerProjectile = loadImage("src/main/resources/projectile_lg.png");
        swarm = new InvaderSwarm(new PImage[] {projectileImage, powerProjectile}, regularInvader, armouredInvader, powerInvader, 205, 0, 16, 16, new int[] {1, 0});
        allObjects.add(swarm);

        //Initialise the PImage arrays for the Barrier objects that have to be created. Since there are four different sets of sprites available, they were arranged into arrays for easier class construction.
        PImage[] leftSprites = new PImage[] {loadImage("src/main/resources/barrier_left1.png"), loadImage("src/main/resources/barrier_left2.png"), loadImage("src/main/resources/barrier_left3.png"), loadImage("src/main/resources/empty.png")};
        PImage[] topSprites = new PImage[] {loadImage("src/main/resources/barrier_top1.png"), loadImage("src/main/resources/barrier_top2.png"), loadImage("src/main/resources/barrier_top3.png"), loadImage("src/main/resources/empty.png")};
        PImage[] rightSprites = new PImage[] {loadImage("src/main/resources/barrier_right1.png"), loadImage("src/main/resources/barrier_right2.png"), loadImage("src/main/resources/barrier_right3.png"),loadImage("src/main/resources/empty.png")};
        PImage[] solidSprites = new PImage[] {loadImage("src/main/resources/barrier_solid1.png"), loadImage("src/main/resources/barrier_solid2.png"), loadImage("src/main/resources/barrier_solid3.png"), loadImage("src/main/resources/empty.png")};
       	int[] barrierPositions = new int[] {boundaryLeft+20, screenWidth/2-12, boundaryRight-44};
       	
       	for (int i=0;i<barrierPositions.length;i++) {
       		Barrier newBarrier = new Barrier(leftSprites, topSprites, rightSprites, solidSprites, barrierPositions[i], screenHeight-26-8*3, 8, 8, new int[] {0, 1});
            barrierObject = newBarrier;
            allObjects.add(newBarrier);
       	}

        explosionImage = loadImage("src/main/resources/explosion.png");
        tankLives = loadImage("src/main/resources/tankLives.png");
    }

    /**
     * Default settings for the display screen. The variables screenWidth and screenHeight have been initialised in the App constructor.
     */
    public void settings() {
        size(screenWidth, screenHeight); 
    }

    /**
     * Draw function for the App class runs the loop for PApplet. The frameRate for the draw function is set to 60 in order to produce a 60FPS effect. If the InvaderSwarm is destroyed, players progress
     * to the next level, if the InvaderSwarm reaches within 10 pixels of the barriers or the tank is destroyed, the game is over. A minimum of two seconds has been set on the transition phase to either
     * a "new game" or next level.
     */
    public void draw() { 
    	// This is for the main game loop
    	background(0);
        textFont(gameFont, 8);
        fill(255);
        text("Highscore" + Integer.toString(highScore), screenWidth - 8 * 9, 8);
        text(Integer.toString(highScore), screenWidth - 8 * Integer.toString(highScore).length(), 16);
        text("Score: " + Integer.toString(currentScore), 0, 8);

        //Draw the tank's lives as heart icons on the top left of the screen.
        for (int i = 0; i < gameTank.getHealth(); i++) {
            image(tankLives, 0 + i * 20, 10,15, 15);
        }

        //If the game is in a transition phase, display the appropriate transition image. Otherwise switch the boolean value of transition to begin the game again.
    	if (transition) {
    		if (frameCount <= 120) {
    			image(transitionImage, screenWidth / 2 - 61,screenHeight / 2 - 16, 122, 16);
    		} else {
    			transition = false;
    			frameCount = -1;
    		}
    	} else {
    		for (Projectile gameProjectile : allProjectiles) {
    			gameProjectile.draw(this);
    		}

	    	for(GameObject gameObject : allObjects) {
		   		gameObject.draw(this);
	    	}

	    	//Checks whether or not an invader should shoot a projectile. checkRemove returns an ArrayList of projectiles that need to be deleted due to collision.
            checkFire();
	    	ArrayList<GameObject> removeList = new ArrayList<>(checkRemove(allObjects));

	    	for (int i = 0; i < removeList.size(); i++) {
                image(explosionImage, removeList.get(i).getX()-8, removeList.get(i).getY(), 12, 12);
	    		allProjectiles.remove(removeList.get(i));
	    	}

	    	//At the end of each loop, this function tests whether the game is over or there is a next level.
            checkGameConditions();
            currentScore += swarm.getFrameScore();
    	}
    }

    /**
     * Checks whether projectiles have collided and returns a list of collided projectiles.
     * @param allObjects An ArrayList of all of the Game's GameObject objects (not including projectiles).
     * @return Returns an ArrayList of GameObjects that must be removed from the game's projectiles.
     */
    public ArrayList<GameObject> checkRemove(ArrayList<GameObject> allObjects) {
        ArrayList<GameObject> returnList = new ArrayList<>();

        for (int i = 0; i < allProjectiles.size(); i++) {
            Boolean whetherRemove = allProjectiles.get(i).collisionCheck(allObjects);
            if (whetherRemove) {
                returnList.add(allProjectiles.get(i));
            }
        }

        return returnList;
    }

    /**
     * Checks if it is the correct frame for the Invader Swarm to fire a projectile. The formula for calculating the fire rate in relation the the current frame of the app is (frameCount)%(5-currentLevel+1)*60.
     */
    public void checkFire() {
        int currentFire = (5 - currentLevel + 1);

        if (currentFire < 1) {
            currentFire = 1;
        }

        if (frameCount % (currentFire * 60) == 0) {
            allProjectiles.add(swarm.fireProjectile());
        } else {
            return;
        }
    }

    /**
     * When a key is pressed by the user, this function is automatically called at the end of the frame. If the space bar is pressed, a new projectile is launched, otherwise the list of keys is checked and
     * the tank's velocity is adjusted appropriately. The allKeys ArrayList contains an ArrayList of key objects that are removed when the keyReleased function has been called.
     */
    public void keyPressed() {
    	if (key == ' ' && gameTank.getFire()) {
    		Projectile tankProjectile = new Projectile(gameTank, projectileImage, gameTank.getX()+gameTank.getWidth()/2, gameTank.getY(), 1, 3, new int[] {0, -1}, 1);
			allProjectiles.add(tankProjectile);
    		gameTank.setFire();
    	} else if (key == CODED && allKeys.contains(keyCode) == false) {
    		allKeys.add(keyCode);
    		if (allKeys.contains(RIGHT) && allKeys.contains(LEFT)) {
    			gameTank.adjustVelocity(0);
    		} else if (allKeys.contains(LEFT)) {
    			gameTank.adjustVelocity(-1);
    		}  else if (allKeys.contains(RIGHT)) {
    			gameTank.adjustVelocity(1);
    		}
    	}
    }

    /**
     * The keyReleased function is automatically called at the end of a frame if a key is released. If the key is the space bar, the game tank's ability to fire is changed back to true. Otherwise,
     * if the key is a direction, remove it from the allKeys ArrayList.
     */
    @Override
    public void keyReleased() {
    	if (key == ' ') {
    		gameTank.setFire();
    	} else if (key == CODED && allKeys.contains(keyCode)) {
    		allKeys.remove((Integer) keyCode);
    		gameTank.adjustVelocity(0);
    	}
    }

    /**
     * Main function of the App that begins the App.
     * @param args Command line arguments that can be entered but are not necessary.
     */
    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

    /**
     * This function is called by the draw() function and checks for game ending or next level conditions. If these conditions are reached, then the transition image must be displayed and the
     * current score must be compared with the high score.
     */
    public void checkGameConditions() {
        for (int k = 0; k < allObjects.size(); k++) {
            //If the health of the tank is zero transition to game over.
            if (allObjects.get(k).getHealth() == 0) {
                if (allObjects.get(k) instanceof Tank) {
                    transition = true;
                    transitionImage = gameOverImage;
                    if (currentScore > highScore) {
                        highScore = currentScore;
                    }
                    currentScore = 0;
                    currentLevel = 1;
                    frameCount = -1;
                } else if (allObjects.get(k) instanceof InvaderSwarm) {
                    //If the InvaderSwarm is destroyed, move to the next level.
                    transition = true;
                    transitionImage = nextLevelImage;
                    currentLevel++;
                    frameCount = -1;
                }

                allObjects.remove(allObjects.get(k));
                k--;

            } else if (allObjects.get(k) instanceof InvaderSwarm) {
                //If the swarm reaches 10 pixels from the barricades, the game over transition should eb run.
                Boolean whetherEnd = swarm.checkEnd();

                if (whetherEnd) {
                    transition = true;
                    transitionImage = gameOverImage;

                    if (currentScore > highScore) {
                        highScore = currentScore;
                    }

                    currentScore = 0;
                    currentLevel = 1;
                    frameCount = -1;
                }
            }
        }
    }
    public int getBoundaryLeft() {
		return boundaryLeft;
	}

	public int getBoundaryRight() {
    	return boundaryRight;
	}

	public int getScreenWidth() {
    	return screenWidth;
	}

	public int getScreenHeight() {
    	return screenHeight;
	}

	public Tank getGameTank() {
    	return gameTank;
	}

    public void setCurrentScore(int score) {
        currentScore = score;
    }

	public ArrayList<Integer> getAllKeys() {
    	return allKeys;
	}

	public ArrayList<Projectile> getAllProjectiles() {
		return allProjectiles;
	}

	public ArrayList<GameObject> getAllObjects() {
		return allObjects;
	}

	public PImage getProjectileImage() {
    	return projectileImage;
	}

	public PImage getPowerProjectile() {
    	return powerProjectile;
	}

	public PImage getNextLevelImage() {
    	return nextLevelImage;
	}

	public PImage getGameOverImage() {
    	return gameOverImage;
	}

	public InvaderSwarm getSwarm() {
    	return swarm;
	}

	public Boolean getTransition() {
    	return transition;
	}

	public PImage getTransitionImage() {
    	return transitionImage;
	}

	public int getCurrentLevel() {
    	return currentLevel;
	}

	public int getHighscore() {
    	return highScore;
	}

	public int getCurrentScore() {
    	return currentScore;
	}

	public PFont getGameFont() {
    	return gameFont;
	}

    public void setAllKeys(ArrayList<Integer> newKeys) {
        this.allKeys = newKeys;
    }

    public Barrier getBarrier() {
        return barrierObject;
    }

}
