import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SourcesMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SourcesMenu extends World
{
    private Button[] buttons;
    private TextWindow[] textWindows;
    /**
     * Constructor for objects of class SourcesMenu.
     * 
     */
    public SourcesMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        buttons = new Button[1];
        textWindows = new TextWindow[1];
        
        Button goBack = new Button(200, 600, "Back to Menu", 40, 1);
        TextWindow sourcesList = new TextWindow(500, 500, getWidth() / 2, getHeight() / 2);
        // Add all sources
        // Audio sources
        sourcesList.addText("Audio", 40, true);
        sourcesList.addText("Game over music: \"What is Left\" by SeKa (opengameart.org)", 20, false);
        sourcesList.addText("Gameplay music: \"The Perilous Journey: Imaginary Music From\n A Fantasy Video Game\" by Peter Eastman (opengameart.org)", 20, false);
        sourcesList.addText("Powerup sound effect: \"8-Bit Powerup\", unknown (pixabay.com)", 20, false);
        // Image sources
        sourcesList.addText("Images", 40, true);
        sourcesList.addText("T-Rex: mostafaelturkey97 (pixabay.com)", 20, false);
        sourcesList.addText("Menu background: Yuliya Pauliukevich (vecteezy.com)", 20, false);
        sourcesList.addText("Platform: Tio Aimar (opengameart.org)", 20, false);
        sourcesList.addText("T-Rex skull (splashscreen): Wb101n (cleanpng.com)", 20, false);
        
        
        buttons[0] = goBack;
        textWindows[0] = sourcesList;
        displayMenu();
    }
    
    private void displayMenu()
    {
        // Display all buttons
        for (Button btn: buttons) {
            addObject(btn, btn.getPosX(), btn.getPosY());
        }
        // Display all text windows
        for (TextWindow txtWin: textWindows) {
            addObject(txtWin, txtWin.getPosX(), txtWin.getPosY());
        }
    }
    
    public void act()
    {
        if (Greenfoot.mouseMoved(buttons[0])) {
            buttons[0].selectImage();
        } else if (Greenfoot.mouseMoved(null)) {
            buttons[0].unselectImage();
        }
        
        if (Greenfoot.mouseClicked(buttons[0])) {
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
