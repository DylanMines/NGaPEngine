package NGaPEngine.GameObject;

import java.awt.*;

import NGaPEngine.World.*;
public abstract class Component extends Locatable{
    protected WorldObject parent;
    protected int ZIndex; //Order of processing (where the component ends up in its controllers array)
    //Object methods

    //GETTERS
    /**
     * Gets the component's parent
     * @return The WorldObject this component belongs to
     */
    public WorldObject getParent() {
        return this.parent;
    }
    public Point getParentPosition() {
        return this.parent.getPosition();
    }
    /**
     * Gets the component's ZIndex
     * @return The ZIndex of this component
     */
    public int getZIndex() {
        return this.ZIndex;
    }
    public Rectangle getAnchorRectangle() { //Not sure what these do
        Point anchor = getAnchor();
        Point position = this.getPosition();
        Point parentPosition = this.getParent().getPosition();
        Dimension size = getSize();
        Point corner = getCornerPoint(anchor,getCornerPoint(new Point(-1,-1),position,size),size);
        return new Rectangle(corner.x+parentPosition.x, corner.y+parentPosition.y, size.width, size.height);
    }
    /*public Rectangle getAnchorRectangle(Point pos) {
        Point corner = getCornerPoint(anchor,getCornerPoint(new Point(-1,-1),position,size),size);
        return new Rectangle(corner.x+pos.x, corner.y+pos.y, size.width, size.height);
    }*/
    //METHODS
    /**
     * Invoked whenever a property of a component is changed. Passes on to a componenets WorldObject.
     */
    public void changed() {
        this.parent.changed();
    }

    //SETTERS
    /**
     * Change the parent of this component
     * @param parent new parent
     
    public void setParent(WorldObject parent) { //Use caution when using (why?)
        WorldObject oldParent = this.parent;
        oldParent.removeComponenet(this);
        parent.addComponent(this);
        this.parent = parent;
    }*/
    public void setZIndex(int ZIndex) {
        this.ZIndex = ZIndex;
    }
}
