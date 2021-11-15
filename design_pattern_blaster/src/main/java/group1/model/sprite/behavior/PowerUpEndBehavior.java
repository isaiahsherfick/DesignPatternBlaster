package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;

public class PowerUpEndBehavior implements Behavior{

	
	private StrategyBehavior strategyBehavior;
	
	public PowerUpEndBehavior(StrategyBehavior strategyBehavior) {
		this.strategyBehavior = strategyBehavior;
	}
	
	@Override
	public void performBehavior(Sprite sprite) {
		
		strategyBehavior.setExecution(false);
		
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
