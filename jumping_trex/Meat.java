import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Meat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Meat extends Powerup
{
    private static double scalingFactor = 1.5;
    
    public Meat()
    {
        super(10000, 2);
        GreenfootImage img = getImage();
        img.scale((int) (img.getWidth() * scalingFactor), (int) (img.getHeight() * scalingFactor));
        setImage(img);
    }

    public void activate(TRex player)
    {
        super.start();
        addAura(player, Color.RED, player.getImage().getHeight() + 20, player.getImage().getHeight() + 20, 3);
        player.setJumpVelocity(800);
    }
    
    public void deactivate(TRex player)
    {
        removeAura(player);
        player.setJumpVelocity(600);
    }
}
