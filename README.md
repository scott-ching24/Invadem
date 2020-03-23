# Invadem
This is a mimic of the classic Invadem Game whereby a player must defend the planet from space invaders. The player is represented as a tank and can move left or right and fire projectiles at the oncoming space invaders. The tank begins with three lives and loses one life if it is hit by a regular space invader shot or three lives if the space invader is powerful.

The game concludes when either the space invaders reach the beginning barrier level or the player destroys all the space invaders. If all the space invaders are destroyed, the game progresses to the next level where space invaders firing speed is increased by 1 (to a maximum of 5 levels).

This assignment was created using the Java programming language and tested my ability to implement my knowledge of the programming language and Object Oriented Programming (OOP). From this assignment, I also began to understand more about .build files and wrappers, Maven dependencies, Gradle and unit tests which can be found in the project folder.

##Tank
This is a player controlled entity that can be moved by pressing the left and right arrow keys on the keyboard
and moves at a rate of 1 pixel per a frame . The tank is 22x16 pixels, it starts at the bottom-middle of the
display. A tank can fire projectiles which can hit the barriers or enemy invaders. It can shoot multiple
projectiles towards the invaders. In addition to moving the tank with left and right arrow keys, the player can
fire projectiles using the space key. If an enemy projectile hits the tank, it will lose a hit point, if the tank is hit 3
times, the game should transition to a Game Over screen since the tank has been destroyed.

##Invader
Each invader has a unique starting position but move in time with every other invader. The invader swarm
moves from the top-middle of the screen to the player's barriers. Once an invader has reached the barriers,
the game should transition to a Game Over screen.
Invaders will move 30 steps in one direction before moving down and heading 30 steps in the other direction.
Each sideways step will constitute a movement of 1 pixel, each step is made every two frames. When an
invader moves downward, it will move 8 pixels down and transition to it's other sprite.
The invaders are part of a larger swarm, the swarm starts with 40 invaders (10 invaders per row, 4 rows). Each
invader is 16x16 pixels, this will correspond with the size of their sprites. They will have the same collision
area as their sprites.
Once an invader is hit, it is considered to have been destroyed and should no longer be rendered by the
game. When all invaders have been hit, this will result in the player winning the game and transitioning to the
Next Level screen.
Every 5 seconds, an invader will be randomly selected to fire a projectile downwards.

##Barrier
A barrier is composed of 7 different components, each component can sustain 3 hits. Once a component has
been destroyed, it no longer offers protection for the tank. When a barrier sustains a hit, it will change to a
different sprite, indicating that it has been damaged. The player is provided with 3 barriers, each barrier, left
barrier is at least 20 pixels away from the left boundary described in the application section, the center barrier
starts in the centre of the screen, right barrier is at least 20 pixels away from the right boundary. Each barrier
is at least 10 pixels above the tank's location.
A barrier can be hit by the tank and an invader

##Projectile
A projectile can be fired by both the tank and an invader, however, an invader's projectile will not hit any other
invader (only the barrier and tank). The tank can hit the barrier as well as any invader. Once a projectile
impacts with another entity, it will cease to exist.
The projectile is 1x3 pixels and travels upwards 1px per frame.

##Game Conditions
The goal of the game is for the player to destroy all invaders before either the tank is destroyed or the
invaders land.
The player wins when the following conditions have been met:
*All invaders are destroyed.
The computer wins when one of the following conditions have been met:
*An invader reaches the barriers (10px away from the barriers).
*The tank is hit 3 times and destroyed.

All copyrights and ownership of this assignment scaffold belong to the University of Sydney, Australia.

