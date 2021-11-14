package group1.model.sprite.behavior;
import org.json.simple.JSONObject;
import group1.model.sprite.Sprite;
import group1.App;
import javafx.scene.input.KeyCode;

public class JumpBehavior implements Behavior
{
	
	private int maxJumpHeight;
    private KeyCode keyToJumpWhenHeld;
    private Double yValueBeforeJumpStarted;
    private boolean jumping, falling;
	
	public JumpBehavior(int maxJumpHeight, KeyCode key)
	{
		this.maxJumpHeight = maxJumpHeight;
        keyToJumpWhenHeld = key;
        yValueBeforeJumpStarted = 0.0;
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
	public void performBehavior(Sprite sprite) 
	{
        double ceiling = yValueBeforeJumpStarted - maxJumpHeight;
		if (App.model.getKeyInputManager().isPressed(keyToJumpWhenHeld) && !jumping)
		{
            if (!jumping && !falling)
            {
                jumping = true;
                yValueBeforeJumpStarted = sprite.getY();
                sprite.setY(sprite.getY() - sprite.getVelocityY());
            }
        }
        else if (jumping && sprite.getY() > ceiling)
        {
            sprite.setY(sprite.getY() - sprite.getVelocityY());
        }
        else if (jumping && sprite.getY() <= ceiling)
        {
            jumping = false;
            falling = true;
        }
        if (falling && sprite.getY() < yValueBeforeJumpStarted)
        {
            //System.out.println("FALLING!");
            sprite.setY(sprite.getY() + sprite.getVelocityY());
            if (sprite.getY() >= yValueBeforeJumpStarted)
            {
                falling = false;
            }
        }
	}
	
	public Behavior copy()
	{
		return new JumpBehavior(maxJumpHeight, keyToJumpWhenHeld);
	}
}
