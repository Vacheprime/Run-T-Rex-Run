import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ControlsMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlsMenu extends World
{
    private Button[] buttons;
    /**
     * Constructor for objects of class ControlsMenu.
     * 
     */
    public ControlsMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        buttons = new Button[4];
        Button jump = new Button(640, 300, "W or up arrow: jump", 30, 1);
        Button moveRight = new Button(640, 450, "D or right arrow: move right", 30, 2);
        Button moveLeft = new Button(640, 600, "A or left arrow: move left", 30, 3);
        Button goBack = new Button(200, 600, "Back to Menu", 40, 4);
        
        // Add all buttons
        buttons[0] = jump;
        buttons[1] = moveRight;
        buttons[2] = moveLeft;
        buttons[3] = goBack;
        
        displayMenu();
    }
    
    private void displayMenu()
    {
        for (Button btn: buttons) {
            addObject(btn, btn.getPosX(), btn.getPosY());
        }
    }
    
    public void act()
    {
        if (Greenfoot.mouseMoved(buttons[3])) {
            buttons[3].selectImage();
        } else if (Greenfoot.mouseMoved(null)) {
            buttons[3].unselectImage();
        }
        
        if (Greenfoot.mouseClicked(buttons[3])) {
            Greenfoot.setWorld(new MainMenu());
        }
    }
    
}
