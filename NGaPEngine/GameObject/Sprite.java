package NGaPEngine.GameObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;

import NGaPEngine.Input.*;
import NGaPEngine.World.*;

public class Sprite extends Component{
    private Color color;
    private WorldShape shape;
    private int ZIndex;
    private File image;

    //CONSTRUCTORS
    /**
     * Sprite constructor
      *  @param parent The worldObject that the component belongs to
    */
    public Sprite(WorldObject parent) {
        this.parent = parent;
        setPosition(new Point());
        setSize(new Dimension(20,20));
        color = Color.BLACK;
        ZIndex = 0; // order that objects appear
        setAnchor(new Point(0,0));
        shape = WorldShape.ELLIPSE;
    }
    /**
     * Sprite constructor
     * @param parent The worldObject that the component belongs to
     * @param position The position on the screen in pixels
     * @param size The size of the sprites bounding box
     */
    public Sprite(WorldObject parent, Point position, Dimension size) {
        this.parent = parent;
        setPosition(position);
        setSize(size);
        color = Color.BLACK;
        ZIndex = 0; // order that objects appear
        setAnchor(new Point(0,0));
        shape = WorldShape.RECTANGLE;
    }
    /**
     * Sprite constructor
     * @param parent The worldObject that the component belongs to
     * @param position The position on the screen in pixels
     * @param size The size of the sprites bounding box
     * @param color The Color the sprite will appear (tint if image)
     */
    public Sprite(WorldObject parent, Point position, Dimension size, Color color) {
        this.parent = parent;
        setPosition(position);
        setSize(size);
        this.color = color;
        ZIndex = 0; // order that objects appear
        setAnchor(new Point(0,0));
        shape = WorldShape.RECTANGLE;
    }
    /**
     * Sprite constructor
     * @param parent The worldObject that the component belongs to
     * @param position The position on the screen in pixels
     * @param size The size of the sprites bounding box
     * @param color The Color the sprite will appear (tint if image)
     * @param ZIndex The order that the sprite is rendered
     */
    public Sprite(WorldObject parent, Point position, Dimension size, Color color, int ZIndex) {
        this.parent = parent;
        setPosition(position);
        setSize(size);
        this.color = color;
        this.ZIndex = ZIndex; // order that objects appear
        setAnchor(new Point(0,0));
        shape = WorldShape.RECTANGLE;
    }
    /**
     * Sprite constructor
     * @param parent The worldObject that the component belongs to
     * @param position The position on the screen in pixels
     * @param size The size of the sprites bounding box
     * @param color The Color the sprite will appear (tint if image)
     * @param ZIndex The order that the sprite is rendered
     * @param anchor The corner where the position property is applied
     */
    public Sprite(WorldObject parent, Point position, Dimension size, Color color, int ZIndex, Point anchor) {
        this.parent = parent;
        setPosition(position);
        setSize(size);
        this.color = color;
        this.ZIndex = ZIndex; // order that objects appear
        setAnchor(new Point(0,0));
        shape = WorldShape.RECTANGLE;
    }
    /**
     * Sprite constructor
     * @param parent The worldObject that the component belongs to
     * @param position The position on the screen in pixels
     * @param size The size of the sprites bounding box
     * @param color The Color the sprite will appear (tint if image)
     * @param anchor The corner where the position property is applied
     */
    public Sprite(WorldObject parent, Point position, Dimension size, Color color, Point anchor) {
        this.parent = parent;
        setPosition(position);
        setSize(size);
        this.color = color;
        this.ZIndex = 0; // order that objects appear
        anchor = new Point(0,0);
        shape = WorldShape.RECTANGLE;
    }

    ///Methods
    
    ///Getters
    public Color getColor() {
        return this.color;
    }
    ///Setters
    
    public void setColor(Color color) {
        this.color = color;
    }  
    public void setShape(WorldShape shape) {
        this.shape = shape;
        //System.out.println(this.shape);
    }


    //TODO: Create setImage method once the image thing is ready.

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        Rectangle drawArea = getAnchorRectangle();
        //System.out.println(shape);
        switch (shape) {
            case RECTANGLE:
                g2d.fillRect(drawArea.x,drawArea.y,drawArea.width,drawArea.height);
                break;
            case ELLIPSE:
                g2d.fillOval(drawArea.x,drawArea.y,drawArea.width,drawArea.height);
                break;
        }
    }
}
