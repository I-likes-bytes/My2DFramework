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

    I bought a giant comprehensive asset pack that contains so many sprites
This pack was only $6 USD but it is well worth it. It was created by the artist
Emanuelle on the itch.io website. When looking through the various files I saw a
32 x 32 sign that read "Beach"and I felt it'd be easy to add into the game. When trying to go about creating a Class for creating and adding this object I 
quickly realized I should start creating different subclasses for how I'd like
different objects to act. Some objects are going to be interactable, some are 
going to be purely to obstruct the player path, I eventually want weapons, etc. I am trying to think about how to structure my code to handle the different 
Object Catergories. I'm just going to start with a StaticSolidObject class an
InteractiveObject class. Eventually I want classes for Enemy objects, NPC 
objects, etc. (It's currently 10:46PM EST).

### 2025/11/2

I added two sub-classes for GameObject to accomodate objects with different behaviors and properties. I did end up sticking with InteractiveObject and StaticSolidObject. I found a sprite for a coin so I'm going to use the coin to start working on the Player being able to interact with objects of the InteractiveObject class. I'm going to continue to utilize the sprite for the sign in order to test the StaticSolidObject class. I will have to update a few important classes to accomadate behavior specific objects.

ALSO I restructured a lot of the Classes because there were starting to become too many Classes which was starting to hinder my own process when it came to actually programming. I separated the engine into /core, where engine core Classes exists and /input, where the InputManager for the engine is going to be. In the engine exists my Main class used for running the application. I separated /game into /entities (hold life objects), /objects (hold specialized GameObects), and /world (hold world specific classes). I don't think it was necessary to change the structure of my project but it made working on it easier for me. 

##  Interactive Object
I made this class abstract because I want objects that are interactable to be forced to implement some sort of logic for what happens when they're actually interacted with. Right now the class is largely structured around the Coin object which is the reason for the inclusion of the boolean collect and for the inclusion of methods isCollected() and collect(). After actually adding the Coin to the game I'm thinking about making a serparate class for collectable objects as they behave differently (Gathered by Player) than traditional InteractiveObjects (Engaged with to perform an action). Still going to do some more planning before implementing changes.

- abstract method onInteract() which is to be implemented by child classes
Any interactive object must be forced to have some sort of logic that occurs upon interaction however that may be

- setImage() method used to provide sprite images for objects
Thinking about putting this method within the GameObject class as all GameObjects need some sort of image to be rendered

- isCollected() returns the state of the boolean indicating wether or not the InteractiveObject as been collected
- collect() sets the boolean used to indicate if the InteractiveObject has been collected to TRUE
Thinking about adding both of these methods as well as the boolean to a sub-class of InteractiveObjects called Collectable or maybe even CollectableObject

- update() method used to update the object in whatever way necessary in the future
Currently not needed for my simple Coin test case

- draw() method used to draw the sprite image
Currently this method contains logic somewhat specific to the Coin but this can also be added to a Collectable sub-class of some sort in the future.

## StaticSolidObject
Class used for constructing solid objects that don't move, these are mainly going to be for things that obstruct Player path or are for decoration currently very basic. Really because of the nature these objects not much coding was really required for this class. It implements the update() and draw() methods because it extends my GameObject class but has it's own setImage() method. Like with the InteractiveObject class I don't think I need the setImage() method to be in both classes. Since all GameObjects will need to be rendered (have a viewable image for the user) a setter method for this could probably just be put there instead. 

## Coin
Class was created to test the InteractiveObject class contains three methods

- Coin(int x, int y, int width, int height);
Constructor for the coin object, calls super constructor then sets sprite image

- onInteract(), used to handle the interaction logic (being collected for now)
When called onInteract() will set boolean collected, inherited by the InteractiveObject super class, to true indicating the Coin has been picked up by the player. I also just included a simple print statement to the console to just indicate that the object has been collected. Setting the collected boolean to true indicates to the WorldManager class, used to manage all world objects, that the Coin has been picked up so it is no longer necessary to render. 

- getValue(), used to return the provided value of the Coin object
returns an integer value

## SignObject
Class created to test StaticSolidObject class contains three methods, not a lot of overhead required due to the simple nature of StaticSolidObjects. I think I'm going to use these to be purely for decoration and to obstruct the player path. I was thinking that this class could be used to accomodate Buildings sometime in the future but that's skipping a lot of steps lol.

- SignObject(int x, int y, int width, int height)
Constructor for the sign object, calls super constructor then sets sprite image

- update(), not actually implemented as static objects don't need updates

- draw(), used to render sprite-specific image for the sign


## WorldManager Class
With the addition of different types of GameObjects I had update and add a few things to this class.
+ ArrayList to hold InteractiveObjects
+ ArrayList to hold GameObjects that are solid for collision detection
- Modified methods addObject() and removeObject() 
Classes now handle adding objects of specific types
- Modified update() method to take into account my Coin object
I want the Coin object to be collected by the Player when they walk over it, when the Player does walk over the Coin the isInteracted() method will be called setting the Coin's collected boolean to true indicating to the WorldManager that it no longer needs to be rendered. 

## Player
To just get the Coin object working and to see interaction amongst objects I threw in some logic for checking for interaction and handling it. Super simple, when coding this I got the idea for possibly another Behavior class called 'CollectableObject' as not all objects that I want the player to interact with I want to be collected by walking over which is currently the case. Basically there is a check with the WorldManager if an object is an instance of InteractiveObject, then there will be another check to see if the object that is an InteractiveObject has been collected and then if the Player's bounds are intersecting with the bounds of the InteractiveObject if object's collected boolean is false and the the Player intersects the InteractiveObject (Coin) trigger the Coin's onInteract() method. 

- The code currently in place to handle the interaction is specifically tailored to the Coin object for testing purposes this will need to be reworked ovbiously. 
