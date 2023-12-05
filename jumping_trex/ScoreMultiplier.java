import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class ScoreMultiplier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreMultiplier extends Powerup
{
    private static double scalingFactor = 0.10;
    
    public ScoreMultiplier()
    {
        super(20000, 1);
        GreenfootImage img = getImage();
        img.scale((int) (img.getWidth() * scalingFactor), (int) (img.getHeight() * scalingFactor));
        setImage(img);
    }
    
    public void activate(TRex player)
    {
        super.start();
        addAura(player, Color.YELLOW, player.getImage().getHeight() + 10, player.getImage().getHeight() + 10, 3);
        player.getWorld().getObjects(Score.class).get(0).setPointAtMS(1000 / 20);
    }
    
    public void deactivate(TRex player)
    {
        removeAura(player);
        player.getWorld().getObjects(Score.class).get(0).setPointAtMS(1000 / 10);
    }
}
