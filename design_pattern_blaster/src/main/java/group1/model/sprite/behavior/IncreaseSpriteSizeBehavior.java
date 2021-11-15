package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;

public class IncreaseSpriteSizeBehavior implements Behavior{
	
	private double defaultWidth = 0;
	private double defaultHeight = 0;
	private int amount = 25;
	private double newWidth = 0;
	private double newHeight = 0;
	private Sprite sprite;

	public IncreaseSpriteSizeBehavior(Sprite sprite) {
		this.sprite = sprite;
		defaultWidth = sprite.getWidth();
		defaultHeight = sprite.getHeight();
		
		newWidth = defaultWidth + amount;
		newHeight = defaultHeight + amount;
	}

	@Override
	public void performBehavior(Sprite sprite) {
		
		this.sprite.setWidth(newWidth);
		this.sprite.setHeight(newHeight);
		
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
