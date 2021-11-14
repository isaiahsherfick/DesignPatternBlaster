package group1.model.sprite.behavior;
import org.json.simple.JSONObject;
import group1.model.sprite.Sprite;
import group1.App;
import javafx.scene.input.KeyCode;

public class JumpBehavior implements Behavior
{
    private KeyCode jumpKey;
    private double yVelocity;
	
	public JumpBehavior(KeyCode key, double dy)
	{
        jumpKey = key;
        yVelocity = dy;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
        if (App.model.getKeyInputManager().isPressed(jumpKey) && sprite.getVelocityY() == 0)
        {
            sprite.setY(sprite.getY() - 1);
            sprite.setVelocityY(yVelocity);
        }
	}
	
	public Behavior copy()
	{
		return new JumpBehavior(jumpKey, yVelocity);
	}
}
