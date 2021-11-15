package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.paint.Color;

public class TakeNoDamageBehavior implements Behavior{

	private Sprite safeSprite;
	private int interval;
	private double counter = 0.0;
	private boolean isSet = false;
	private Color defaultColor;
	private int defaultHealth = 0;
	
	public TakeNoDamageBehavior(Sprite safeSprite) {
		this.safeSprite = safeSprite;
		interval = Constants.TAKE_NO_DAMANGE_INTERVAL;
		defaultColor = safeSprite.getColor();
		defaultHealth = safeSprite.getHealth();
	}
		
	
	public void isBehaviorSet(boolean value) {
		this.isSet = value;
	}
	
	@Override
	public void performBehavior(Sprite sprite) {
		
//		if(isSet) {
			counter += App.model.getTimeDelta();
			
			if (counter > interval) {
				safeSprite.setColor(defaultColor);
				sprite.respondToEvent(GameEvent.PowerUpEndEvent());
//				counter = 0;
//				isSet = false;
			}
			else {
				safeSprite.setColor(Color.GREEN);
				safeSprite.setHealth(defaultHealth);
			}
//		}
		
	}

	@Override
	public Behavior copy() {
		return new TakeNoDamageBehavior(safeSprite);
	}

}
