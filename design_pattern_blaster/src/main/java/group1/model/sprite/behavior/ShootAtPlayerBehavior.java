package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import group1.physics.Vector;
import group1.model.sprite.NullSprite;

public class ShootAtPlayerBehavior implements Behavior
{
	private Sprite blueprint;
	int offsetX, offsetY, projectileSpeed;
	
	public ShootAtPlayerBehavior(int offsetX, int offsetY, Sprite blueprint, int projectileSpeed)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.blueprint = blueprint;
		this.projectileSpeed = projectileSpeed;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		ArrayList<Sprite> playerSprites = App.model.getPlayerSprites();
        Sprite nearestPlayerSprite = new NullSprite();
        double minDistance = Double.MAX_VALUE;
        double x = sprite.getX() + offsetX;
        double y = sprite.getY() + offsetY;
        for (Sprite s : playerSprites)
        {
            //System.out.println(s.getX());
            if (Math.abs(s.getX() - x) < minDistance)
            {
                nearestPlayerSprite = s;
                minDistance = Math.abs(s.getX() - x);
            }
        }
        if (sprite.getX() < nearestPlayerSprite.getX())
        {
        	sprite.setDirection(Constants.RIGHT);
        }
        else
        {
        	sprite.setDirection(Constants.LEFT);
        }

        //Spawn the copy of the blueprint sprite
        Sprite newSprite = blueprint.copy();

        //Set its position
        newSprite.setX(x);
        newSprite.setY(y);
        
        //Generate dx and dy
        double dx = (nearestPlayerSprite.getX() - x);
        double dy = (nearestPlayerSprite.getY() - y);

        //Calculate angle -- SOH CAH TOA
        double angle = Math.atan(dy / dx);
//        System.out.println("angle: " + Math.toDegrees(angle));
        
        //Magnitude is projectileSpeed
        double magnitude = (double)projectileSpeed;
        
        //Create vector object
        Vector shootVector = new Vector(magnitude, angle);
        
        double velocityX = shootVector.getCosComponent();
        double velocityY = shootVector.getSinComponent();
        if (Math.toDegrees(angle) > 90)
            velocityY *= -1;
        if (Math.toDegrees(angle) <= 0)
        {
            velocityY *= -1;
            velocityX *= -1;
        }
        
        newSprite.setVelocityX(velocityX);
        newSprite.setVelocityY(velocityY);
//        System.out.println("VelocityX : " + velocityX);
//        System.out.println("VelocityY : " + velocityY);

        newSprite.setDirection(sprite.getDirection());
        App.model.addSprite(newSprite);
	}
	public Behavior copy()
	{
		return new ShootAtPlayerBehavior(offsetX, offsetY, blueprint.copy(), projectileSpeed);
	}
}
