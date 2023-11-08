import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends Actor
{
    public Background()
    {
        // Scale the image by a factor of 1.5
        GreenfootImage img = getImage();
        img.scale((int) (img.getWidth()*1.5), (int) (img.getHeight()*1.5));
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
