import NGaPEngine.Engine.*;
import NGaPEngine.GameObject.*;
import NGaPEngine.Input.*;
import NGaPEngine.World.*;

public final class App {
    public static AbstractEngine AE;
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true"); //Enable hardware acceleration (use the gpu)
        AE = new AbstractEngine();
        AE.begin();
    }
}
