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
    public LifePowerup()
    {
        setImage(heart);
    }
    
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        // Add life if bike touches
        if(isTouching(Bike.class))
        {
            world.powerupSound.play();
            world.removeObject(this);
            world.addLives(1);
        }
    }
}
