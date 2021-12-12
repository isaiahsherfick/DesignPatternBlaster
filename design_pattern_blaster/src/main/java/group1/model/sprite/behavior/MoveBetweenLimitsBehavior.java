package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;

public class MoveBetweenLimitsBehavior implements Behavior{
	
	double minX, maxX, currentPos;
	boolean isMoveLeft = false, isMoveRight = false;

	public MoveBetweenLimitsBehavior(double minX, double maxX, double currentPos) {
		this.minX = minX;
		this.maxX = maxX;
		this.currentPos = currentPos;
		if(currentPos > minX && currentPos < maxX) {
			isMoveRight = true;
		}
	}

	@Override
	public void performBehavior(Sprite sprite) {
		
		if(isMoveLeft) {
			sprite.setX(sprite.getX() - sprite.getVelocityX());
		}
		if(isMoveRight) {
			sprite.setX(sprite.getX() + sprite.getVelocityX());
		}
		
		if(sprite.getX() > maxX) {
			isMoveLeft = true;
			isMoveRight = false;
		}
		if(sprite.getX() < minX){
			isMoveRight = true;
			isMoveLeft = false;
		}
	}

	@Override
	public Behavior copy() {
		return null;
	}
	
	
	
}
