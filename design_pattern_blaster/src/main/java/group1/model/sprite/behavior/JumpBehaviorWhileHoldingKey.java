package group1.model.sprite.behavior;
import org.json.simple.JSONObject;
import group1.model.sprite.Sprite;
import group1.App;
import javafx.scene.input.KeyCode;

public class JumpBehaviorWhileHoldingKey implements Behavior
{
	
	private int maxJumpHeight;
    private KeyCode keyToJumpWhenHeld;
    private Double yValueBeforeJumpStarted;
    private boolean jumping, falling;
	
	public JumpBehaviorWhileHoldingKey(int maxJumpHeight, KeyCode key)
	{
		this.maxJumpHeight = maxJumpHeight;
        keyToJumpWhenHeld = key;
        yValueBeforeJumpStarted = null;
        jumping = false;
        falling = false;
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
            System.out.println("JUMPING!");
            if (!falling)
            {
                if (!jumping)
                {
                    jumping = true;
                    yValueBeforeJumpStarted = sprite.getY();
                    sprite.setY(sprite.getY() + sprite.getVelocityY());
                }
                else
                {
                    if (sprite.getY() < yValueBeforeJumpStarted + maxJumpHeight)
                    {
                        sprite.setY(sprite.getY() + sprite.getVelocityY());
                    }
                }
            }
		}
        else 
        {
            if (jumping)
            {
                falling = true;
                jumping = false;
            }
            else if (falling)
            {
                System.out.println("FALLING!");
                sprite.setY(sprite.getY() - sprite.getVelocityY());
                if (sprite.getY() <= yValueBeforeJumpStarted)
                    falling = false;
            }
        }
	}
}
