package group1.model.sprite.behavior;
import group1.model.sprite.Sprite;

public class CollideWithFloorNoClipBehavior implements Behavior 
{
    private Sprite floor;

    public CollideWithFloorNoClipBehavior(Sprite floor)
    {
        this.floor = floor;
    }

	public void performBehavior(Sprite sprite)
	{
        //Only perform the stop if the player is falling
        if (sprite.getVelocityY() > 0 && (sprite.getHeight() + sprite.getY() <= floor.getY() + sprite.getVelocityY()))
        {
            //System.out.println("CollideWithFloorNoClipBehavior.java: Setting sprite's Y to " + floor.getY() + "!");
            sprite.setY(floor.getY() - sprite.getHeight() - 1);
            sprite.setVelocityY(0);
        }
	}
	
	public Behavior copy()
	{
		return new CollideWithFloorNoClipBehavior(floor);
	}
}
