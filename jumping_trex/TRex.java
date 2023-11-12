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
    private Point2D previousPosition;
    private Vector2D velocity;
    private Vector2D acceleration;
    private int height, width;
    
    // T-Rex hit box
    private final int legHitBox = 27; // Determined manually
    
    // Orientation, moving speed, jump velocity
    private char facing = 'r';
    private int runVelocity = 200;
    private Vector2D jumpVelocity = new Vector2D(0, -700);
    
    // Gravity and max falling speed variables
    private static final double GRAVITY = 9.8 * 100; // 100 px = 1 m
    private static final int MAX_Y_VEL = 450;
    
    public TRex()
    {
        // Scale the T-Rex to 1/4 of its original size
        GreenfootImage img = getImage();
        img.scale(img.getWidth()/4, img.getHeight()/4);
        setImage(img);
        
        // Initialize the TRex
        this.position = null;
        this.previousPosition = null;
        this.velocity = new Vector2D(0,0);
        this.acceleration = new Vector2D(0, GRAVITY);
        
        // Set the fields to the width and height of the scaled image
        this.width = img.getWidth();
        this.height = img.getHeight();
    }
    
    public void addedToWorld(World world)
    {
		// Set the initial position and last frame position to the current
		// X and Y when added to the world
		this.position = new Point2D(getX(), getY());
		this.previousPosition = new Point2D(position);
	}
    
    /**
     * Act - do whatever the TRex wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Execute the movements
        moveTRex();
        
        // Update the physics of the T-Rex
        updatePhysics();
        
        // Detect collision with lava
        if (detectLavaCollision())
        {
			Volcano world = (Volcano) getWorld();
            world.removeObject(this);
            changeWorld(new GameOverWorld(world.getObjects(Score.class).get(0).getScore(), world.getScalingFactor()));
        }
    }
    
    private void moveTRex()
    {
        // When key d or right arrow is pressed
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))
		{
            if (facing != 'r')
            {
                // Change the orientation of the T-Rex to the right
                mirrorImage('h');
                facing = 'r';
            }
            
            if (velocity.getX() != 5)
            {
                velocity.setX(runVelocity);
            }
        
        // When key a or left arrow is pressed
        } 
        else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))
        {
            if (facing != 'l')
            {
                // Chand the orientation of the T-Rex to the left
                mirrorImage('h');
                facing = 'l';
            }
            
            if (velocity.getX() != -5)
            {
                velocity.setX(-runVelocity);
            }
        
        } else if (velocity.getX() != 0)
        {
			velocity.setX(0);
        }
        
        // When key w or up arrow is pressed and the player is not mid-air
        if ( (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up") ) && acceleration.getY() == 0)
        {
            velocity = Vector2D.add(velocity, jumpVelocity); 
        }
    }
    
    private void updatePhysics()
    {
		
        // Detect collisions on platforms and stop the TRex from falling
        int toRelocate = detectPlatformCollisions();
        if (toRelocate != -1 && acceleration.getY() != 0)
        {
			// Only relocate if the T-Rex is falling and not standing still
			// on the platform
            if (acceleration.getY() != 0)
            {
                acceleration.setY(0);
                velocity.setY(0);
                position.setY(toRelocate);
            }
        
        // If there are no collisions, then gravity must be present
        } else if (acceleration.getY() != GRAVITY)
        {
            acceleration.setY(GRAVITY);
        }
        
        // Detect collisions with the volcano borders
        toRelocate = detectBorderCollision();
        if (toRelocate != -1)
        {   
			// If it is a collision on the left border
            if (toRelocate < getWorld().getWidth() / 2)
            {
				if (velocity.getX() < 0)
				{
					velocity.setX(0);
				}
            } 
            else if (velocity.getX() > 0)
            {
                velocity.setX(0);
            }
            position.setX(toRelocate);
        }
        
        // Update the position of the T-Rex on the screen
        updatePosition();
    }
    
    private void mirrorImage(char side)
    {
        switch (side)
        {    
            case 'h':
            {
                // Flip the image horizontally
                GreenfootImage img = getImage();
                img.mirrorHorizontally();
                setImage(img);
                break;
            }
            case 'v':
            {
                // Flip the image vertically
                GreenfootImage img = getImage();
                img.mirrorVertically();
                setImage(img);
                break;
            }
        }
    }
    

    private int detectPlatformCollisions()
    {
        // Get all platforms
        List<Platform> platformObjects = getWorld().getObjects(Platform.class);
        int positionToSurface = -1;
        
        // Get the next Position
        Point2D nextPosition = predictNextPosition();
        
        // Loop for every platform
        for (int i = 0; i < platformObjects.size(); i++)
        {
            Platform platform = platformObjects.get(i);
            
            // get the half height and width of the platforms
            int pfHalfWidth = platform.getImage().getWidth() / 2;
            int pfHalfHeight = platform.getImage().getHeight() / 2;
            
            // Minimal distance between the two actors so that there is no collision
            int minimalXDist = pfHalfWidth + (legHitBox);
            int minimalYDist = pfHalfHeight + (height / 2);
            
            // The distance between the two actors in the next frame
            int distX = Math.abs(platform.getX() - (int) nextPosition.getX());
            int distY = Math.abs(platform.getY() - (int) nextPosition.getY());
            
            // The distance between the two actors in the last frame
            int currentDistX = Math.abs(platform.getX() - (int) position.getX());
            int currentDistY = Math.abs(platform.getY() - (int) position.getY());
            
            // Check for collisions in the next frame
            if (distX <= minimalXDist && distY <= minimalYDist)
            {
				// Check if the TRex was above the platform in the last frame
				if ((int) position.getY() + height/2 <= platform.getY() - pfHalfHeight)
				{
					if (velocity.getY() > 0)
					{
						positionToSurface = platform.getY() - minimalYDist;
					}
				}
				
                // Only a collision if the T-Rex is falling from above
                //if (velocity.getY() > 0  && (getY() + height / 2) < platform.getY())
                //{
                //    positionToSurface = ;
                //}
            }
        }
        // Return where the T-Rex should be on the Y axis
        return positionToSurface;
    }
    
    private int detectBorderCollision()
    {
		// The distance of the left border from the edge of the screen 
        double leftBorderDistance = 50 * 1.5;
        
        // The distance of the right border from the edge of the screen
        double rightBorderDistance = getWorld().getWidth() - leftBorderDistance;
        
        int positionToBorder = -1;
        if (getX() - width/2 <= leftBorderDistance)
        {
            positionToBorder = (int) leftBorderDistance + width/2;
        } else if (getX() + width/2 >= rightBorderDistance) 
        {
            positionToBorder = (int) rightBorderDistance - width/2;
        }
        
        // Return where the T-Rex should be on the X axis
        return positionToBorder;
    }
    
    private boolean detectLavaCollision() {
        
        // The Y location of the lava and of the upper bound of the T-Rex
        int playerUpperBound = getY() + height/2;
        int currentLavaLevel = getWorld().getObjects(Lava.class).get(0).getLavaLevel();
        
        if (playerUpperBound >= currentLavaLevel && currentLavaLevel != -1)
        {
            return true;
        } else
        {
            return false;
        }
    }
    
    private void updatePosition()
    {   
		// Save the current position
		previousPosition.setX(position.getX());
		previousPosition.setY(position.getY());
		
        // Get the next position
        position = predictNextPosition();
        
        // Set the position of the actor
        setLocation((int) position.getX(), (int) position.getY());        
    }
    
    private Point2D predictNextPosition()
    {
        // Get time step duration
        Volcano world = (Volcano) getWorld();
        double dt = world.getTimeStepDuration();
        System.out.println(dt);
        // Update the Y velocity
        Vector2D velocityVariation = Vector2D.multiply(acceleration, dt);
        
        velocity = Vector2D.add(velocity, velocityVariation);
        
        // Make sure the Y velocity does not exceed the maximum
        if (velocity.getY() > MAX_Y_VEL)
        {
            velocity.setY(MAX_Y_VEL); 
        }
        
        // Update position
        Point2D nextPosition = new Point2D(position);
        Vector2D positionVariation = Vector2D.multiply(velocity, dt);
        nextPosition.add(positionVariation); 
        
        return nextPosition;
    }
    
    public void scrollDown(int scrollingSpeed)
    {
        position.setY(getY() + scrollingSpeed);
    }
    
    private void changeWorld(World world)
    {
        Greenfoot.setWorld(world);
    }
}
