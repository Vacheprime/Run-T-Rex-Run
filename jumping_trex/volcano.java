import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class volcano here.
 * 
 * @author Shaheryar Anwar and Danat Ali Muradov 
 * @version 1
 */
public class volcano extends World
{
    private final int LAVA_LOWER_LIMIT = 350;
    /**
     * Constructor for objects of class volcano.
     * 
     */
    public volcano()
    {    
        // Create a new volcano world with 600x400 cells with a cell size of 1x1 pixels.
        super(750, 900, 1, false); 
        GreenfootImage img = getBackground();
        img.scale((int) (img.getWidth()*1.5), (int) (img.getHeight()*1.5));
        setBackground(img);
        
        // Set the lava to be at the bottom middle of the screen
        Actor lava = new Lava();
        addObject(lava, getWidth()/2, getHeight() + LAVA_LOWER_LIMIT);
        
    }
}
