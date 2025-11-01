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

