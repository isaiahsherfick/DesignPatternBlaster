package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;

public class DisplayPopupWhileInteract implements Behavior {

	private Sprite popup;

	public DisplayPopupWhileInteract(Sprite popup) {
		this.popup = popup;
	}

	@Override
	public void performBehavior(Sprite sprite) {
		popup.setEnabled(true);
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
