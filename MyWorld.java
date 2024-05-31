import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main World
 * 
 * @author Shane DG
 * @version May 2024
 */
public class MyWorld extends World
{
    public int scrollSpeed = 5;
    private SimpleTimer cooldown = new SimpleTimer();
    private SimpleTimer timeElapsed = new SimpleTimer();
    double distance = 0;
    Label distanceLabel;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 900, 1, false);
        setBackground(new GreenfootImage("images/roadBackground.png"));
        // Keep labels above other objects
        setPaintOrder(Label.class);
        // Create the bike
        Bike bike = new Bike();
        addObject(bike, 300, 750);
        // Add distance label
        distanceLabel = new Label ("0.00km", 40);
        addObject(distanceLabel, 100, 50);
        
        timeElapsed.mark();
        spawnCrate();
    }
    
    public void act()
    {
        // Scroll background, code partially sourced from user danpost (https://www.greenfoot.org/topics/56895/0)
        GreenfootImage background = new GreenfootImage(getBackground());
        getBackground().drawImage(background, 0, scrollSpeed);
        getBackground().drawImage(background, 0, scrollSpeed-getHeight());
        for (Object obj : getObjects(Crate.class))
        {
            Actor actor = (Actor)obj;
            actor.setLocation(actor.getX(), actor.getY() + scrollSpeed);
        }
        
        // Check time passed to spawn a new crate
        // TEMPORARY 1 SECOND STATIC COOLDOWN
        if(cooldown.millisElapsed() > 1000)
        {
            spawnCrate();
        }
        
        // Calculate distance in km and draw to distance label
        distance = (timeElapsed.millisElapsed()/1000 * scrollSpeed) / 1000.0;
        distanceLabel.setValue(Math.floor(distance*100)/100 + "km");
    }
    /**
     * Spawn a crate at a random x location
     */
    public void spawnCrate()
    {
        cooldown.mark();
        Crate crate = new Crate();
        int location = Greenfoot.getRandomNumber(5);
        int[] lanes = {50,150,250,350,450,550};
        addObject(crate, lanes[location], -32);
    }
}
