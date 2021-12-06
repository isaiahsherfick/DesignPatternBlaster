package group1.model.sprite.behavior;

import group1.App;
import group1.model.sprite.Sprite;
import javafx.geometry.Point2D;

public class MouseDragUpdateBehavior implements Behavior {

	@Override
	public void performBehavior(Sprite sprite) {
		if(App.model.getCollisionManager().checkIfDraggableCollision(sprite)) {
//			System.out.print("Drag happened x before: "+sprite.getX());
			Point2D dragPosition = App.model.getDragAndDropManager().getDragPosition();
			Point2D prevDragPosition = App.model.getDragAndDropManager().getPrevDragPosition();
			Point2D pt = dragPosition.subtract(prevDragPosition);
			sprite.setX(sprite.getX()+pt.getX());
			sprite.setY(sprite.getY()+pt.getY());
//			System.out.println("x after: "+sprite.getX());
//			System.out.println("PrevDrag: "+prevDragPosition.getX()+",  "+prevDragPosition.getY());
//			System.out.println("currDrag: "+dragPosition.getX()+",  "+dragPosition.getY());
//			System.out.println("PT: "+pt.getX()+",  "+pt.getY());
		} else {
//			System.out.println("Dragged but you're dragging nothing");
		}
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
