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
	
    private char facing = 'r';
    private final int VELOCITY_X = 5;
    
    public TRex() {
        // Scale the T-Rex to 1/4 of its original size
        GreenfootImage img = getImage();
        img.scale(img.getWidth()/4, img.getHeight()/4);
        setImage(img);
    }
    
    /**
     * Act - do whatever the TRex wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Execute the movements
        move();
    }
    
    public void move() {
        // When key d or right arrow is pressed
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            if (facing != 'r') {
				// Change the orientation of the T-Rex to the right
                mirrorImage('h');
                facing = 'r';
            }
            
            // Move to the right by the amount defined in velocity x
            setLocation(getX() + VELOCITY_X, getY());
        
        // When key a or left arrow is pressed
        }if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            if (facing != 'l') {
				// Chand the orientation of the T-Rex to the left
                mirrorImage('h');
                facing = 'l';
            }
            
            // Move to the left by the amount defined in velocity x
            setLocation(getX() - VELOCITY_X, getY());
        }
        
    }
    
    public void mirrorImage(char side) {
        switch (side) {
            case 'h' -> {
				// Flip the image horizontally
                GreenfootImage img = getImage();
                img.mirrorHorizontally();
                setImage(img);
            }
            case 'v' -> {
				// Flip the image vertically
				GreenfootImage img = getImage();
				img.mirrorVertically();
				setImage(img);
            }
        }
    }
    
    public void detectPlatformCollisions() {
		List<Platform> platformObjects = getWorld().getObjects(Platform.class);
		
		for (int i = 0; i < platformObjects.size(); i++) {
			Platform platform = platformObjects.get(i);
			
		}
		
	}
}
