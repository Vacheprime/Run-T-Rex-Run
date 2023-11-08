import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends World
{

    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(750, 900, 1, false);
        
        // Scale the background to 1.5 times its size
        GreenfootImage img = getBackground();
        img.scale((int) (img.getWidth()*1.5), (int) (img.getHeight()*1.5));
        setBackground(img);
        
        
    }
}
