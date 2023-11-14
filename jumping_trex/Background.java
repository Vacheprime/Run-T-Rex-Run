import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends Actor
{
    private static double worldScalingFactor = Volcano.getScalingFactor();
    public Background()
    {
        // Scale the image by a factor of 1.5
        GreenfootImage img = getImage();
        img.scale((int) (img.getWidth() * worldScalingFactor), (int) (img.getHeight() * worldScalingFactor));
        setImage(img);
    }
    
    /**
     * Act - do whatever the Background wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
}
