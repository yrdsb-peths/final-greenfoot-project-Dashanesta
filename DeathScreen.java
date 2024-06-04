import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world that appears after dying
 * 
 * @author Shane DG
 * @version June 2024
 */
public class DeathScreen extends World
{
    Label deathText;
    Label distanceText;
    Label newHighScoreText;
    Label highScoreText;
    Label instructions;
    GreenfootImage bg = new GreenfootImage(900, 600);
    public static double highScore = 0;
    public DeathScreen()
    {    
        super(900, 600, 1);
        // Add colored background
        bg.setColor(Color.GRAY);
        bg.fill();
        setBackground(bg);
        // Add death text
        deathText = new Label("You Died!", 140);
        deathText.setFillColor(Color.RED);
        addObject(deathText, getWidth()/2, 120);
        // Add distance text
        distanceText = new Label("You Traveled " + Math.floor(MyWorld.distance*100)/100 + "km", 70);
        addObject(distanceText, getWidth()/2, 300);
        // Add instructions
        instructions = new Label("Press [ENTER] to\nplay again!", 70);
        instructions.setFillColor(new Color(176,207,255));
        addObject(instructions, getWidth()/2, 425);
        // Add new high score text
        newHighScoreText = new Label("New High Score!", 70);
        newHighScoreText.setFillColor(new Color(176,207,255));
        // Set high score
        if(MyWorld.distance > highScore)
        {
            highScore = MyWorld.distance;
            addObject(newHighScoreText, getWidth()/2, 220);
        }
        // Add high score text
        highScoreText = new Label("Your longest distance traveled is " + Math.floor(highScore*100)/100 + "km", 50);
        addObject(highScoreText, getWidth()/2, 550);
    }
    
    public void act()
    {
        // Detect enter key
        if(Greenfoot.isKeyDown("enter"))
        {
            MyWorld.distance = 0;
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
}
