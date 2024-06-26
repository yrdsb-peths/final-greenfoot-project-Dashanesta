import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Main World
 * 
 * @author Shane DG
 * @version June 2024
 */
public class MyWorld extends World
{
    private int gameSpeed = 5;
    private int loops = 0;
    private double speedCurve = 0;
    private boolean speedBoost = false;
    private int speedTimer = 900;
    private int crateCooldown = 0;
    private int powerupCooldown = 0;
    public static double distance = 0;
    private int[] lanes = {50,150,250,350,450,550};
    public int lives = 0;
    public GreenfootSound powerupSound = new GreenfootSound("sounds/powerup.mp3");
    Label distanceLabel;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 900, 1, false);
        setBackground(new GreenfootImage("images/roadBackground.png"));
        // Keep GUI above moving objects
        setPaintOrder(GUI.class, MovingClass.class);
        // Create the bike
        Bike bike = new Bike();
        addObject(bike, 300, 750);
        // Add hearts for lives
        addLives(3);
        // Add distance label
        distanceLabel = new Label("0.00km", 40);
        addObject(distanceLabel, 100, 20);
    }
    
    public void act()
    {
        // loop counter
        loops++;
        // Scroll background, code partially sourced from user danpost (https://www.greenfoot.org/topics/56895/0)
        GreenfootImage background = new GreenfootImage(getBackground());
        getBackground().drawImage(background, 0, gameSpeed);
        getBackground().drawImage(background, 0, gameSpeed-getHeight());
        for (Object obj : getObjects(MovingClass.class))
        {
            Actor actor = (Actor)obj;
            actor.setLocation(actor.getX(), actor.getY() + gameSpeed);
        }
        // exponential speed increase
        speedCurve = Math.pow(loops, 2) % Math.pow(10, 6);
        // If speed boost is active, set crateCooldown to 100 loops, otherwise use formula based on speed
        crateCooldown = speedBoost ? 100 : (int) (250.0 / (gameSpeed));
        powerupCooldown = (int) (10000.0 / (gameSpeed));
        // Check time passed to spawn a new crate
        if(loops % crateCooldown == 0)
        {
            spawnCrate();
        }
        // Only spawn powerups when there is not an active speed boost
        if(!speedBoost && loops % powerupCooldown == 0)
        {
            spawnPowerup();
        }
        // Use speed boost to increase game speed exponentially
        if(speedCurve == 0)
        {
            gameSpeed++;
        }
        // Count 900 act cycles before disabling speed boost
        if(speedBoost)
        {
            speedTimer--;
            if(speedTimer == 0)
            {
                gameSpeed /= 3;
                speedBoost = false;
            }
        }
        // Calculate distance in km and draw to distance label
        distance += (loops/50 * gameSpeed) / 1000000.0;
        distanceLabel.setValue(Math.floor(distance*100)/100 + "km");
        // Game over
        if(lives <= 0)
        {
            DeathScreen deathScreen = new DeathScreen();
            Greenfoot.setWorld(deathScreen);
        }
    }
    
    /**
     * Spawn a crate at a random x location
     */
    public void spawnCrate()
    {
        Crate crate = new Crate();
        int location = Greenfoot.getRandomNumber(6);
        addObject(crate, lanes[location], -32);
    }
    
    /**
     * Spawn a random powerup
     */
    public void spawnPowerup()
    {
        int index = Greenfoot.getRandomNumber(2);
        int location = Greenfoot.getRandomNumber(6);
        switch(index)
        {
            case 0:
                LifePowerup life = new LifePowerup();
                addObject(life, lanes[location], -32);
                break;
            case 1:
                SpeedPowerup speed = new SpeedPowerup();
                addObject(speed, lanes[location], -32);
                break;
        }
    }
    
    int heartX = 550;
    int heartY = 20;
    ArrayList<Heart> hearts = new ArrayList<Heart>();
    /**
     * Add lives
     */
    public void addLives(int iterations)
    {
        for(int i = 0; i < iterations; i++)
        {
            lives++;
            hearts.add(new Heart());
            addObject(hearts.get(hearts.size()-1), heartX, heartY);
            if(hearts.size() % 5 == 0)
            {
                heartY = 20 + (50*(hearts.size()/5));
                heartX = 550;
            } else
            {   
                heartY = 20 + (50*(hearts.size()/5));
                heartX = 550 - (50*(hearts.size()%5));
            }
        }
    }
    
    /**
     * Remove lives
     */
    public void removeLives(int iterations)
    {
        for (int i = 0; i < iterations; i++)
        {
            if(lives > 0)
            {
                lives--;
                removeObject(hearts.get(hearts.size()-1));
                hearts.remove(hearts.size()-1);
                heartX += 50;
                if(hearts.size() % 5 == 0)
                {
                    heartY = 20 + (50*(hearts.size()/5));
                    heartX = 550;
                } else
                {
                    heartY = 20 + (50*(hearts.size()/5));
                    heartX = 550 - (50*(hearts.size()%5));
                }
            }
        }
    }
    
    /**
     * Speed boost multiplies speed by 3 and reduces crate spawning for approx 15 seconds
     */
    public void speedBoost()
    {
        gameSpeed *= 3;
        speedBoost = true;
    }
}
