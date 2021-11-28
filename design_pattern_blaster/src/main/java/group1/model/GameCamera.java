package group1.model;

import group1.constants.Constants;
import group1.model.sprite.NullSprite;
import group1.model.sprite.Sprite;

public class GameCamera
{
	private double x, y, xMinClampPos, xMaxClampPos; 
	private Sprite focusSprite;
	
	private boolean isMoving = false;

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public Sprite getFocusSprite() {
		return focusSprite;
	}

	//set the sprite which the game camera should follow (player in our case)
	public void setFocusSprite(Sprite focusSprite) {
		this.focusSprite = focusSprite;
	}

	public GameCamera(double x, double y) {
		this.x = x;
		this.y = y;
		focusSprite = new NullSprite();
	}

	//move camera keeping focus sprite at the center. We shall only be moving camera with respect to the x axis
	public void moveCamera() {
		double temp = -focusSprite.getX() + Constants.WINDOW_WIDTH/2;
		if(isMoving) {
			x = temp;
			if(temp >= xMinClampPos) {
				x = xMinClampPos;
			}
			
			if(temp <= -xMaxClampPos) {
				x = -xMaxClampPos;
			}
			
		}
	}

	//set isMoving to false to stop camera movement
	public void stopCamera() {
		isMoving = false;
	}

	public double getXPos() {
//		if(x<0) return 0;
		return x;
	}

	public void setXPos(double x) {
		this.x = x;
	}

	public double getYPos() {
//		if(y<0) return 0;
		return y;
	}

	public void setYPos(double y) {
		this.y = y;
	}

	public double getxMinClampPos() {
		return xMinClampPos;
	}

	public void setxMinClampPos(double xMinClampPos) {
		this.xMinClampPos = xMinClampPos;
	}

	public double getxMaxClampPos() {
		return xMaxClampPos;
	}

	public void setxMaxClampPos(double xMaxClampPos) {
		this.xMaxClampPos = xMaxClampPos;
	}
	
}