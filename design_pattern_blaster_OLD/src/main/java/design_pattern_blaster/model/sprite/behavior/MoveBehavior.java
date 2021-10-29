/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package design_pattern_blaster.model.sprite.behavior;
import org.json.simple.JSONObject;
import design_pattern_blaster.model.sprite.Sprite;

public class MoveBehavior implements Behavior 
{
	public void performBehavior(Sprite sprite)
	{
		sprite.setX(sprite.getVelocityX() + sprite.getX());
		sprite.setY(sprite.getVelocityY() + sprite.getY());
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","MoveBehavior");
		return json;
	}
}
