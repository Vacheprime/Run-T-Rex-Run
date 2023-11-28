import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends World
{
    private static double worldScalingFactor = Volcano.getScalingFactor();
    private GreenfootSound music = new GreenfootSound("WhatIsLeft.mp3");
    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld(int score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(750, 900, 1, false);
        started();
        
        // Scale the background to 1.5 times its size
        
        GreenfootImage img = getBackground();
        img.scale((int) (img.getWidth() * worldScalingFactor), (int) (img.getHeight() * worldScalingFactor));
        setBackground(img);
        
        String gameOverText = String.format("YOU LOST!\nFinal Score: %d", score);
        GreenfootImage textImage = new GreenfootImage(gameOverText, 40, Color.WHITE, null);
        
        
        
        img.drawImage(textImage, getWidth()/2 - textImage.getWidth()/2, getHeight()/2 - textImage.getHeight()/2);
        
        
        
    }
    public void started()
    {
        music.play();
    }
    public void stopped()
    {
        music.stop();
    }
}
