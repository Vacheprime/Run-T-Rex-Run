import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private int posX;
    private int posY;
    private boolean isSelected = false;
    private int ID;
    private int fontSize;
    private String text;
    private final String SELECTED_FILE = "button_selected.png";
    private final String UNSELECTED_FILE = "button_unselected.png";
    
    public Button(int x, int y, String txt, int txtSize, int id)
    {
        posX = x;
        posY = y;
        ID = id;
        text = txt;
        fontSize = txtSize;
        setText(text);
    }
    
    private void setText(String txt)
    {
        // Create a text image
        GreenfootImage txtImg = new GreenfootImage(txt, fontSize, Color.BLACK, null);
        GreenfootImage currImg = getImage();
        currImg.drawImage(txtImg, (currImg.getWidth() / 2) - txtImg.getWidth() / 2, (currImg.getHeight() / 2) - txtImg.getHeight() / 2);
        setImage(currImg);
    }
    
    public int getID()
    {
        return ID;
    }
    
    public int getPosX()
    {
        return posX;
    }
    
    public int getPosY()
    {
        return posY;
    }
    
    public boolean getIsSelected() 
    {
        return isSelected;
    }
    
    public void selectImage()
    {
        setImage(SELECTED_FILE);
        isSelected = true;
        setText(text);
    }
    
    public void unselectImage()
    {
        setImage(UNSELECTED_FILE);
        isSelected = false;
        setText(text);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
