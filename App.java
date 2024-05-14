import NGaPEngine.Engine.*;
import NGaPEngine.GameObject.*;
import NGaPEngine.Input.*;
import NGaPEngine.World.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public final class App {
    public static AbstractEngine AE;
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true"); //Enable hardware acceleration (use the gpu)
        AE = new AbstractEngine();
        AE.begin();
        new Character(50, true);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(false, 200,20);
        new TestObject(true, 550,700);
        //new TestObject(true, 500,300);
    }
}