# Developement Log

### 2025/10/31

    Wish I started using Git for the entire project. the bulk of the code done
for the initial commit happened from probably October 25th to October 31st. At
9am I showed my instructor a version of the game only consisting of half a tile
map, basic tile-based collision detection, and a controllable character to 
propose this idea for a in-class project.

    I used the software Tiled to create the Grass and Water tiles that were 
obtained from a tileSet found online. I also used a plain Sprite for the player
as well that was found for free online. These assets are going to be updated as
the game progresses.

    I found my first bug as well, because of the way in which the collision
detection was handled in the Player class. A Player object would get "stuck" to
tiles, this behavior prohibited the Player from being able to slide across the 
solid tiles in a direction that was unobstructed (Sorry, hard to explain). This
was happening because one Rectangle and boolean was being used to detect when 
collisions would occur between the Player object and tiles. When the singular
Rectangle detected collision on either the X or Y-Axis the canMove boolean wouldbe set to false prohibiting the change in the Player's position from being 
updated in either direction. To combat this I added another boolean as well as 
another Rectangle object. Now (booleans) canMoveX, canMoveY and (Rectangles) 
nextPosX, nextPosY are used to detect collision independently for both axis.

    - After going through this approach everything works much smoother but I did
    find that the player can "slip" out of the map when in the corners of the 
    tileMap I created. Still confused on this am going to address this.

### 2025/11/1

- Trying to address the bug that exist with the Player being able to slip out of
the corners of the tileMap.

Problem:
After doing some research I found that the reason for the Player being able to slip out of the corners of the tileMap when moving diaganolly is because when the player is in the corner there is a double collision happening. This double collision causes ambiguity, the game notices it touches two walls and then 
is stuck trying to determine which direction specifically is causing the collision.
so when the code is trying to apply the change in position (by canceling movement or 
setting dx and dy to  0) it does it in both axes at once. Both directions being corrected
at the same time enables the Player's position to end up slightly overlapping one of the 
tiles. When the Player overlaps, on the next frame the player's bounding box starts inside the
wall (Tile) which would prevent the collision check from resolving this correctly as the player
is already intersecting allowing the player to slip through the corner gap. 

Solution:
I saw on a Reddit thread that splitting movement into two independent checks gets rid of the 
confusion that occurs when a double collision happens. First the player moves in one direction,
if a wall is hit STOP movement in this direction immediately. Then the player moves in the other
direction, if a wall is hit STOP in this direction immediately. With each axis handled independently
the collision correction is always clear, the computer no longer is stuck trying to determine which
of the axes it needs to correct.
 
