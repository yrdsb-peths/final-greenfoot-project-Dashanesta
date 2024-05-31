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
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 900, 1);
        setBackground(new GreenfootImage("images/roadBackground.png"));
        // Create the bike
        Bike bike = new Bike();
        addObject(bike, 300, 750);
        
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
    }
    /**
     * Spawn a crate at a random x location
     */
    public void spawnCrate()
    {
        Crate crate = new Crate();
        int location = Greenfoot.getRandomNumber(5);
        int[] lanes = {50,150,250,350,450,550};
        addObject(crate, lanes[location], 0);
    }
}
