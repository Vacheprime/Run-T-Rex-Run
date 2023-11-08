import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lava here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lava extends Actor
{
    // Rising speed variables
    private int risingSpeed = 1;
    private final byte RISE_AT_FRAME = 4;
    private byte frameCounter = 0;
    private int lavaLevel = -1;
    public Lava() {
        // Scale the image to be 1.5x bigger
        GreenfootImage img = getImage();
        img.scale((int) (img.getWidth()*1.5), (int) (img.getHeight()*1.5));
        setImage(img);
    }
    
    private void setLavaLevel(int lavaLevel)
    {   
        
        this.lavaLevel = lavaLevel - getImage().getHeight()/2;
    }
    
    public int getLavaLevel()
    {
        return this.lavaLevel;    
    }
    
    /**
     * Act - do whatever the Lava wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (frameCounter == RISE_AT_FRAME)
        {
            setLocation(getX(), getY() - risingSpeed);
            frameCounter = 0;
        } else {
            frameCounter++;        
        }
        setLavaLevel(getY());
    }
}
