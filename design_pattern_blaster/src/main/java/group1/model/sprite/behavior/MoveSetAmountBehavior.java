/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package group1.model.sprite.behavior;
import group1.model.sprite.Sprite;

/**
 * Behavior for moving a sprite a set amount,
 * disregarding velocity
 */
public class MoveSetAmountBehavior implements Behavior
{

	int x,y;

	public MoveSetAmountBehavior(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void performBehavior(Sprite sprite)
	{
		sprite.setX(sprite.getX() + x);
		sprite.setY(sprite.getY() + y);
	}
}
