package group1.model.sprite.behavior;
import group1.App;
import group1.model.sprite.Sprite;

//Same as normal move behavior except it incrementally checks for collision with floors and stops movement if it's detected
public class PlayerMoveBehavior implements Behavior 
{
	public void performBehavior(Sprite sprite)
	{
        //Move on the x
        sprite.setX(sprite.getX() + sprite.getVelocityX());

        //On the y, incrementally move and check for floor collision at each step
        //so as to avoid clipping above or in the floor
        double dy = sprite.getVelocityY();
        double y = sprite.getY();
        boolean dyNegative = dy < 0;
        if (dyNegative)
        {
            while (dy < 0)
            {
                y += -1;
                dy += 1;
                //If we're colliding with the floor
                if (App.model.spriteIsCollidingWithFloor(sprite))
                {
                    //Stop falling / jumping
                    dy = 0;
                }
            }
        }
        else
        {
            while (dy > 0)
            {
                y += 1;
                dy += -1;
                //If we're colliding with the floor
                if (App.model.spriteIsCollidingWithFloor(sprite))
                {
                    //Stop falling / jumping
                    dy = 0;
                }
            }
        }
        //Move on the y
        sprite.setY(y);
	}
	
	public Behavior copy()
	{
		return new PlayerMoveBehavior();
	}
}
