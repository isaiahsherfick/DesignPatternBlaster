package group1.model.sprite.behavior;

import group1.App;
import group1.model.sprite.Sprite;

public class PowerUpBehavior implements Behavior{
	
	private StrategyBehavior strategyBehavior;
	
	public PowerUpBehavior(StrategyBehavior strategyBehavior) {
		this.strategyBehavior = strategyBehavior;
	}

	@Override
	public void performBehavior(Sprite sprite) {
		
//		Iterator<EventBehavior> eventIterator = sprite.getEventBehaviors().iterator();
//        while(eventIterator.hasNext()) {
//        	if(eventIterator.next().equals(behavior))
//		}
		strategyBehavior.setExecution(true);
		strategyBehavior.setSpriteToMoveTowards(App.model.getLevelManager().getLevel(2).getFocusSprite());
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
