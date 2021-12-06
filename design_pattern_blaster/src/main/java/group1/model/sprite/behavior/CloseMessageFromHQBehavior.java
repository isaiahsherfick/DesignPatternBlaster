package group1.model.sprite.behavior;

import group1.App;
import group1.model.sprite.Sprite;

public class CloseMessageFromHQBehavior implements Behavior {

	private Sprite messageFromHQ;

	public CloseMessageFromHQBehavior(Sprite messageFromHQ) {
		this.messageFromHQ = messageFromHQ;
	}
	@Override
	public void performBehavior(Sprite sprite) {
		messageFromHQ.disable();
		App.model.getGameCamera().getFocusSprite().enable();
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
