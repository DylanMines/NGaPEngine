package NGaPEngine.Engine;
import javax.swing.JFrame;

import NGaPEngine.GameObject.*;
import NGaPEngine.Input.*;
import NGaPEngine.World.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.sound.sampled.*;
import java.io.*;
//import org.json.simple.*;
public final class AbstractEngine extends JFrame implements Runnable, KeyListener{
    final int FPS_CAP = 60;
    final boolean FULLSCREEN = false;
    final Dimension WINDOWED_SIZE = new Dimension(1280,720); //TODO: replace with the values in settings.json
    final static String TITLE = "NGaP Engine"; //Not Good at Physics
    public static Render render;
    public static Physics physics;
    public static boolean running;
    private boolean physrunning = true; // true by default
    private static ArrayList<InputListener> inputListeners = new ArrayList<>();

    public AbstractEngine() { //Initialization
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        if (FULLSCREEN) {
            setExtendedState(JFrame.MAXIMIZED_BOTH); 
            setUndecorated(true);
            render = new Render(TITLE, new Dimension(getWidth(),getHeight()));
        }
        else {
            setSize(WINDOWED_SIZE);
            setResizable(false);
            render = new Render(TITLE, WINDOWED_SIZE);
        }
        setVisible(true);
        add(render);
        

        validate();


        physics = new Physics();
    }

    public static void addInputListener(InputListener Il) {
        inputListeners.add(Il);
    }

    public void keyPressed(KeyEvent e) {
        for (InputListener I: inputListeners) {
            I.onKeyInput(e);
        }
    }
    public void keyReleased(KeyEvent e) {
        for (InputListener I: inputListeners) {
            I.onKeyRelease(e);
        }
    }
    public void keyTyped(KeyEvent e) {}

    public synchronized void stop() { //thread stop note: also stops rendering
        running = false;
    }
    public synchronized void stopPhysics() { //keep rendering but stop physics
        physrunning = false;
    }
    public synchronized void begin() { //thread start
        running = true;
        new Thread(this).start();
    }
    public void run() { //game loop thread
        //running = true;
        
        //Timing enviornment variables
        long lastLoopTime = System.nanoTime();
        
        final long OPTIMAL_TIME = 1000000000 / FPS_CAP;	

        //Init
        gameloop:
        while(running) {
            //timing calculation
            long now = System.nanoTime();
		    long updateLength = now - lastLoopTime;
		    lastLoopTime = now;
		    double delta = updateLength / ((double)OPTIMAL_TIME);
            //

            //input
            //physics
            physics.PhysicsStep(delta,physrunning);

            //render
            render.draw(); //Draw to the screen
           // System.out.println(100/(updateLength/1e6)*10);
            //timing wait at end of frame
            double wait_time = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;
            if (wait_time <=0) {continue gameloop;}
            try{Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );}
            catch(InterruptedException e) {System.out.println(e);}
        }
    }
   // public static Object getSettingsTable(String title) {

    //}
} 