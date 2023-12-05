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
    
    // T-Rex hit box
    private final int legHitBox = 27; // Determined manually
    
    // Orientation, moving speed, jump velocity
    private static double scalingFactor = 0.25;
    private static double worldScalingFactor = Volcano.getScalingFactor();
    private char facing = 'r';
    private int runVelocity = 200;
    private Vector2D jumpVelocity = new Vector2D(0, -600);
    
    // Gravity and max falling speed variables
    private static final double GRAVITY = 9.9 * 100; // 100 px = 1 m
    private static final int MAX_Y_VEL = 450;
    private static GreenfootSound music1 = new GreenfootSound("battle.mp3");
    
    // Power up vars
    private boolean isInvincible = false;
    private Powerup[] allPowerups = new Powerup[3];
    
    public TRex()
    {
        // Scale the T-Rex to 1/4 of its original size
        GreenfootImage img = getImage();
        img.scale((int) (img.getWidth() * scalingFactor), (int) (img.getHeight() * scalingFactor));
        setImage(img);
        
        // Initialize the TRex
        this.position = null;
        this.velocity = new Vector2D(0,0);
        this.acceleration = new Vector2D(0, GRAVITY);
        
        // Set the fields to the width and height of the scaled image
        this.width = img.getWidth();
        this.height = img.getHeight();
    }
    
    public Vector2D getAcceleration()
    {
        return new Vector2D(acceleration);
    }
    
    public void addedToWorld(World world)
    {
        // Set the initial position and last frame position to the current
        // X and Y when added to the world
        this.position = new Point2D(getX(), getY());
        startMusic();
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
        // Collect powerups
        collectPowerups();
        // Manage powerups
        managePowerups();
        // Detect collision with lava
        if (detectLavaCollision())
        {
            // Stop music
            stopMusic();
            World world = getWorld();
            world.removeObject(this);
            changeWorld(new GameOverWorld(world.getObjects(Score.class).get(0).getScore()));
            stopMusic();
        }
    }
    
    public void setIsInvincible(boolean inv)
    {
        isInvincible = inv;
    }
    
    public void setJumpVelocity(int vel)
    {
        jumpVelocity.setY(-vel);
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
                    // Check if the T-Rex is falling down onto the paltform
                    if (velocity.getY() > 0)
                    {
                        positionToSurface = platform.getY() - minimalYDist;
                    }
                }
            }
        }
        // Return where the T-Rex should be on the Y axis
        return positionToSurface;
    }
    
    private int detectBorderCollision()
    {
        // The distance of the left border from the edge of the screen 
        double leftBorderDistance = 50 * worldScalingFactor;
        
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
            if (isInvincible) {
                // Bounce back up
                velocity.setY(-2000);
                return false;
            } else {
                return true;
            }
        } else
        {
            return false;
        }
    }
    
    private void updatePosition()
    {
        
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
    
    private void collectPowerups()
    {
        Powerup powerup = (Powerup) getOneIntersectingObject(Powerup.class);
        if (powerup != null)
        {
            // Play sound effect
            Greenfoot.playSound("powerup.mp3");
            // Remove the powerup from the world
            getWorld().removeObject(powerup);
            // Add it to the powerups to track
            if (allPowerups[powerup.getID()] != null)
            {
                allPowerups[powerup.getID()].removeAura(this);
            }
            allPowerups[powerup.getID()] = powerup;
            // Activate it on the T-Rex
            switch (powerup.getID())
            {
                case 0:
                {
                    Shield shield = (Shield) powerup;
                    shield.activate(this);
                    break;
                }
                
                case 1:
                {
                    ScoreMultiplier scoreMult = (ScoreMultiplier) powerup;
                    scoreMult.activate(this);
                    break;
                }
                
                case 2:
                {
                    Meat meat = (Meat) powerup;
                    meat.activate(this);
                    break;
                }
            }
        }
    }
    
    private void managePowerups()
    {
        for (Powerup powerup: allPowerups)
        {
            if (powerup != null) {
                switch (powerup.getID())
                {
                    // Shield
                    case 0:
                    {
                        Shield shield = (Shield) powerup;
                        if (shield.isOver()) {
                            shield.deactivate(this);
                            allPowerups[0] = null;
                        }
                        break;
                    }
                    
                    case 1:
                    {
                        ScoreMultiplier scoreMult = (ScoreMultiplier) powerup;
                        if (scoreMult.isOver()) {
                            scoreMult.deactivate(this);
                            allPowerups[1] = null;
                        }
                        break;
                    }
                    
                    case 2:
                    {
                        Meat meat = (Meat) powerup;
                        if (meat.isOver()) {
                            meat.deactivate(this);
                            allPowerups[2] = null;
                        }
                        break;
                    }
                }
            }
        }
        
    }
    
    public void startMusic()
    {
        music1.playLoop();
    }
    public void stopMusic()
    {
        music1.stop();
    }
}
