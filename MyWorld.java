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
    }
    
    public void act()
    {
        // Scroll background
        GreenfootImage background = new GreenfootImage(getBackground());
        getBackground().drawImage(background, 0, scrollSpeed);
        getBackground().drawImage(background, 0, scrollSpeed-getHeight());
    }
}
