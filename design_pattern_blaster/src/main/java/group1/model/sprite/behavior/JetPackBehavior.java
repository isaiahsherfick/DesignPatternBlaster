package group1.model.sprite.behavior;
import org.json.simple.JSONObject;
import group1.model.sprite.Sprite;
import group1.App;
import group1.constants.Constants;
import javafx.scene.input.KeyCode;

public class JetPackBehavior implements Behavior
{
    private KeyCode jumpKey;
    private double yVelocity;
    private double maxVelocity;
	
	public JetPackBehavior(KeyCode key, double dy)
	{
        jumpKey = key;
        yVelocity = dy;
        maxVelocity = Constants.MAX_JETPACK_DY;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
        if (App.model.getKeyInputManager().isPressed(jumpKey) && sprite.getVelocityY() > maxVelocity)
        {
            sprite.setY(sprite.getY() - 1);
            sprite.setVelocityY(sprite.getVelocityY() + yVelocity);
        }
	}
	
	public Behavior copy()
	{
		return new JetPackBehavior(jumpKey, yVelocity);
	}
}
