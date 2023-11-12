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
    public GameOverWorld(int score, double scalingFactor)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(750, 900, 1, false);
        
        // Scale the background to 1.5 times its size
        GreenfootImage img = getBackground();
        img.scale((int) (img.getWidth() * scalingFactor), (int) (img.getHeight() * scalingFactor));
        setBackground(img);
        
        String gameOverText = String.format("YOU LOST!\nFinal Score: %d", score);
        GreenfootImage textImage = new GreenfootImage(gameOverText, 40, Color.WHITE, null);
        
        
        
        img.drawImage(textImage, getWidth()/2 - textImage.getWidth()/2, getHeight()/2 - textImage.getHeight()/2);
        
        
    }
}
