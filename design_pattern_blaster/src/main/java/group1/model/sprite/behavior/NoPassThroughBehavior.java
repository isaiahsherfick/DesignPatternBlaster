package group1.model.sprite.behavior;

import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class NoPassThroughBehavior implements Behavior {

	private Sprite wall;

	public NoPassThroughBehavior(Sprite wall) {
		this.wall = wall;
	}
	@Override
	public void performBehavior(Sprite sprite) {

		if(wall.isEnabled())
		{
			if(sprite.getDirection() == Constants.LEFT) {
				if(sprite.getX()<wall.getX()+wall.getWidth()) {
					sprite.setX(wall.getX()+wall.getWidth()+10);
				}
			} else if(sprite.getDirection() == Constants.RIGHT) {
				if(sprite.getX()+sprite.getWidth()>wall.getX()) {
					sprite.setX(wall.getX()-sprite.getWidth()-10);
				}
			}
		}
	}

	@Override
	public Behavior copy() {
		return new NoPassThroughBehavior(wall.copy());
	}

}

