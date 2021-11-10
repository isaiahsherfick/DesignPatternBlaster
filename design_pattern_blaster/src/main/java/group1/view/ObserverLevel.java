package group1.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import group1.interfaces.Drawable;
import group1.interfaces.Observer;
import group1.model.sprite.Sprite;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ObserverLevel implements Drawable{

	private Stage levelStage;
	private Canvas levelCanvas;
	private Scene levelScene;

	private ArrayList<Drawable> drawables;
	private GraphicsContext graphicsContext;

	public ObserverLevel(Stage stage, Canvas canvas) {

		levelStage = stage;
		levelCanvas = canvas;
		drawables = new ArrayList<Drawable>();
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("observer_level.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }

        renderPlayer();
		renderPulsingCircle();

//		levelStage.show();
	}

	private void renderPulsingCircle() {
//		Image circleImage = new Image("/design_pattern_blaster/src/main/resources/observer/1x/energize0.png");
		DrawableCircle circle = new DrawableCircle();
		DrawableImage circleImage = new DrawableImage();
		circleImage.setPath("/design_pattern_blaster/src/main/resources/observer/1x/energize0.png");
		circle.addDrawable(circleImage);
		drawables.add(circle);

	}

	private void renderPlayer() {
		Drawable player = new DrawableRectangle();
	}

	public void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}

	public void removeDrawable(Drawable drawable) {
		drawables.remove(drawable);
	}

	@Override
	public void draw(GraphicsContext g) {
		for(Drawable drawable: drawables) {
			drawable.draw(g);
		}
	}

	@Override
	public void draw(GraphicsContext g, Sprite sprite) {
		// TODO Auto-generated method stub

	}
}


