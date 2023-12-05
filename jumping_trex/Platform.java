import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    private static double scalingFactor = 0.5;
    public Platform() {
        // Scale the platform to 1/2 of its original size
        GreenfootImage img = getImage();
        img.scale((int) (img.getWidth() * scalingFactor), (int) (img.getHeight() * scalingFactor));
        setImage(img);
    }
    
    public void act()
    {
        if (getY() - getImage().getHeight() > getWorld().getHeight())
        {
            getWorld().removeObject(this);
        }
    }

    public void scrollDown(int scrollingSpeed)
    {
        setLocation(getX(), getY() + scrollingSpeed);
    }
}
