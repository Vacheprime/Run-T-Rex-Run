import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{
    private Button[] buttons;
    /**
     * Constructor for objects of class Menus.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with height and width cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        // Create the buttons
        Button playGame = new Button(640, 300, "PLAY", 40, 1);
        Button controls = new Button(640, 450, "CONTROLS", 40, 2);
        Button sources = new Button(640, 600, "SOURCES", 40, 3);
        buttons = new Button[3];
        buttons[0] = playGame;
        buttons[1] = controls;
        buttons[2] = sources;
        // Display the menu
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
        for (Button btn: buttons) {
            // Check if mouse if hovering above buttons
            if (Greenfoot.mouseMoved(btn)) {
                btn.selectImage();
            } else if (Greenfoot.mouseMoved(null)) {
                btn.unselectImage();
            }
            // Check if player clicked on the button
            if (Greenfoot.mouseClicked(btn)) {
                switch (btn.getID()) {
                    // Button play
                    case 1: {
                        Greenfoot.setWorld(new Volcano(System.currentTimeMillis()));
                    }
                    break;
                    
                    // Button controls
                    case 2: {
                        Greenfoot.setWorld(new ControlsMenu());
                    }
                    break;
                    
                    // Button sources
                    case 3:  {
                        Greenfoot.setWorld(new SourcesMenu());
                    }
                    break;
                }
            }
        }
    }
}
