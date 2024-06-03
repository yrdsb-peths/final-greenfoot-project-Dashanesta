import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerup which gives a life
 * 
 * @author Shane DG
 * @version June 2024
 */
public class LifePowerup extends Actor
{
    GreenfootImage heart = new GreenfootImage("images/heart.png");
    public void act()
    {
        setImage(heart);
        
        MyWorld world = (MyWorld) getWorld();
        // Add life if bike touches
        if(isTouching(Bike.class))
        {
            world.removeObject(this);
            world.addLives(1);
        }
    }
}
