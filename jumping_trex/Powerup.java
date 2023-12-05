import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Powerup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Powerup extends Actor
{
    // Falling speed variables
    private int fallingSpeed = 2;
    private int fallAtFrame = 3;
    private int frameCounter = 0;
    
    private int ID;
    private long timeActivated;
    private int duration;
    
    // Aura variables
    private Aura powerupAura;
    
    public Powerup(int d, int id)
    {
        duration = d;
        ID = id;
    }
    
    public int getID()
    {
        return ID;
    }
    
    public void start()
    {
        timeActivated = System.currentTimeMillis();
    }
    
    public boolean isOver()
    {
        boolean over = false;
        if (System.currentTimeMillis() - timeActivated >= duration)
        {
            over = true;
        }
        return over;
    }
    
    public void scrollDown(int speed)
    {
        setLocation(getX(), getY() + speed);
    }
    
    public void addAura(TRex player, Color col, int width, int height, int borderSize)
    {
        powerupAura = new Aura(col, width, height, player, borderSize);
        player.getWorld().addObject(powerupAura, player.getX(), player.getY());
    }
    
    public void removeAura(TRex player)
    {
        if (powerupAura != null)
        {
            player.getWorld().removeObject(powerupAura);
            powerupAura = null;
        }
    }
    
    public void act()
    {
        if (frameCounter == fallAtFrame)
        {   
            setLocation(getX(), getY() - fallingSpeed);
            frameCounter = 0;
        } else
        {
            frameCounter++; 
        }
        setLocation(getX(), getY() + 1);
    }
}
