/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package group1.model.sprite.behavior;
import group1.model.sprite.Sprite;

public class MoveBehavior implements Behavior 
{
	public void performBehavior(Sprite sprite)
	{
        //System.out.println("MoveBehavior.java: sprite.getVelocityY() == " + sprite.getVelocityY());
		sprite.setX(sprite.getVelocityX() + sprite.getX());
		sprite.setY(sprite.getVelocityY() + sprite.getY());
	}
	
	public Behavior copy()
	{
		return new MoveBehavior();
	}
}
