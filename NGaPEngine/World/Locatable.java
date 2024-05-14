package NGaPEngine.World;
import java.awt.*;
public abstract class Locatable {
    private Point position = new Point(); //CENTER POSITION! NOT TOP LEFT! (i think?)
    private Dimension size = new Dimension();
    private Point anchor = new Point(); 
    /*
    Anchor points
    (-1,-1)-----(0,-1)-----(1,-1)
        |          |          |
        |          |          |
    (-1,0)------(0,0)------(1,0) 
        |          |          |
        |          |          |
    (-1,1)------(0,1)------(1,1)
     
     */

    ///Methods
    public abstract void changed(); //Classes that inherit Locatable will have to decide what to do with this

    ///Getters
    /**
     * Returns the position
     * @return The Point of the position
     */
    public Point getPosition() {
        return this.position;
    }
    /**
     * Returns the size
     * @return The Dimension of the size
     */
    public Dimension getSize() {
        return this.size;
    }
    /**
     * Returns the anchor corner
     * @return The Point of the anchor
     */
    public Point getAnchor() {
        return this.anchor;
    }

    ///Setters
    /**
     * Sets the position
     * @param position A point representing the new position
     */
    public void setPosition(Point position) {
        this.position = position;
        this.changed();
    }
    /**
     * Sets the size
     * @param size A dimension representing the new size
     */
    public void setSize(Dimension size) {
        this.size = size;
        this.changed();
    }
    /**
     * Sets the anchor
     * @param anchor A point representing the new anchor corner
     */
    public void setAnchor(Point anchor) {
        this.anchor = anchor;
        this.changed();
    }
    public static Point getCenterPoint(Point corner,Point pos,Dimension size) {
        Point fromCorner = new Point(-corner.x,-corner.y);
        return getCornerPoint(fromCorner, pos, size);
    }
    public static Point getCornerPoint(Point corner,Point pos,Dimension size) { //(-1,1),(1,1),(1,-1),(-1,-1)
        int newX = pos.x+corner.x*(size.width/2);
        int newY = pos.y+corner.y*(size.height/2);
        return new Point(newX,newY);
    }
}
