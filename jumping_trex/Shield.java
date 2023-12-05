import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shield here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield extends Powerup
{
    private static double scalingFactor = 0.25;
    
    public Shield()
    {
        // Duration of the powerup
        super(10000, 0);
        
        GreenfootImage img = getImage();
        img.scale((int) (img.getWidth() * scalingFactor), (int) (img.getHeight() * scalingFactor));
        setImage(img);
    }
    
    public void activate(TRex player)
    {
        super.start();
        addAura(player, Color.BLUE);
        player.setIsInvincible(true);
    }
    
    public void deactivate(TRex player)
    {
        player.setIsInvincible(false);
        removeAura(player);
    }
    
}
