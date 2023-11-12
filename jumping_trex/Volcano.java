import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class volcano here.
 * 
 * @author Shaheryar Anwar and Danat Ali Muradov 
 * @version 1
 */
public class Volcano extends World
{
    // Scalling factor
    private double scalingFactor = 1.5;
    
    // Scrolling variables
    private boolean isScrolling = false;
    private final static int SCROLL_SPEED = 2;
    
    // Time step variables
    private long lastFrameTimeMS;
    private double timeStepDuration; 
    
    /**
     * Constructor for objects of class volcano.
     * 
     */
    public Volcano()
    {    
        // Create a new volcano world with 750x900 cells with a cell size of 1x1 pixels.
        super(750, 900, 1, false);
        // Remove the background
        setBackground((GreenfootImage) null);
        
        // Create two background actors that are goin to be scrolling
        addObject(new Background(), getWidth()/2, getHeight()/2);
        addObject(new Background(), getWidth()/2, getHeight()/2);
        
        
        // Set the lava to be at its lower limit
        Lava lava = new Lava(scalingFactor);
        addObject(lava, getWidth()/2, getHeight() + lava.getLavaLowerLimit());
        
        // Draw the actors in the right order
        setPaintOrder(Score.class, Lava.class, TRex.class);
        
        // Populate the volcano
        prepare();
    }
      
    public double getTimeStepDuration()
    {
        return timeStepDuration;
    }
    
    public double getScalingFactor()
    {
        return scalingFactor;
    }
    
    public void started()
    {
        // Set the initial frame time 
        lastFrameTimeMS = System.currentTimeMillis();
        
        // Create a new score actor
        Score score = new Score(lastFrameTimeMS - System.currentTimeMillis());
        addObject(score, 30, 20);
    }
    
    public void act()
    {
        // Update the time step duration
        updateTimeStep();
        
        // Scroll the screen
        scrollScreen();
    }
    
    private void updateTimeStep()
    {
        // Update the time step duration
        timeStepDuration = (System.currentTimeMillis() - lastFrameTimeMS) / 1000.0;
        
        // Set the last frame time
        lastFrameTimeMS = System.currentTimeMillis();
        if(Greenfoot.getRandomNumber(100) < 7)
        {
            addObject(new Platform(), Greenfoot.getRandomNumber(500) + 130, Greenfoot.getRandomNumber(400));
        }
    }
 
    private void scrollScreen()
    {
        // Get the Y position of the T-Rex (player)
        TRex player = getObjects(TRex.class).get(0);
        int currentYPosition = player.getY();

        if (currentYPosition >= getHeight() / 2 && isScrolling)
        {    
            isScrolling = false;
            // Stop scrolling the lava downwards
            Lava lava = getObjects(Lava.class).get(0);
            lava.stopScrollDown();
        } else if (isScrolling || currentYPosition < getHeight() / 4)
        {
            isScrolling = true;
            // Scroll all platforms
            List<Platform> platforms = getObjects(Platform.class);
            for (int i = 0; i < platforms.size(); i++)
            {
                platforms.get(i).scrollDown(SCROLL_SPEED);
            }
            
            // Scroll the lava downwards
            Lava lava = getObjects(Lava.class).get(0);
            lava.scrollDown(SCROLL_SPEED);
            
            // Scroll the player
            player.scrollDown(SCROLL_SPEED);
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Platform platform = new Platform();
        addObject(platform,213,773);
        platform.setLocation(226,763);
        Platform platform2 = new Platform();
        addObject(platform2,332,732);
        Platform platform3 = new Platform();
        addObject(platform3,467,689);
        Platform platform4 = new Platform();
        addObject(platform4,582,643);
        Platform platform5 = new Platform();
        addObject(platform5,471,577);
        Platform platform6 = new Platform();
        addObject(platform6,379,576);
        platform6.setLocation(391,576);
        Platform platform7 = new Platform();
        addObject(platform7,305,536);
        Platform platform8 = new Platform();
        addObject(platform8,191,489);
        Platform platform9 = new Platform();
        addObject(platform9,309,444);
        Platform platform10 = new Platform();
        addObject(platform10,420,395);
        Platform platform11 = new Platform();
        addObject(platform11,535,331);
        Platform platform12 = new Platform();
        addObject(platform12,423,273);
        Platform platform13 = new Platform();
        addObject(platform13,308,236);
        Platform platform14 = new Platform();
        addObject(platform14,205,196);
        Platform platform15 = new Platform();
        addObject(platform15,99,148);
        Platform platform16 = new Platform();
        addObject(platform16,222,100);
        platform16.setLocation(227,101);
        platform15.setLocation(129,146);
        platform16.setLocation(188,104);
        Platform platform17 = new Platform();
        addObject(platform17,293,58);
        platform2.setLocation(378,715);
        platform.setLocation(278,758);
        platform12.setLocation(432,290);
        platform13.setLocation(330,246);
        platform14.setLocation(227,214);
        platform15.setLocation(168,192);
        platform16.setLocation(106,133);
        platform12.setLocation(434,285);
        platform13.setLocation(381,261);
        platform14.setLocation(284,224);
        platform15.setLocation(204,200);
        platform16.setLocation(121,165);
        platform17.setLocation(165,124);
        Platform platform18 = new Platform();
        addObject(platform18,248,80);
        platform18.setLocation(265,81);
        platform3.setLocation(497,681);
        platform2.setLocation(420,714);
        platform.setLocation(338,756);
        platform18.setLocation(288,72);
        platform13.setLocation(354,254);
        platform13.setLocation(360,246);
        platform14.setLocation(286,216);
        platform15.setLocation(192,186);
        platform16.setLocation(123,152);
        platform17.setLocation(180,116);
        removeObject(platform17);
        platform18.setLocation(288,70);
        removeObject(platform18);
        platform11.setLocation(528,365);
        platform12.setLocation(435,301);
        platform13.setLocation(323,265);
        platform14.setLocation(248,230);
        platform15.setLocation(132,187);
        platform16.setLocation(212,132);
        platform15.setLocation(145,213);
        platform16.setLocation(101,147);
        platform11.setLocation(508,372);
        platform12.setLocation(470,329);
        platform13.setLocation(374,301);
        platform14.setLocation(267,275);
        platform15.setLocation(194,252);
        platform16.setLocation(104,196);
        platform11.setLocation(508,369);
        platform12.setLocation(456,308);
        platform13.setLocation(389,274);
        platform13.setLocation(387,276);
        platform14.setLocation(274,234);
        platform15.setLocation(205,192);
        platform15.setLocation(201,188);
        platform16.setLocation(107,134);
        addObject(platform17,189,93);
        platform17.setLocation(188,92);
        addObject(platform18,267,54);
        platform3.setLocation(500,685);
        platform2.setLocation(429,716);
        platform.setLocation(360,752);
        platform17.setLocation(186,109);
        platform18.setLocation(243,61);
        Platform platform19 = new Platform();
        addObject(platform19,348,25);
        platform4.setLocation(574,613);
        platform3.setLocation(512,665);
        platform2.setLocation(435,707);
        platform.setLocation(343,757);
        platform.setLocation(328,756);
        platform.setLocation(328,756);
        removeObject(platform);
        platform4.setLocation(568,620);
        platform3.setLocation(489,668);
        platform2.setLocation(388,720);
        platform12.setLocation(454,310);
        platform13.setLocation(364,276);
        platform14.setLocation(276,230);
        platform15.setLocation(162,196);
        platform16.setLocation(113,144);
        platform11.setLocation(535,364);
        platform10.setLocation(465,401);
        platform9.setLocation(367,439);
        platform8.setLocation(244,468);
        platform9.setLocation(301,432);
        platform10.setLocation(407,401);
        platform11.setLocation(509,367);
        platform12.setLocation(428,299);
        platform13.setLocation(345,280);
        platform14.setLocation(240,226);
        platform15.setLocation(146,197);
        platform16.setLocation(102,146);
        removeObject(platform16);
        platform17.setLocation(209,142);
        platform18.setLocation(274,96);
        platform19.setLocation(345,48);
        platform12.setLocation(437,332);
        platform13.setLocation(348,295);
        platform14.setLocation(261,263);
        platform15.setLocation(164,234);
        platform17.setLocation(198,177);
        platform18.setLocation(279,100);
        platform17.setLocation(89,181);
        platform11.setLocation(511,366);
        platform10.setLocation(426,405);
        platform11.setLocation(512,380);
        platform11.setLocation(516,392);
        platform12.setLocation(450,340);
        platform13.setLocation(354,309);
        platform14.setLocation(270,281);
        platform14.setLocation(244,278);
        platform13.setLocation(314,306);
        platform15.setLocation(172,254);
        platform17.setLocation(106,189);
        platform12.setLocation(440,336);
        platform11.setLocation(529,388);
        platform13.setLocation(350,295);
        platform14.setLocation(276,263);
        platform15.setLocation(200,224);
        platform17.setLocation(124,188);
        platform18.setLocation(193,149);
        platform19.setLocation(288,83);
        platform4.setLocation(551,635);
        platform3.setLocation(484,679);
        platform2.setLocation(404,715);
        platform12.setLocation(432,339);
        platform12.setLocation(440,325);
        platform13.setLocation(368,297);
        platform13.setLocation(372,289);
        platform14.setLocation(280,258);
        platform15.setLocation(193,216);
        platform17.setLocation(126,176);
        platform14.setLocation(256,260);
        platform18.setLocation(193,134);
        platform19.setLocation(250,96);
        addObject(platform,316,49);
        platform.setLocation(330,51);
        platform2.setLocation(376,744);
        platform2.setLocation(399,715);
        platform.setLocation(349,37);
        platform19.setLocation(295,74);
        platform18.setLocation(200,113);
        platform17.setLocation(111,164);
        platform18.setLocation(183,124);
        platform18.setLocation(219,120);
        platform19.setLocation(290,80);
        platform.setLocation(354,30);
        platform3.setLocation(516,678);
        platform2.setLocation(421,724);
        platform2.setLocation(413,714);
        removeObject(platform2);
        platform.setLocation(393,36);
        platform.setLocation(393,36);
        platform3.setLocation(475,740);
        platform4.setLocation(556,707);
        addObject(platform2,635,636);
        platform2.setLocation(648,644);
        addObject(platform16,568,607);
        platform16.setLocation(568,607);
        Platform platform20 = new Platform();
        addObject(platform20,568,607);
        platform16.setLocation(564,603);
        platform16.setLocation(548,612);
        removeObject(platform20);
        platform16.setLocation(540,610);
        platform2.setLocation(638,654);
        platform2.setLocation(633,656);
        platform2.setLocation(630,660);
        platform4.setLocation(572,701);
        platform3.setLocation(489,760);
        addObject(platform20,582,387);
        platform20.setLocation(582,400);
        platform20.setLocation(576,391);
        platform20.setLocation(578,399);
        platform20.setLocation(580,400);
        platform20.setLocation(579,395);
        platform18.setLocation(229,138);
        platform19.setLocation(312,93);
        platform.setLocation(361,60);
        Platform platform21 = new Platform();
        addObject(platform21,420,53);
        platform21.setLocation(438,54);
        platform21.setLocation(438,53);
        platform21.setLocation(440,56);
        platform21.setLocation(440,55);
        TRex tRex = new TRex();
        addObject(tRex,261,686);
        tRex.setLocation(469,708);
        platform11.setLocation(644,389);
        platform5.setLocation(453,580);
        platform5.setLocation(456,581);
        platform5.setLocation(454,582);
        platform5.setLocation(456,580);
        platform5.setLocation(453,580);
        platform.setLocation(379,57);
        platform.setLocation(381,59);
        platform.setLocation(381,56);
        platform.setLocation(380,56);
        platform20.setLocation(525,360);
        platform11.setLocation(596,364);
        platform11.setLocation(595,362);
        platform11.setLocation(596,366);
        platform9.setLocation(352,415);
        platform13.setLocation(341,284);
        platform.setLocation(392,51);
        platform21.setLocation(440,44);
        platform21.setLocation(447,46);
        platform21.setLocation(452,48);
        platform.setLocation(400,48);
        platform21.setLocation(437,48);
        platform.setLocation(404,46);
        platform18.setLocation(205,127);
        platform19.setLocation(276,96);
        platform.setLocation(371,53);
        platform21.setLocation(411,47);
        platform.setLocation(410,54);
        platform.setLocation(412,52);
        platform8.setLocation(225,472);
        platform9.setLocation(328,435);
        platform20.setLocation(516,380);
        platform12.setLocation(448,313);
        platform5.setLocation(448,582);
        platform6.setLocation(363,553);
        platform5.setLocation(455,585);
        platform16.setLocation(544,595);
        platform2.setLocation(612,654);
        platform2.setLocation(619,652);
        platform16.setLocation(574,604);
        platform5.setLocation(492,576);
        platform6.setLocation(409,550);
        platform.setLocation(351,71);
        platform.setLocation(427,46);
    }
}
