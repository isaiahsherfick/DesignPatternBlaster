package group1.model.sprite.game_event;

import javafx.scene.input.KeyCode;

public class KeyPressEvent extends GameEvent
{
	private KeyCode keyCode;
	public KeyPressEvent(KeyCode keyCode)
	{
		super(GameEvent.KEY_PRESSED);
		this.keyCode = keyCode;
	}
	public KeyCode getKeyCode()
	{
		return keyCode;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof KeyPressEvent)
		{
			KeyPressEvent k = (KeyPressEvent)o;
			return keyCode.equals(k.getKeyCode());
		}
		return false;
	}
}
