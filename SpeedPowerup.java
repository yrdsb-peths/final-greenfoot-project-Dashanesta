import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerup which gives increased speed and decreased crates for 15 seconds
 * 
 * @author Shane DG
 * @version June 2024
 */
public class SpeedPowerup extends MovingClass
{
    int loops = 0;
    GreenfootImage speed = new GreenfootImage("images/speed.png");
    public SpeedPowerup()
    {
        setImage(speed);
    }
    
    public void act()
    {        
        MyWorld world = (MyWorld) getWorld();
        // Activate speed if bike touches
        if(isTouching(Bike.class))
        {
            world.powerupSound.play();
            world.removeObject(this);
            world.speedBoost();
        }
    }
}
