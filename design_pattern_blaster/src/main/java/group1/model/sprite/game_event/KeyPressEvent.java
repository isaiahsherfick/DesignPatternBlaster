package group1.model.sprite.game_event;

import javafx.scene.input.KeyCode;

public class KeyPressEvent extends GameEvent
{
	private KeyCode keyCode;
	public KeyPressEvent(KeyCode keyCode)
	{
		super(GameEvent.KEY_PRESSED);
	}
	public KeyCode getKeyCode()
	{
		return keyCode;
	}
}
