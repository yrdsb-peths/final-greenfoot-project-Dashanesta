import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main Player
 * 
 * @author Shane DG
 * @version May 2024
 */
public class Bike extends Actor
{
    GreenfootImage bicycle = new GreenfootImage("images/bicycle/bike1.png");
    public Bike()
    {
        bicycle.scale(60, 96);
        setImage(bicycle);
    }
    
    public void act()
    {
        // Use A and D keys to move left and right
        if(Greenfoot.isKeyDown("a"))
        {
            move(-4);
        }
        else if (Greenfoot.isKeyDown("d"))
        {
            move(4);
        }
        
        // temp game over
        if(isTouching(Crate.class))
        {
            Greenfoot.stop();
        }
    }
}
