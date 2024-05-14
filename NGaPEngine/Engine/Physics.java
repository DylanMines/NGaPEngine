package NGaPEngine.Engine;

import java.util.ArrayList;

import javax.naming.NameAlreadyBoundException;

import java.awt.geom.*;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.*;
import java.awt.geom.*;

import NGaPEngine.GameObject.*;
import NGaPEngine.Input.*;
import NGaPEngine.World.*;

public final class Physics {
    private ArrayList<Collision> dynamicObjects = new ArrayList<>(); //Things that can move and collide
    private ArrayList<Collision> staticObjects = new ArrayList<>(); //Things that don't move

    public final double GRAVITY = 9.8;
    public Physics() {

    }

    /**
     * Execute one physics step
     * @param delta The amount of time since last frame
     * @param running Boolean value representing the state of the AbstractEngine
     */
    public void PhysicsStep(double delta, boolean running) {
        delta *= 0.1;
        //System.out.println(60+(60-(delta*600)));
        if (!running) delta = 0;
        if (AbstractEngine.running) {
            //Tell dynamic objects to move
                for (int z = 0;z<dynamicObjects.size();z++) {
                    dynamicObjects.get(z).move(delta);
                }

            /*  CALCULATING COLLISIONS:
             * loop through dynamic objects and check with dynamic and static objects 
             * since static objects are stationary, they do not need to be checked as if they collide with something else is has to be either a dynamic object,
             * or unimportant
             * big O notation (probably wrong) O(D^2 + D log S)
             * if that big O is right tho its still more efficient than O((S+D)^2)
             */
            DetectCollisions(running);
            

            /*  RESOLVING COLLISIONS:
             * no idea
             */

        }
    }

    private void DetectCollisions(boolean running) {
        loop:
        for (int D1 = 0; D1 < dynamicObjects.size(); D1++) { //loop through all dynamic objects
            if (!running) {continue loop;}
            dynamic:
            for (int D2 = 0; D2 < dynamicObjects.size(); D2++) { //check other dynamic objects (don't check collision between 2 objects twice)
            if (D1==D2) {continue dynamic;}
            if (dynamicObjects.get(D1).getAnchorRectangle().intersects(dynamicObjects.get(D2).getAnchorRectangle())) {
                ResolveDynamicCollision(dynamicObjects.get(D1),dynamicObjects.get(D2)); 
            }
            }
            for (int S = 0; S<staticObjects.size(); S++) { //check collision with static objects (for this specific dynamic object)
                if (dynamicObjects.get(D1).getAnchorRectangle().intersects(staticObjects.get(S).getAnchorRectangle())) {
                    ResolveStaticCollision(dynamicObjects.get(D1),staticObjects.get(S)); 
                }
               // System.out.println("Checking collision between a static and dymanic object");
            }
        }
    }

    private void ResolveDynamicCollision(Collision c1, Collision c2) {
        System.out.println("resolving collision");
        Rectangle intersection = c1.getAnchorRectangle().intersection(c2.getAnchorRectangle());
        Vector2D v1 = c1.velocity.getUnitVector();
        Vector2D v2 = c2.velocity.getUnitVector();
        Point newPos1 = c1.getParentPosition();
        Point newPos2 = c2.getParentPosition();
        
        //c1.velocity = new Vector2D();
        Vector2D newVelocity1 = c1.velocity;
        Vector2D newVelocity2 = c2.velocity;
        //System.out.println(c1.getSize().getWidth());
        if (intersection.getWidth() < c1.getSize().getWidth() && intersection.getWidth() < c2.getSize().getWidth()) {
            newPos1.x -= (int)((intersection.getWidth()/2)*v1.x);
            newVelocity1.x = 0;
            newPos2.x += (int)((intersection.getWidth()/2)*v2.x);
            newVelocity2.x = 0;
        }
        if (intersection.getHeight() < c1.getSize().getHeight() && intersection.getHeight() < c2.getSize().getHeight()) {
            newPos1.y -= (int)((intersection.getHeight()/2)*v1.y);
            newVelocity1.y = 0;
            newPos2.y += (int)((intersection.getHeight()/2)*v2.y);
            newVelocity2.y = 0;
        }
        c1.velocity = newVelocity1;
        c2.velocity = newVelocity2;
        c1.getParent().setPosition(newPos1);
        c2.getParent().setPosition(newPos2);
    }
    private void ResolveStaticCollision(Collision c1, Collision c2) { //c2 is static
        Rectangle intersection = c1.getAnchorRectangle().intersection(c2.getAnchorRectangle());
        Vector2D v = c1.velocity.getUnitVector();
        Point newPos = c1.getParentPosition();
        
        //c1.velocity = new Vector2D();
        Vector2D newVelocity = c1.velocity;
        //System.out.println(c1.getSize().getWidth());
        if (intersection.getWidth() < c1.getSize().getWidth()) {
            newPos.x -= (int)(intersection.getWidth()*v.x);
            newVelocity.x = 0;
        }
        if (intersection.getHeight() < c1.getSize().getHeight()) {
            newPos.y -= (int)(intersection.getHeight()*v.y);
            newVelocity.y = 0;
        }
        c1.velocity = newVelocity;
        c1.getParent().setPosition(newPos);
        //System.out.println(intersection);
        //c1.velocity = new Vector2D(-v.x*0.3,-v.y*0.3); 
    }

    /**
     * Adds a dynamic collision component to the list of components to process
     * @param c The collision object to be added
     */
    public synchronized void addDynamicObject(Collision c) {
        int index = Math.min(c.getZIndex(),dynamicObjects.size());
        dynamicObjects.add(index,c);
    }
    /**
     * Adds a static collision component to the list of components to process
     * @param c The collision object to be added
     */
    public synchronized void addStaticObject(Collision c) {
        int index = Math.min(c.getZIndex(),staticObjects.size());
        staticObjects.add(index,c);
    }
    /**
     * removes a dynamic collision component from the list of components to process
     * @param c The collision object to be removed
     */
    public synchronized void removeDynamicObject(Collision c) {
        dynamicObjects.remove(c);
    }
    /**
     * removes a static collision component from the list of components to process
     * @param c The collision object to be removed
     */
    public synchronized void removeStaticObject(Collision c) {
        dynamicObjects.remove(c);
    }
}

