package group1.model.sprite.behavior;

import java.util.ArrayList;

import group1.App;
import group1.model.sprite.Sprite;

public class MessageFromHQPopupBehavior implements Behavior {

	Sprite messageSprite;
	ArrayList<Sprite> puzzleSprites;

	public MessageFromHQPopupBehavior(Sprite messageSprite, ArrayList<Sprite> puzzleSprites) {
		this.messageSprite = messageSprite;
		this.puzzleSprites = puzzleSprites;
	}
	@Override
	public void performBehavior(Sprite sprite) {
		App.model.getGameCamera().getFocusSprite().disable();
		messageSprite.enable();
		for(Sprite s: puzzleSprites) {
			s.enable();
		}
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
