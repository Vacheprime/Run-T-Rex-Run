import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class volcano here.
 * 
 * @author Shaheryar Anwar and Danat Ali Muradov 
 * @version 1
 */
public class Volcano extends World
{
    // Scalling factor
    private static double scalingFactor = 1.5;
    
    // Scrolling variables
    private boolean isScrolling = false;
    private final int scrollLine = 2;
    
    // Time step variables
    private long lastFrameTimeMS;
    private double timeStepDuration;
    
    private static int borderWidth = (int) (50 * scalingFactor);
    /**
     * Constructor for objects of class volcano.
     * 
     */
    public Volcano(long lastFrameTimeMS)
    {    
        // Create a new volcano world with 750x900 cells with a cell size of 1x1 pixels.
        super(750, 900, 1, false);
        // Set the current time
        this.lastFrameTimeMS = lastFrameTimeMS;
        
        // Create a new score actor
        Score score = new Score(lastFrameTimeMS - System.currentTimeMillis());
        addObject(score, 30, 20);
        
        // Remove the background
        setBackground((GreenfootImage) null);
        
        // Create two background actors that are goin to be scrolling
        addObject(new Background(), getWidth() / 2, getHeight() / 2);
        addObject(new Background(), getWidth() / 2, getHeight() / 2);
        
        
        // Set the lava to be at its lower limit
        Lava lava = new Lava();
        addObject(lava, getWidth() / 2, getHeight() + lava.getLavaLowerLimit());
        
        // Draw the actors in the right order
        setPaintOrder(Score.class, Aura.class, Lava.class,TRex.class, Powerup.class, Platform.class, Background.class);
        
        // Populate the volcano
        prepare();
    }
      
    public double getTimeStepDuration()
    {
        return timeStepDuration;
    }
    
    public static double getScalingFactor()
    {
        return scalingFactor;
    }
    
    public void act()
    {
        // Update the time step duration
        updateTimeStep();
        
        // Scroll the screen
        scrollScreen();
        
        // Generate powerups
        generatePowerups();
        
        // Generate platforms
        generatePlatforms();
    }
    
    private void updateTimeStep()
    {
        // Update the time step duration
        timeStepDuration = (System.currentTimeMillis() - lastFrameTimeMS) / 1000.0;
        
        // Set the last frame time
        lastFrameTimeMS = System.currentTimeMillis();
    }
    
    private void generatePlatforms()
    {
        // Get the last platform spawned
        List<Platform> platforms = getObjects(Platform.class);
        Platform previousPlatform = platforms.get(platforms.size() - 1);
        int positionX = previousPlatform.getX();
        int positionY = previousPlatform.getY();
        //System.out.println(positionY);
        int minDistanceX = 70;
        int minDistanceY = 50;
        int rangeX = 150;
        int rangeY = 90;
        //System.out.println(positionX + " " + positionY);
        if (positionY > -100) {
            int x = 0;
            int side = Greenfoot.getRandomNumber(2) + 1;
            // Right side
            if (side == 1) {
                // Check if it is possible to spawn on the right
                if (positionX + rangeX < getWidth() - borderWidth) {
                    x = positionX + (Greenfoot.getRandomNumber(rangeX - minDistanceX) + minDistanceX);
                // If not, spawn on the left
                } else {
                    x = positionX - (Greenfoot.getRandomNumber(rangeX - minDistanceX) +  minDistanceX);
                }
            // Left side
            } else {
                // Check if it is possible to spawn on the left
                if (positionX - rangeX > borderWidth) {
                    x = positionX - (Greenfoot.getRandomNumber(rangeX - minDistanceX) +  minDistanceX);
                // If not, spawn on the right
                } else {
                    x = positionX + (Greenfoot.getRandomNumber(rangeX - minDistanceX) + minDistanceX);
                }
            }
            
            int y = positionY - (Greenfoot.getRandomNumber(rangeY - minDistanceY) + minDistanceY);
            addObject(new Platform(), x, y);
        }
    }
    
    private void generatePowerups()
    {
        int randomNumber = Greenfoot.getRandomNumber(10000);
        // Create a shield
        if (randomNumber < 1)
        {
            Shield shield = new Shield();
            addObject(shield, Greenfoot.getRandomNumber(750 - 2 * borderWidth) + borderWidth, 0);
        } else if (randomNumber < 5) 
        {
            ScoreMultiplier scoreMult = new ScoreMultiplier();
            addObject(scoreMult, Greenfoot.getRandomNumber(750 - 2 * borderWidth) + borderWidth, 0);
        } else if (randomNumber < 10) {
            Meat meat = new Meat();
            addObject(meat, Greenfoot.getRandomNumber(750 - 2 * borderWidth) + borderWidth, 0);
        }
    }
 
    private void scrollScreen()
    {
        // Get the Y position of the T-Rex (player)
        TRex player = getObjects(TRex.class).get(0);
        int currentYPosition = player.getY();

        if (currentYPosition > getHeight() / scrollLine && isScrolling)
        {    
            isScrolling = false;
            // Stop scrolling the lava downwards
            Lava lava = getObjects(Lava.class).get(0);
            lava.stopScrollDown();
        } else if (isScrolling || (currentYPosition < getHeight() / scrollLine))
        {
            isScrolling = true;
            int distToScrollLine= Math.abs(currentYPosition - getHeight() / scrollLine);
            int scrollingSpeed = distToScrollLine / 15;
            // Set a minimal scrolling speed
            if (scrollingSpeed < 1)
            {
                scrollingSpeed = 1;
            }
            // Scroll all platforms
            List<Platform> platforms = getObjects(Platform.class);
            for (int i = 0; i < platforms.size(); i++)
            {
                platforms.get(i).scrollDown(scrollingSpeed);
            }
            // Scroll all powerups
            List<Powerup> powerups = getObjects(Powerup.class);
            for (int i = 0; i < powerups.size(); i++)
            {
                powerups.get(i).scrollDown(scrollingSpeed);
            }
            // Scroll the lava downwards
            Lava lava = getObjects(Lava.class).get(0);
            lava.scrollDown(scrollingSpeed);
            
            // Scroll the player
            player.scrollDown(scrollingSpeed);
        }
    }
    
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        TRex tRex = new TRex();
        addObject(tRex,377,681);
        Platform platform = new Platform();
        addObject(platform,380,760);
    }
}
