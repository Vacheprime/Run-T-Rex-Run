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
    private final static int SCROLL_SPEED = 1;
    
    // Time step variables
    private long lastFrameTimeMS;
    private double timeStepDuration; 
    
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
        setPaintOrder(Score.class, Lava.class, TRex.class, Platform.class, Background.class);
        
        
        
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
        
    }
    
    private void updateTimeStep()
    {
        // Update the time step duration
        timeStepDuration = (System.currentTimeMillis() - lastFrameTimeMS) / 1000.0;
        
        // Set the last frame time
        lastFrameTimeMS = System.currentTimeMillis();
    }
 
    private void scrollScreen()
    {
        // Get the Y position of the T-Rex (player)
        TRex player = getObjects(TRex.class).get(0);
        int currentYPosition = player.getY();

        if (currentYPosition >= getHeight() / 2 && isScrolling)
        {    
            isScrolling = false;
            // Stop scrolling the lava downwards
            Lava lava = getObjects(Lava.class).get(0);
            lava.stopScrollDown();
        } else if (isScrolling || currentYPosition < getHeight() / 4)
        {
            isScrolling = true;
            // Scroll all platforms
            List<Platform> platforms = getObjects(Platform.class);
            for (int i = 0; i < platforms.size(); i++)
            {
                platforms.get(i).scrollDown(SCROLL_SPEED);
            }
            if(Greenfoot.getRandomNumber(100) < 4)
        {
            addObject(new Platform(), Greenfoot.getRandomNumber(500) + 130, Greenfoot.getRandomNumber(300));
        }
            // Scroll the lava downwards
            Lava lava = getObjects(Lava.class).get(0);
            lava.scrollDown(SCROLL_SPEED);
            
            // Scroll the player
            player.scrollDown(SCROLL_SPEED);
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Platform platform = new Platform();
        addObject(platform,372,725);
        Platform platform2 = new Platform();
        addObject(platform2,472,667);
        Platform platform3 = new Platform();
        addObject(platform3,578,602);
        Platform platform4 = new Platform();
        addObject(platform4,461,546);
        Platform platform5 = new Platform();
        addObject(platform5,346,503);
        Platform platform6 = new Platform();
        addObject(platform6,242,449);
        Platform platform7 = new Platform();
        addObject(platform7,369,390);
        Platform platform8 = new Platform();
        addObject(platform8,493,342);
        Platform platform9 = new Platform();
        addObject(platform9,374,284);
        Platform platform10 = new Platform();
        addObject(platform10,257,244);
        Platform platform11 = new Platform();
        addObject(platform11,394,175);
        Platform platform12 = new Platform();
        addObject(platform12,516,113);
        Platform platform13 = new Platform();
        addObject(platform13,225,97);
        TRex tRex = new TRex();
        addObject(tRex,365,662);
    }
}
