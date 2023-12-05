import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    private int score;
    private int timeToPoint;
    private long lastTimeStep;
    private  double pointAtMS = 1000 / 10; // Milliseconds / points
    private GreenfootImage img;
    private Volcano volcanoWorld;
    
    public Score(long lastTimeStep)
    {
        this.score = 0;
        this.timeToPoint = 0;
        this.lastTimeStep = lastTimeStep;
        this.img = null;
        this.volcanoWorld = null;
    }
    
    public void setPointAtMS(double pAtMs)
    {
        pointAtMS = pAtMs;
    }
    
    public int getScore()
    {
        return this.score;
    }
    
    public void addedToWorld(World world)
    {
        // Get the width when the actor is added to the world
        int width = world.getWidth();
        String currentScore = String.format("Score: %d", score);
        
        // Create a new image with the current score
        GreenfootImage txtImg = new GreenfootImage(width, 20);
        Font font = new Font(20);
        txtImg.setFont(font);
        txtImg.setColor(Color.WHITE);
        txtImg.drawString(currentScore, 0, 20);
        
        // Set the Actor image to the created image
        setImage(txtImg);
        img = txtImg;
        volcanoWorld = (Volcano) world;
        // Set the location of the actor to be at the top left corner of the screen.
        setLocation(getX() + width/2, getY());
    }
    
    /**
     * Act - do whatever the Score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Get current time Step and update score
        timeToPoint += (int) (volcanoWorld.getTimeStepDuration() * 1000.0);
        
        // if pointsPerSecond seconds have passed, increment the score
        if (timeToPoint >= pointAtMS)
        {
            score += (int) timeToPoint / pointAtMS;
            timeToPoint = 0; // reset the time until the next point
            
            // Update the image
            String currentScore = String.format("Score: %d", score);
            img.clear();
            img.drawString(currentScore, 0, 20);
        }
        
    }
}
