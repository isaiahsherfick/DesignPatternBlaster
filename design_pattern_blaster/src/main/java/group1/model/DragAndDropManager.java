package group1.model;

import java.util.ArrayList;
import java.util.HashMap;

import group1.App;
import group1.model.sprite.Sprite;
import javafx.geometry.Point2D;

public class DragAndDropManager {

	private Point2D dragStartPosition;
	private Point2D dragPosition;
	private Point2D prevDragPosition;
	private boolean dragStatus;
	private ArrayList<Sprite> dragSelectedSprites;

	public DragAndDropManager()
	{
		dragStartPosition = new Point2D(-1,-1);
		prevDragPosition = new Point2D(-1,-1);
		dragPosition = new Point2D(-1,-1);
		dragSelectedSprites = new ArrayList<Sprite>();
		dragStatus = false;
	}

	public boolean getDragStatus() {
		return dragStatus;
	}

	public Point2D getDragPosition() {
		return dragPosition;
	}

	public Point2D getPrevDragPosition() {
		return prevDragPosition;
	}

	public Point2D getDragStartPosition() {
		return dragStartPosition;
	}

	public void setDragPosition(Point2D dragPosition) {
//		if(!dragStatus) {
//			setDragStartPosition(dragPosition);
//			prevDragPosition = dragPosition;
//		} else {
//			prevDragPosition = this.dragPosition;
//		}
		prevDragPosition = this.dragPosition;
		this.dragPosition = dragPosition;
	}
	public void setDragStartPosition(Point2D dragStartPosition) {
		this.dragStartPosition = dragStartPosition;
		this.dragPosition = dragStartPosition;
	}

	public void setPrevDragPosition(Point2D dragStartPosition) {
		this.dragStartPosition = dragStartPosition;
	}

	public void setDragStatus(boolean dragStatus) {
		this.dragStatus = dragStatus;
		if(!dragStatus) {
			dragStartPosition.subtract(dragStartPosition.getX()+1,dragStartPosition.getY()+1);
		}/*} else if(!dragSelectedSprites.isEmpty()) {
			Point2D pt = dragPosition.subtract(prevDragPosition);
			for(Sprite s: dragSelectedSprites) {
				s.setX(s.getX()+pt.getX());
				s.setY(s.getY()+pt.getY());
			}
		}*/
	}

	public boolean draggableSpriteExists(Point2D point) {
//		System.out.println("Point is that draggable spirtes do might: "+point.getX()+", "+point.getY());
		dragSelectedSprites = App.model.getCollisionManager().getDraggablesAtPoint(point, App.model.getSpriteManager());
		return !dragSelectedSprites.isEmpty();
	}

	public void reset() {
		prevDragPosition.subtract(prevDragPosition.getX()+1, prevDragPosition.getY()+1);
		dragPosition.subtract(dragPosition.getX()+1,dragPosition.getY()+1);
		dragSelectedSprites.clear();
		dragStartPosition.subtract(dragStartPosition.getX()+1, dragStartPosition.getY()+1);
		dragStatus = false;
	}
}
