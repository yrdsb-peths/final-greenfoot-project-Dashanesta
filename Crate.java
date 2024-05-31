import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Crate obstacles for the bike to dodge.
 * 
 * @author Shane DG
 * @version May 2024
 */
public class Crate extends Actor
{
    GreenfootImage crate = new GreenfootImage("images/crate.png");
    public void act()
    {
        crate.scale(64, 64);
        setImage(crate);
        
        // Remove crate when it hits the bottom of the screen
        MyWorld world = (MyWorld) getWorld();
        if (getY() >= world.getHeight() + 32)
        {
            world.removeObject(this);
        }
    }
}
