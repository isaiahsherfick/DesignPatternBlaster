package group1.model.collision;

import group1.model.sprite.Sprite;

public class HitBox {
	
	Sprite sprite;

	public HitBox(Sprite sprite) {
		super();
		this.sprite = sprite;
	}
	
	public HitBoxOverlapType overlaps(HitBox secondaryBox) {
		
		double topLeftX = sprite.getX();
		double topLeftY = sprite.getY();
		
		double bottomRightX = sprite.getX() + sprite.getWidth();
		double bottomRightY = sprite.getY() + sprite.getHeight();
		
		boolean bottomOverlap = bottomRightY <= secondaryBox.getTopLeftY() && bottomRightY >= secondaryBox.getBottomRightY();
		boolean topOverlap = topLeftY >= secondaryBox.getBottomRightY() && topLeftY <= secondaryBox.getTopLeftY();
		boolean leftOverlap = topLeftX <= secondaryBox.getBottomRightX() && topLeftX >= secondaryBox.getTopLeftX();
		boolean rightOverlap = bottomRightX >= secondaryBox.getTopLeftX() && bottomRightX <= secondaryBox.getBottomRightX();
		
		if(bottomOverlap) {
			if(leftOverlap) 
				return HitBoxOverlapType.BOTTOM_LEFT;
			else if(rightOverlap) 
				return HitBoxOverlapType.BOTTOM_RIGHT;
			else 
				return HitBoxOverlapType.BOTTOM_CENTER;
		} 
		else if(topOverlap) {
			if(leftOverlap) 
				return HitBoxOverlapType.TOP_LEFT;
			else if(rightOverlap) 
				return HitBoxOverlapType.TOP_RIGHT;
			else 
				return HitBoxOverlapType.TOP_CENTER;
		} else if(leftOverlap) 
			return HitBoxOverlapType.LEFT_CENTER;
		else if(rightOverlap) 
			return HitBoxOverlapType.RIGHT_CENTER;
		
		return HitBoxOverlapType.NO_OVERLAP;		
	}
	
	public double getTopLeftX() {
		return sprite.getX();
	}
	
	public double getTopLeftY() {
		return sprite.getY();
	}
	
	public double getBottomRightX() {
		return sprite.getX() + sprite.getWidth();
	}
	
	public double getBottomRightY() {
		return sprite.getY() + sprite.getHeight();
	}
}
