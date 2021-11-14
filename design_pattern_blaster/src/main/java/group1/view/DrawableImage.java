package group1.view;

import group1.interfaces.Drawable;
import group1.model.sprite.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawableImage implements Drawable {

	private String path;
	private Point2D topLeft;
	private int width;
	private int height;
	private Image image;
	public DrawableImage() {
//		image = new Image();
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setTopLeft(Point2D point) {
		topLeft = point;
	}

	public void setTopLeft(int posX, int posY) {
		topLeft = new Point2D(posX, posY);
	}

	@Override
	public void draw(GraphicsContext g) {
//		image = new Image(path);
		//		Image image = new Image(getClass().getResource(path).toString());
		if(g==null)
			//System.out.println("Graphics Context is null");
		if(image!=null) {
//			g.drawImage(image, topLeft.getX(), topLeft.getY());
//			g.drawImage(image, topLeft.getX(), topLeft.getY(), width, height);
			g.drawImage(image, topLeft.getX(), topLeft.getY(), image.getWidth(), image.getHeight());
			//System.out.println("Image should be displayed"+image.getWidth()+", "+image.getHeight());
		} else {
			//System.out.println("Image is null");
		}
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void draw(GraphicsContext g, Sprite sprite) {
		// TODO Auto-generated method stub

	}

}
