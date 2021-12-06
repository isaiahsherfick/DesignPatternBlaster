package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;
import javafx.scene.paint.Color;

public class ColorBasedCollisionBehavior implements Behavior{

	Color currentGunColor;
	Color currentDoorColor;
	
	public void setCurrentGunColor(Color color) {
		currentGunColor = color;
	}
	public void setCurrentDoorColor(Color color) {
		currentDoorColor = color;
	}
	
	@Override
	public void performBehavior(Sprite sprite) {
		if(currentGunColor == currentDoorColor) {
			sprite.disable();
		}
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
