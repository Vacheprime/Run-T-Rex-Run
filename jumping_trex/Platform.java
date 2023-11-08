import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    public Platform() {
        // Scale the platform to 1/2 of its original size
        GreenfootImage img = getImage();
        img.scale(img.getWidth()/2, img.getHeight()/2);
        setImage(img);
    }
    
    /**
     * Act - do whatever the Platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        while(isTouching(Platform.class))
        {
         setLocation(Greenfoot.getRandomNumber(750),Greenfoot.getRandomNumber(900));
        }
    }
}
