package group1.interfaces;

import group1.model.sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;

public interface Drawable 
{
	public void draw(GraphicsContext g, Sprite sprite);
}
