package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;

public class PressKeyToInteractPopupBehavior implements Behavior {

	Sprite hintSprite;

	public PressKeyToInteractPopupBehavior(Sprite popupPressToInteract) {
		this.hintSprite = popupPressToInteract;
	}

	@Override
	public void performBehavior(Sprite sprite) {
		hintSprite.enable();
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
