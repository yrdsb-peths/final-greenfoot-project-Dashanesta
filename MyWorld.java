import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main World
 * 
 * @author Shane DG
 * @version May 2024
 */
public class MyWorld extends World
{
    private int gameSpeed = 5;
    private int loops = 0;
    private double speedCurve = 0;
    private int crateCooldown = 0;
    private double distance = 0;
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
        
        spawnCrate();
    }
    
    public void act()
    {
        // loop counter
        loops++;
        // Scroll background, code partially sourced from user danpost (https://www.greenfoot.org/topics/56895/0)
        GreenfootImage background = new GreenfootImage(getBackground());
        getBackground().drawImage(background, 0, gameSpeed);
        getBackground().drawImage(background, 0, gameSpeed-getHeight());
        for (Object obj : getObjects(Crate.class))
        {
            Actor actor = (Actor)obj;
            actor.setLocation(actor.getX(), actor.getY() + gameSpeed);
        }
        // exponential speed increase
        speedCurve = Math.pow(loops, 2) % Math.pow(10, 6);
        crateCooldown = (int) (250.0 / (gameSpeed));
        // Check time passed to spawn a new crate
        if(loops % crateCooldown == 0)
        {
            spawnCrate();
        }
        
        if(speedCurve == 0)
        {
            gameSpeed++;
        }
        
        // Calculate distance in km and draw to distance label
        distance = (loops/50 * gameSpeed) / 1000.0;
        distanceLabel.setValue(Math.floor(distance*100)/100 + "km");
    }
    /**
     * Spawn a crate at a random x location
     */
    public void spawnCrate()
    {
        Crate crate = new Crate();
        int location = Greenfoot.getRandomNumber(6);
        int[] lanes = {50,150,250,350,450,550};
        addObject(crate, lanes[location], -32);
    }
}
