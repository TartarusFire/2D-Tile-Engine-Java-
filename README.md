# 2D Tile Engine [Java]
[Easily Convertible]
A lightweight jar or series of files that allows for a visual display of a map of tiles.
  >> An engine that returns collsion values while not constraining movment to unit sizes.

# Downloads
To Be Added

# Features
[This project can be wrapped into a jar and added into an eclipse project as a library for easiest use.]

< This Engine allows for non restricted movement to a unit, so you can have pixel movements even when the unit is greater than 1 >



• Map Engine
  - A static class that allows for only 1 implementation of a map at a time (will Change to an object later on)
  - The class will decide collision of a character or object inside the map
    *** Four values of x,y, width,height must be passed in or use a Hitbox object to determine collsision
    Collsisions are based on blocks being solid

• Block Engine
  - A system that allows for the extension of fully customizable blocks
  - Has default blocks
  - 'Error' colors for null textures on blocks
  - A unit size for all blocks (blocks will be square)
    - The unit will decide all map functions
    - The unit is limited to the range of 0 < X < 256
  - The total block limit is one unsigned short or 2^16-1 different blocks
  
• Hitbox Engine
  - Allows for teh creation of a Hitbox Object if you don't want to hardcode a few values in your own Character class
  
• Lightweight
  - pretty much just a few objects that allow for easy versatility
