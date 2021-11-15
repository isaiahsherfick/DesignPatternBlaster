package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;

public class IncreaseSpriteSizeBehavior implements Behavior{
	
	private double defaultWidth = 0;
	private double defaultHeight = 0;
	private int amount = 20;
	private double newWidth = 0;
	private double newHeight = 0;
	private Sprite sprite;

	public IncreaseSpriteSizeBehavior(Sprite sprite) {
		this.sprite = sprite;
		defaultWidth = sprite.getWidth();
		defaultHeight = sprite.getHeight();
	}

	@Override
	public void performBehavior(Sprite sprite) {
		
		
		
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
