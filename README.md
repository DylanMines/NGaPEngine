# NGaPEngine
 Not Good at Physics Engine
# Created by Dylan Mines

## Welcome to the Not Good at Physics engine! Here's an introduction


### Importing the engine
The engine is split into 4 packages: 

Engine:
The core parts of the NGaP system. This package is imported into app.java to initilize the engine. Relavant settings for each part (engine, rendering, physics) are located in each class. You shouldn't have to import this package into any other files.

GameObject:
The GameObject package has the main parts for creating WorldObjects, the fundamental part of the engine. This package also includes all the components.

Input:
The only file relavant in this package is InputListener.java, and allows adding input to WorldObjects/

World:
This package deals with the physical world that the WorldObjects reside in.

Import a package by putting at the top of your file:
```
import NGaPEngine.Engine.*; //for all files in the Engine package
import NGaPEngine.GameObject.*; //for all files in the GameObject package
import NGaPEngine.Input.InputListener; //import just the imput listener file
```

### Setting up the engine
It is crucial that the app.java file is setup correctly. It must import `NGaPEngine.Engine.*;` and have a main method. app.java should initialize the AbstractEngine as so: `public static AbstractEngine AE;`.

Inside the main method, it must contain
```
System.setProperty("sun.java2d.opengl", "true"); //Enable hardware acceleration (use the gpu)
AE = new AbstractEngine();
AE.begin();
```

### World objects
The engine uses WorldObjects to do create objects in 2D space. To create one, first import the GameObject package, then write your class:
`public class <ClassName> extends WorldObject`. 
In the class constructor, create a component, like a sprite, by writing
`Sprite s = new Sprite(arguments);`.
Add a component to the WorldObject by writing
`this.addComponent(s);`. 
Or do both at once
`this.addComponent(new Sprite(arguments));`

**Each WorldObject can only have one of each component!**

Each component has its own position, like an offset from its parent. Each component will still follow it's parent position.

### Rendering
The engine will render at 60 fps by default. You can change this by changing the FPS_CAP in AbstractEngine.java. The rendering engine will render Sprites before TextLabels. Each rendering component (sprite/textlabel) has a ZIndex that represents the order in which it is rendered, lower first. If, for whatever reason, you wish to create your own drawing method, create a class and extend the sprite component. In your new class, overwrite the draw method and use Java's `Graphics2D` library to draw something to the screen.

### Physics
The Not Good at Physics engine has very questionable physics most of the time, but heres the basics:
In order for a WorldObject to have a collision component, it MUST implement the Touchable interface. Add a collision component like any other component by using `this.addComponent(c);` Each collision object can either by dynamic or static. Static objects cannot have a velocity or acceleration. Dynamic components have a velocity and acceleration instance variables that will automatically move the object each physics cycle. Dynamic components can also be affected by gravity if their mass is greater than 0. Both static and dymanic objects will inkove their parents `touched()` method upon contact with another collision object. Each collision object has a size instance variable that determines its bounding box. A collision components bounding box is completly independant from any other component or its parent's size.
