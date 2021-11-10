package group1.view;

import java.util.ArrayList;

import group1.interfaces.Drawable;
import group1.model.sprite.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class DrawableRectangle implements Drawable {

	private Rectangle rectangle;
	private ArrayList<Drawable> drawableList;

	public DrawableRectangle() {
		rectangle = new Rectangle();
		drawableList = new ArrayList<Drawable>();
	}

	public void setWidth(int width) {
		rectangle.setWidth(width);
	}

	public int getWidth() {
		return (int)rectangle.getWidth();
	}

	public void setHeight(int height) {
		rectangle.setHeight(height);
	}

	public int getHeight() {
		return (int)rectangle.getHeight();
	}

	public void setTopLeft(Point2D point) {
		rectangle.setLayoutX(point.getX());
		rectangle.setLayoutY(point.getY());
	}

	public void setTopLeft(int posX, int posY) {
		rectangle.setLayoutX(posX);
		rectangle.setLayoutY(posY);
	}

	public void addDrawable(Drawable drawable) {
		drawableList.add(drawable);
	}

	@Override
	public void draw(GraphicsContext g) {
		for(Drawable drawable: drawableList) {
			drawable.draw(g);
		}
	}

	@Override
	public void draw(GraphicsContext g, Sprite sprite) {
		// TODO Auto-generated method stub

	}
}
