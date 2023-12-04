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
    private static GreenfootSound music = new GreenfootSound("WhatIsLeft.mp3");
    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld(int score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(750, 900, 1, false);
        startMusic();
        
        // Scale the background to 1.5 times its size
        
        GreenfootImage img = getBackground();
        img.scale((int) (img.getWidth() * worldScalingFactor), (int) (img.getHeight() * worldScalingFactor));
        setBackground(img);
        
        String gameOverText = String.format("YOU LOST!\nFinal Score: %d\nPress 'space' to go to the main menu", score);
        GreenfootImage textImage = new GreenfootImage(gameOverText, 40, Color.WHITE, null);
        
        img.drawImage(textImage, getWidth()/2 - textImage.getWidth()/2, getHeight()/2 - textImage.getHeight()/2);
    }
    
    public void startMusic()
    {
        music.playLoop();
    }
    public void stopMusic()
    {
        music.stop();
    }
   
    
    
    public void act()
    {
        if (Greenfoot.isKeyDown("space")) {
            stopMusic();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
