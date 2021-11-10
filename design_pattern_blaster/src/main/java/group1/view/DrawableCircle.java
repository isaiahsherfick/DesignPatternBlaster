package group1.view;

import java.util.ArrayList;

import group1.interfaces.Drawable;
import group1.model.sprite.Sprite;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DrawableCircle implements Drawable {

	private ArrayList<Drawable> drawables;
	private Circle circle;
	private int orientationAngle;

	public static final int FULL_ROTATION_DEGREES = 360;

	public DrawableCircle() {
		circle = new Circle();
		drawables = new ArrayList<Drawable>();
		orientationAngle = 0;
	}

	public void setRadius(int radius) {
		circle.setRadius(radius);
	}

	public int getRadius() {
		return (int)circle.getRadius();
	}

	public void setCenter(Point2D point) {
		circle.setCenterX(point.getX());
		circle.setCenterY(point.getY());
	}

	public void setCenter(int centerX, int centerY) {
		circle.setCenterX(centerX);
		circle.setCenterY(centerY);
	}


	public void setPivot(Point2D pivot) {
//		rotate.setPivotX(point.getX());
//		rotate.setPivotY(point.getY());
//		pivot = point;
		circle.setRotationAxis(new Point3D(pivot.getX(),pivot.getY(),0)); //No z-axis
	}

	public void setPivot(int pivotX, int pivotY) {
		circle.setRotationAxis(new Point3D(pivotX, pivotY,0)); //No z-axis
	}

	public void rotateClockwise(int angle) {
		orientationAngle = (orientationAngle+angle)%FULL_ROTATION_DEGREES;
		circle.setRotate(angle);
	}

	public void rotateAntiClockwise(int angle) {
		orientationAngle = (orientationAngle-angle)%FULL_ROTATION_DEGREES;
		circle.setRotate(0-angle);
	}

	public void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}

	@Override
	public void draw(GraphicsContext g) {
		g.setFill(Color.RED);
		g.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius()*2, circle.getRadius()*2);
		g.setStroke(Color.ALICEBLUE);
		g.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius()*2, circle.getRadius()*2);
		for(Drawable drawable: drawables) {
			drawable.draw(g);
		}
	}

	@Override
	public void draw(GraphicsContext g, Sprite sprite) {
		// TODO Auto-generated method stub

	}
}
