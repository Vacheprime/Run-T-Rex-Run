import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TRex here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TRex extends Actor
{
    public TRex() {
        GreenfootImage img = getImage();
        img.scale(img.getWidth()/4, img.getHeight()/4);
        setImage(img);
    }
    
    /**
     * Act - do whatever the TRex wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
}
