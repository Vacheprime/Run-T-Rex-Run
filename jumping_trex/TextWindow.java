import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextWinfow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextWindow extends Actor
{
    private int width;
    private int height;
    private int posX;
    private int posY;
    private final int OFFSET_X = 10;
    private final int OFFSET_Y = 10;
    private int nextTextPosition = OFFSET_X;
    /**
     * Act - do whatever the TextWinfow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TextWindow(int w, int h, int x, int y)
    {
        width = w;
        height = h;
        posX = x;
        posY = y;
        
        GreenfootImage img = new GreenfootImage(width, height);
        Color backgroundColor = new Color(241, 117, 25);
        img.setColor(backgroundColor);
        img.fill();
        setImage(img);
    }
    
    public int getPosX()
    {
        return posX;
    }
    
    public int getPosY()
    {
        return posY;
    }
    
    public void addText(String text, int size, boolean centerText)
    {
        String[] textsToDraw = text.split("\\n"); 
        GreenfootImage currentImg = getImage();
        for (String txt: textsToDraw) {
            GreenfootImage txtImg = new GreenfootImage(txt, size, Color.BLACK, null);
            if (centerText) {
                currentImg.drawImage(txtImg, currentImg.getWidth() / 2 - txtImg.getWidth() / 2, nextTextPosition);
            } else {
                currentImg.drawImage(txtImg, OFFSET_X, nextTextPosition);
            }
            nextTextPosition += txtImg.getHeight();
        }
        setImage(currentImg);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
