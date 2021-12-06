package group1.model.sprite.behavior;

import group1.App;
import group1.model.sprite.Sprite;
import javafx.geometry.Point2D;

public class MouseDragDoneBehavior implements Behavior {

	@Override
	public void performBehavior(Sprite sprite) {
//		System.out.println("Drag Completion: "+sprite.getX()+", "+sprite.getY());
		App.model.getDragAndDropManager().reset();

	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
