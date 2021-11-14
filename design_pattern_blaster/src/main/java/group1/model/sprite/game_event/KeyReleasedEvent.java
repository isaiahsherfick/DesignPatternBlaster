package group1.model.sprite.game_event;

import javafx.scene.input.KeyCode;

public class KeyReleasedEvent extends GameEvent
{
	private KeyCode keyCode;
	public KeyReleasedEvent(KeyCode keyCode)
	{
		super(GameEvent.KEY_RELEASED);
		this.keyCode = keyCode;
	}
	public KeyCode getKeyCode()
	{
		return keyCode;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof KeyReleasedEvent)
		{
			KeyReleasedEvent k = (KeyReleasedEvent)o;
			return keyCode.equals(k.getKeyCode());
		}
		return false;
	}
}
