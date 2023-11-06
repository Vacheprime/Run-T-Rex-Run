import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class TRex here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TRex extends Actor
{   
    // Vector information on the position, velocity and acceleration of the T-Rex
    private Point2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private int height, width;
    
    // Orientation, moving speed, jump velocity and gravity variables
    private char facing = 'r';
    private Vector2D runVelocity = new Vector2D(5, 0);
    private Vector2D jumpVelocity = new Vector2D(0, 500);
    private static final double GRAVITY = 9.8 * 300; // 300 px = 1 m
    
    public TRex() {
        // Scale the T-Rex to 1/4 of its original size
        GreenfootImage img = getImage();
        img.scale(img.getWidth()/4, img.getHeight()/4);
        setImage(img);
        
        // Initialize the TRex
        position = null;
        velocity = new Vector2D(0,0);
        acceleration = new Vector2D(0, GRAVITY);
        
        width = img.getWidth();
        height = img.getHeight();
    }
    
    /**
     * Act - do whatever the TRex wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Initialize the initial position of the T-Rex
        if (position == null) {
            position = new Point2D(getX(), getY());
        }
        // Execute the movements
        moveTRex();
    }
    
    public void moveTRex() {
        // When key d or right arrow is pressed
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            if (facing != 'r') {
                // Change the orientation of the T-Rex to the right
                mirrorImage('h');
                facing = 'r';
            }
            
            if (velocity.getX() != 5) {
                velocity.setX(5);
            }
        
        // When key a or left arrow is pressed
        } else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            if (facing != 'l') {
                // Chand the orientation of the T-Rex to the left
                mirrorImage('h');
                facing = 'l';
            }
            
            if (velocity.getX() != -5) {
                velocity.setX(-5);
            }
        
        }
        
        // When key w or up arrow is pressed
        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up") ) {
            velocity = Vector2D.add(velocity, jumpVelocity); 
        }
        
    }
    
    public void mirrorImage(char side) {
        switch (side) {
            case 'h': {
                // Flip the image horizontally
                GreenfootImage img = getImage();
                img.mirrorHorizontally();
                setImage(img);
                break;
            }
            case 'v': {
                // Flip the image vertically
                GreenfootImage img = getImage();
                img.mirrorVertically();
                setImage(img);
                break;
            }
        }
    }
    
    public boolean detectPlatformCollisions() {
        // Get all platforms
        List<Platform> platformObjects = getWorld().getObjects(Platform.class);
        boolean collision = false;
        
        // Loop for every platform
        for (int i = 0; i < platformObjects.size(); i++) {
            Platform platform = platformObjects.get(i);
            
            // get the half height and width of the platforms
            int pfHalfWidth = platform.getImage().getWidth() / 2;
            int pfHalfHeight = platform.getImage().getHeight() / 2;
            
            // Minimal distance between the two actors so that there is no collision
            int minimalXDist = pfHalfWidth + (width / 2);
            int minimalYDist = pfHalfHeight + (height / 2);
            
            // The actual distance between the two actors
            int distX = platform.getX() - getX();
            int distY = platform.getY() - getY();
            
            // Check for collisions
            if (Math.abs(distX) <= minimalXDist && Math.abs(distY) <= minimalYDist) {
                // If the collision happens from the top side of the platform
                if (distY > 0) {
                    collision = true;
            }
        }
    }
    return collision;
}
}
