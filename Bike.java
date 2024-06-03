import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main Player
 * 
 * @author Shane DG
 * @version June 2024
 */
public class Bike extends Actor
{
    private int loops = 0;
    private int imageIndex = 1;
    GreenfootImage[] bicycle = new GreenfootImage[8];
    public Bike()
    {
        // Set images for animation
        for(int i = 0; i < bicycle.length; i++)
        {
            bicycle[i] = new GreenfootImage("images/bicycle/bike" + i + ".png");
            bicycle[i].scale(60, 96);
        }
        
        setImage(bicycle[0]);
    }
    
    public void act()
    {   
        loops++;
        // Cycle through images
        if(loops % 12 == 0)
        {
            setImage(bicycle[imageIndex]);
            imageIndex = (imageIndex + 1) % bicycle.length;
        }
        // Use A and D keys to move left and right
        if(Greenfoot.isKeyDown("a") && !(getX() <= 0))
        {
            move(-4);
        }
        else if (Greenfoot.isKeyDown("d") && !(getX() >= 600))
        {
            move(4);
        }
    }
}
