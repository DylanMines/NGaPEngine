import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import NGaPEngine.Engine.*;
import NGaPEngine.GameObject.*;
import NGaPEngine.Input.*;
import NGaPEngine.World.*;

public class TestObject extends WorldObject implements Touchable{
    public TestObject(boolean isStatic, int ypos, int xsize) {
        this.setPosition(new Point(250,ypos));
        this.addCollision(new Dimension(xsize,20), isStatic);
        this.addSprite(new Dimension(xsize,20), new Color(150, 150, 255));
    }
    public void touched(Collision other) {

    }
}
