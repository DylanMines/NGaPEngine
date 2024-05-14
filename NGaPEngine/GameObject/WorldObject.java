package NGaPEngine.GameObject;

import NGaPEngine.Engine.*;
import NGaPEngine.World.*;
import java.awt.*;

public abstract class WorldObject extends Locatable{
    private Sprite sprite;
    private Collision collision;
    private TextLabel textLabel;
    private Audio audio;

    ///Constructors
    /**
     * Class constructor
     */
    public WorldObject() {
        this.setPosition(new Point(0,0));
        this.setSize(new Dimension(20,20));
    }
    /**
     * Class constructor
     * @param position Initial position of the world object
     */
    public WorldObject(Point position) {
        setPosition(position);
        this.setSize(new Dimension(20,20));
    }
    //TODO: Calculate bounding box based on componenets
    ///Methods
    /**
     * Adds a component to the GameObject
     * @param c The component to be added
     */
    public void addComponent(Component c) throws IllegalStateException {
        if (c.getClass().equals(Sprite.class)) { //sprite
            this.sprite = (Sprite)c;
            AbstractEngine.render.addSprite((Sprite)c);
        }
        else if (c.getClass().equals(Collision.class)) { //collision
            if (!Touchable.class.isInstance(this)) {
                throw new IllegalStateException("WorldObject must implement Touchable to have a collision object!");
            }
            Collision collision = (Collision)c;
            this.collision = collision;
            collision.setTouchListener((Touchable)this);
            if (collision.getStatic() == true) {
                AbstractEngine.physics.addStaticObject(collision);
            }
            else {
                AbstractEngine.physics.addDynamicObject(collision);
            }
            
        }
        else if (c.getClass().equals(TextLabel.class)) { //text label
            this.textLabel = (TextLabel)c;
            AbstractEngine.render.addLabel((TextLabel)c);
        }
        changed();
    }

    //TODO: tell the render or physics script a component was removed
    /**
     * Removes a component from the GameObject
     * @param c The component to be removed
     
    public void removeComponenet(Component c) {
        components.remove(c);
        changed();
    }*/

    public void changed() {
        //calculate bounds
    }

    public void addSprite(Dimension size, Color color) {
        this.addComponent(new Sprite(this, new Point(), size, color));
    }
    public void addSprite(Dimension size, Color color, WorldShape shape) {
        Sprite s = new Sprite(this, new Point(), size, color);
        s.setShape(shape);
        this.addComponent(s);
    }
    public void addCollision(Dimension size, boolean isStatic) {
        this.addComponent(new Collision(this,isStatic,new Point(),size));
    }
    public void addCollision(Dimension size, boolean isStatic, boolean mass) {
        this.addComponent(new Collision(this,isStatic,new Point(),size,new Vector2D(),new Vector2D(),mass));
    }

    ///Getters
    public Collision getCollision() throws IllegalComponentStateException{
        if (this.collision != null) {return this.collision;}
        throw new IllegalComponentStateException("No collision objects exist");
    }
    public Point getPosition() {
        System.out.println(super.getPosition());
        return super.getPosition();
    }
}
