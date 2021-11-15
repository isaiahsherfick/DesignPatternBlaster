package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import javafx.scene.paint.Color;

public class TakeNoDamageBehavior implements Behavior{

	private Sprite safeSprite;
	private int interval;
	private double counter = 0.0;
	private boolean isSet = false;
	
	public TakeNoDamageBehavior(Sprite safeSprite) {
		this.safeSprite = safeSprite;
		interval = Constants.TAKE_NO_DAMANGE_INTERVAL;
	}
		
	
	public void isBehaviorSet(boolean value) {
		this.isSet = value;
	}
	
	@Override
	public void performBehavior(Sprite sprite) {
		
//		if(isSet) {
			counter += App.model.getTimeDelta();
			Color defaultColor = safeSprite.getColor();
			int currentHealth = safeSprite.getHealth();
			
			if (counter > interval) {
				safeSprite.setColor(defaultColor);
//				counter = 0;
				isSet = false;
			}
			else {
				System.out.println("Color changiinggg "+counter);
				safeSprite.setColor(Color.GREEN);
				safeSprite.setHealth(currentHealth);
			}
//		}
		
	}

	@Override
	public Behavior copy() {
		return new TakeNoDamageBehavior(safeSprite);
	}

}
