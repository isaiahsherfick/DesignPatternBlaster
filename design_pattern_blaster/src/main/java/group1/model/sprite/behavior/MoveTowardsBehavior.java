package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;
import group1.physics.Vector;

public class MoveTowardsBehavior implements Behavior{

	public MoveTowardsBehavior(Sprite moveTowardsSprite) {
		this.spriteToMoveTowards = moveTowardsSprite;
	}

	private Sprite spriteToMoveTowards;
	
	public Sprite getSpriteToMoveTowards() {
		return spriteToMoveTowards;
	}

	public void setSpriteToMoveTowards(Sprite spriteToMoveTowards) {
		this.spriteToMoveTowards = spriteToMoveTowards;
	}

	@Override
	public void performBehavior(Sprite sprite) {
		
		
		//needs to be optimized
		sprite.turnTowards(spriteToMoveTowards);
		double x = sprite.getX();
		double y = sprite.getY();
			
		//Generate dx and dy
		double dx = (spriteToMoveTowards.getX() - x);
		double dy = (spriteToMoveTowards.getY() - y);
		//Calculate angle -- SOH CAH TOA
		double angle = Math.atan(dy / dx);
		//System.out.println("angle: " + angle);
		//Magnitude is projectileSpeed
		double magnitude = (double)2;
		//System.out.println("magnitude: " + magnitude);
		//Create vector object
		Vector moveVector = new Vector(magnitude, angle);
		double velocityX = moveVector.getCosComponent();
		//System.out.println("velocityX: " + velocityX);
		double velocityY = moveVector.getSinComponent();
		//System.out.println("velocityY: " + velocityY);

		if (Math.toDegrees(angle) > 90)
			velocityY *= -1;
		if (Math.toDegrees(angle) <= 0)
		{
			velocityY *= -1;
			velocityX *= -1;
		}
		
		//Move
		sprite.setX(sprite.getX() + velocityX);
		if (sprite.getY() < 450)
		{
			//System.out.println("ObserverBehavior.java: y " + sprite.getY() + " is less than maxY " + maxY);
			sprite.setY(sprite.getY() + velocityY);
		}
		
		
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
