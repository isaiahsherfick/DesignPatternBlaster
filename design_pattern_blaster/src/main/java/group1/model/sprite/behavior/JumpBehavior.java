package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.model.sprite.Sprite;

public class JumpBehavior implements Behavior
{
	
	private int maxJumpHeight;
	
	public JumpBehavior(int maxJumpHeight)
	{
		this.maxJumpHeight = maxJumpHeight;
	}
	
	public int getMaxJumpHeight()
	{
		return maxJumpHeight;
	}
	
	public void setMaxJumpHeight(int maxJumpHeight)
	{
		this.maxJumpHeight = maxJumpHeight;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type", "JumpBehavior");

		return json;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		
	}

}
