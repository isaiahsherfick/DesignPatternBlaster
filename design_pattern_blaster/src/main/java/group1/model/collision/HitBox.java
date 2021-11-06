package group1.model.collision;

import group1.model.sprite.Sprite;

public class HitBox
{

	private double x, y, width, height;

	public HitBox(Sprite sprite)
	{
		x = sprite.getX();
		y = sprite.getY();
		width = sprite.getWidth();
		height = sprite.getHeight();
	}

	public HitBox(double x, double y, double w, double h)
	{
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}

	public HitBoxOverlapType overlaps(HitBox secondaryBox) {

		double topLeftX = getTopLeftX();
		double topLeftY = getTopLeftY();

		double bottomRightX = getBottomRightX();
		double bottomRightY = getBottomRightY();

		boolean bottomOverlap = bottomRightY >= secondaryBox.getTopLeftY() && topLeftY <= secondaryBox.getBottomRightY();
		boolean topOverlap = topLeftY <= secondaryBox.getBottomRightY() && bottomRightY >= secondaryBox.getTopLeftY();
		boolean leftOverlap = bottomRightX >= secondaryBox.getTopLeftX() && topLeftX <= secondaryBox.getBottomRightX();
		boolean rightOverlap = topLeftX <= secondaryBox.getBottomRightX() && bottomRightX >= secondaryBox.getTopLeftX();

		if((topOverlap && bottomOverlap) && !(leftOverlap || rightOverlap))
			return HitBoxOverlapType.NO_OVERLAP;
		else if((leftOverlap && rightOverlap) && !(topOverlap || bottomOverlap))
			return HitBoxOverlapType.NO_OVERLAP;
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
		return x;
	}

	public double getTopLeftY() {
		return y;
	}

	public double getBottomRightX() {
		return x + width;
	}

	public double getBottomRightY() {
		return y + height;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public HitBox copy()
	{
		return new HitBox(x,y,width,height);
	}
}
