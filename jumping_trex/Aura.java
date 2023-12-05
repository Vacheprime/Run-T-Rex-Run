import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Aura here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Aura extends Actor
{
    private Actor actorToFollow;
    
    public Aura(Color col, int width, int height, Actor toFollow)
    {
        actorToFollow = toFollow;
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(col);
        img.drawOval(0, 0, width, height);
        setImage(img);
    }
    
    /**
     * Act - do whatever the Aura wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(actorToFollow.getX(), actorToFollow.getY());
    }
}
