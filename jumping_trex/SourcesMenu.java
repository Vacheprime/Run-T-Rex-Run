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
        sourcesList.addText("Audio", 30, true);
        sourcesList.addText("Game over music: \"What is Left\" by SeKa (opengameart.org)", 15, false);
        sourcesList.addText("Gameplay music: \"The Perilous Journey: Imaginary Music From A Fantasy Video Game\"", 15, false);
        sourcesList.addText("by Peter Eastman (opengameart.org)", 15, false);
        
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
    
}
