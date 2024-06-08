import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main Menu
 * 
 * @author Shane DG
 * @version June 2024
 */
public class MainMenu extends World
{
    Label title;
    Label instructions;
    Label startText;
    GreenfootImage bg = new GreenfootImage(900, 600);
    GreenfootImage bike = new GreenfootImage("images/bicycle/bike_side.png");
    GreenfootImage road = new GreenfootImage("images/roadBackground.png");
    GreenfootImage heart = new GreenfootImage("images/heart.png");
    GreenfootImage speed = new GreenfootImage("images/speed.png");
    public MainMenu()
    {    
        super(900, 600, 1);
        // Add colored background
        bg.setColor(new Color(148,148,148));
        bg.fill();
        setBackground(bg);
        // Add title
        title = new Label("Biker Mania", 140);
        title.setFillColor(new Color(176,207,255));
        addObject(title, getWidth()/2, 80);
        // Add instructions
        instructions = new Label("Use A and D\nto move\nyour bike", 50);
        addObject(instructions, 150, 375);
        // Add start text
        startText = new Label("Press [ENTER]\nto start", 50);
        addObject(startText, 750, 350);
        // Add bikes
        bike.scale(180,180);
        getBackground().drawImage(bike, 700, 140);
        bike.mirrorHorizontally();
        getBackground().drawImage(bike, 20, 140);
        // Add road
        road.scale(300,600);
        getBackground().drawImage(road, 300, 0);        
        // Add life powerup text
        getBackground().drawImage(heart, 60, 500);
        Label label = new Label("gives 1\nextra life", 35);
        addObject(label, 150, 525);
        // Add speed powerup text
        getBackground().drawImage(speed, 630, 460);
        Label label2 = new Label("gives increased\nspeed and\ndecreased crates\nfor 15 seconds", 35);
        addObject(label2, 775, 525);
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
