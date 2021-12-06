package group1.model.sprite.behavior;
import group1.App;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
public class PlayerMoveBehavior implements Behavior
{
	public void performBehavior(Sprite sprite)
	{
        //System.out.println("MoveBehavior.java: sprite.getVelocityY() == " + sprite.getVelocityY());
		sprite.setX(sprite.getVelocityX() + sprite.getX());
		sprite.setY(sprite.getVelocityY() + sprite.getY());
		if(sprite.getVelocityX()==0) {
			sprite.getAnimation().setState(AnimationState.IDLE);
		}

	}

	public Behavior copy()
	{
		return new PlayerMoveBehavior();
	}
}
