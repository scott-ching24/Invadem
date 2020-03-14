# Invadem
This is a mimic of the classic Invadem Game whereby a player must defend the planet from space invaders. The player is represented as a tank and can move left or right and fire projectiles at the oncoming space invaders. The tank begins with three lives and loses one life if it is hit by a regular space invader shot or three lives if the space invader is powerful.

There are three types of space invaders which vary by power and strength. A regular invader can be killed by a single tank shot, an armoured invader requires three shots to be killed, and the power invader's projectile can entirely remove the health of the tank. Regardless of the type of the invader, each invader moves eight pixels to the right before moving one pixel down.

Barriers are set-up at the beginning of the game and each have a health of three (per BarrierPart object). After a Barrier is destroyed, it is removed from the game and the player is unable to hide behind it.

The game concludes when either the space invaders reach the beginning barrier level or the player destroys all the space invaders. If all the space invaders are destroyed, the game progresses to the next level where space invaders firing speed is increased by 1 (to a maximum of 5 levels).
