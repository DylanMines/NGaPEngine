package NGaPEngine.Input;

import NGaPEngine.Engine.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InputListener {
    /**
     * Invoked when a key is pressed on the keyboard
     */
    public void onKeyInput(KeyEvent e);
    /**
     * Invoked when a key is released from the keyboard
     */
    public void onKeyRelease(KeyEvent e);
    /**
     * Invoked when the mouse has an event
     */
    public void onMouseInput(MouseEvent e);
    /**
     * Allows the GameObject to recieve input
     */
    default public void attachInput() {
        AbstractEngine.addInputListener(this);
    }
}
