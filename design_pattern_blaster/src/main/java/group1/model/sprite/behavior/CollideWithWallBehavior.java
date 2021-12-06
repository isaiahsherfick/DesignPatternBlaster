package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;

public class CollideWithWallBehavior implements Behavior {

	Sprite object;
	int offset;
	
	public CollideWithWallBehavior(Sprite object,int offset) {
		this.object = object;
		this.offset = offset;
	}
	
	@Override
	public void performBehavior(Sprite sprite) {
		// TODO Auto-generated method stub
		if(sprite.getVelocityX()>0 || sprite.getX()>object.getX()) {
			sprite.setX(object.getX() - sprite.getWidth() - offset);
            sprite.setVelocityX(0);
		}
		
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return new CollideWithWallBehavior(object,offset);
	}

}
