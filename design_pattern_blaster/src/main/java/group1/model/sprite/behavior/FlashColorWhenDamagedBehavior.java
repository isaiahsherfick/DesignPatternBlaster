package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;
import javafx.scene.paint.Color;

public class FlashColorWhenDamagedBehavior implements Behavior
{
	private Color color;
	private Color swapColor;
	private Integer currentHealth;
	
	public FlashColorWhenDamagedBehavior(Color color)
	{
		this.color = color;
		currentHealth = null;
	}
	
	public Behavior copy()
	{
		return new FlashColorWhenDamagedBehavior(color);
	}
	
	private boolean healthChanged(Sprite sprite)
	{
		return currentHealth != sprite.getHealth();
	}
	
	public void performBehavior(Sprite sprite)
	{
		if (currentHealth == null)
		{
			currentHealth = sprite.getHealth();
			swapColor = sprite.getColor();
		}
		else
		{
			if (healthChanged(sprite))
			{
				currentHealth = sprite.getHealth();
				sprite.setColor(color);
			}
			else
			{
				if (sprite.getColor().equals(color) == false)
					sprite.setColor(swapColor);
			}
		}
	}
}
