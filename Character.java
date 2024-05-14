import NGaPEngine.Engine.*;
import NGaPEngine.GameObject.*;
import NGaPEngine.Input.*;
import NGaPEngine.World.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
public class Character extends WorldObject implements InputListener, Touchable{
    private boolean controllable;
    public Character(int xpos, boolean controllable) {
        this.addCollision(new Dimension(20,20), false);
        this.setPosition(new Point(xpos,50));
        this.controllable = controllable;
        this.addSprite(new Dimension(20,20), new Color(255, 150, 150));
        attachInput();
    }
    public void onMouseInput(MouseEvent e) {

    }
    public void onKeyInput(KeyEvent e) {
        if (!controllable) {return;}
        if (e.getKeyCode() == KeyEvent.VK_A) {
            getCollision().velocity.x = -35;
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            getCollision().velocity.x = 35;
        }
        else if (e.getKeyCode() == KeyEvent.VK_W) {
            getCollision().velocity.y = -35;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            getCollision().velocity.y = 35;
        }

    }
    public void onKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            getCollision().velocity.x = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
            getCollision().velocity.y = 0;
        }
    }
    public void touched(Collision other) {

    }
    public void changed() {
        //getCollision().velocity = new Vector2D();
    }
}
