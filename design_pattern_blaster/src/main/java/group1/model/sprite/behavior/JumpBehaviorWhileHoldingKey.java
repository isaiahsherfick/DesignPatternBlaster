package group1.model.sprite.behavior;
import org.json.simple.JSONObject;
import group1.model.sprite.Sprite;
import group1.App;
import javafx.scene.input.KeyCode;

public class JumpBehaviorWhileHoldingKey implements Behavior
{
	
	private int maxJumpHeight;
    private KeyCode keyToJumpWhenHeld;
    private Integer yValueBeforeJumpStarted;
	
	public JumpBehaviorWhileHoldingKey(int maxJumpHeight, KeyCode key)
	{
		this.maxJumpHeight = maxJumpHeight;
        keyToJumpWhenHeld = key;
        yValueBeforeJumpStarted = null;
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
		if (App.model.getKeyInputManager().isPressed(keyToJumpWhenHeld))
		{
            
		}
	}

}
