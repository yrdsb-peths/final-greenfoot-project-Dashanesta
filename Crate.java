import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Crate obstacles for the bike to dodge.
 * 
 * @author Shane DG
 * @version June 2024
 */
public class Crate extends MovingClass
{
    GreenfootImage crate = new GreenfootImage("images/crate.png");
    GreenfootSound crateSound = new GreenfootSound("sounds/crate_break.mp3");
    public void act()
    {
        crate.scale(64, 64);
        setImage(crate);
        
        MyWorld world = (MyWorld) getWorld();
        // Bike hits crate
        if(isTouching(Bike.class))
        {
            crateSound.play();
            world.removeObject(this);
            world.removeLives(1);
        }
        // Remove crate when it hits the bottom of the screen
        else if (getY() >= world.getHeight() + 32)
        {
            world.removeObject(this);
        }
    }
}
