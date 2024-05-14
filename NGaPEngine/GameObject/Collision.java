package NGaPEngine.GameObject;

import NGaPEngine.Engine.*;
import NGaPEngine.Input.*;
import NGaPEngine.World.*;
import java.awt.*;
import java.awt.geom.*;

public class Collision extends Component implements Touchable{
    private WorldShape collisionMesh;
    public Vector2D velocity;
    public Vector2D acceleration;
    public boolean hasMass;
    public boolean isStatic;
    private Touchable touchListner;

    ///Constructors
    /**
     * Create a new static collision component
     * @param parent The WorldObject this component belongs to
     */
    public Collision(WorldObject parent) {
        this.parent = parent;
        this.isStatic = true;
        setPosition(new Point());
        setSize(new Dimension(20,20));
        velocity = new Vector2D(0,0);
        acceleration = new Vector2D(0,0);
        hasMass = true;
        ZIndex = 0; // order that objects appear
        
        setAnchor(new Point(0,0));
        collisionMesh = WorldShape.RECTANGLE;
        applyGravity();
    }
    /**
     * Create a new collision component
     * @param parent The WorldObject this component belongs to
     * @param isStatic Determines if this collision object has physics applied
     * @param position The position of this component relative to its parent
     * @param size The collision rectangle size
     */
    public Collision(WorldObject parent, boolean isStatic, Point position, Dimension size) {
        this.parent = parent;
        this.isStatic = isStatic;
        setPosition(position);
        setSize(size);
        velocity = new Vector2D(0,0);
        acceleration = new Vector2D(0,0);
        hasMass = true;
        ZIndex = 0; // order that objects appear
        setAnchor(new Point(0,0));
        collisionMesh = WorldShape.RECTANGLE;
        applyGravity();
    }
    /**
     * Create a new collision component
     * @param parent The WorldObject this component belongs to
     * @param isStatic Determines if this collision object has physics applied
     * @param position The position of this component relative to its parent
     * @param size The collision rectangle size
     * @param initVelocity Starting velocity
     */
    public Collision(WorldObject parent, boolean isStatic, Point position, Dimension size, Vector2D initVelocity) {
        this.parent = parent;
        this.isStatic = isStatic;
        setPosition(position);
        setSize(size);
        this.velocity = initVelocity;
        acceleration = new Vector2D(0,0);
        hasMass = true;
        ZIndex = 0; // order that objects appear
        setAnchor(new Point(0,0));
        collisionMesh = WorldShape.RECTANGLE;
        applyGravity();
    }
    /**
     * Create a new collision component
     * @param parent The WorldObject this component belongs to
     * @param isStatic Determines if this collision object has physics applied
     * @param position The position of this component relative to its parent
     * @param size The collision rectangle size
     * @param initVelocity Starting velocity
     * @param initAcceleration Starting acceleration
     */
    public Collision(WorldObject parent, boolean isStatic, Point position, Dimension size, Vector2D initVelocity, Vector2D initAcceleration) {
        this.parent = parent;
        this.isStatic = isStatic;
        setPosition(position);
        setSize(size);
        this.velocity = initVelocity;
        acceleration = initAcceleration;
        hasMass = true;
        ZIndex = 0; // order that objects appear
        setAnchor(new Point(0,0));
        collisionMesh = WorldShape.RECTANGLE;
        applyGravity();
    }
    /**
     * Create a new collision component
     * @param parent The WorldObject this component belongs to
     * @param isStatic Determines if this collision object has physics applied
     * @param position The position of this component relative to its parent
     * @param size The collision rectangle size
     * @param initVelocity Starting velocity
     * @param initAcceleration Starting acceleration
     * @param hasMass Determines if this object is subject to gravity
     */
    public Collision(WorldObject parent, boolean isStatic, Point position, Dimension size, Vector2D initVelocity, Vector2D initAcceleration, boolean hasMass) {
        this.parent = parent;
        this.isStatic = isStatic;
        setPosition(position);
        setSize(size);
        this.velocity = initVelocity;
        acceleration = initAcceleration;
        this.hasMass = hasMass;
        ZIndex = 0; // order that objects appear
        setAnchor(new Point(0,0));
        collisionMesh = WorldShape.RECTANGLE;
        applyGravity();
    }

    ///Methods
    private void applyGravity() {
        if (hasMass) {
            acceleration.y = AbstractEngine.physics.GRAVITY;
        }
        else {
            acceleration.y = 0;
        }
    }
    
    public void move(double delta) {
        parent.getPosition().x += velocity.x * delta;
        parent.getPosition().y += velocity.y * delta;
        Vector2D vdelta = new Vector2D(acceleration);
        vdelta.multiply(delta);
        velocity.add(vdelta);
        changed();
    }
    public void touched(Collision other) {
        touchListner.touched(this);
    }
    public boolean checkCollision(Collision c) {
        if (isColliding(c)) {
            touched(c);
            return true;
        }
        return false;
    }
    public boolean isColliding(Collision other) {
        Rectangle bounds = getAnchorRectangle();
        Rectangle otherBounds = other.getAnchorRectangle();
        Rectangle intersection = otherBounds.intersection(bounds);
        if (intersection != null) {
            return true;
        }
        return false;
    }
    ///Getters
    public boolean getStatic() {
        return this.isStatic;
    }
    

    //I may not use these
    public Point getNextMove() {
        return new Point((int)(parent.getPosition().x + velocity.x),(int)(parent.getPosition().y + velocity.y));
    }
    public Point getNextMove(double delta) {
        return new Point((int)((parent.getPosition().x + velocity.x) * delta),(int)((parent.getPosition().y + velocity.y) * delta));
    }

    ///Setters
    public void setStatic (boolean isStatic) {
        this.isStatic = isStatic;
    }
    public void setMass (boolean hasMass) {
        this.hasMass = hasMass;
        applyGravity();
    }
    /**
     * Set where this collision component will send touch invokes
     * @param lister A GameObject that implements the Touchable interface. Should be the same as the components parent
     */
    public void setTouchListener(Touchable lister) {
        this.touchListner = lister;
    }
}


