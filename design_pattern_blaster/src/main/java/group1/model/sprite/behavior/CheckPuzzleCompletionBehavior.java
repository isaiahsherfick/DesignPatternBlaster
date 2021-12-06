package group1.model.sprite.behavior;

import java.nio.file.Paths;
import java.util.ArrayList;

import group1.App;
import group1.model.collision.HitBox;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class CheckPuzzleCompletionBehavior implements Behavior {

	private ArrayList<Sprite> sprites;
	public CheckPuzzleCompletionBehavior(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}

	@Override
	public void performBehavior(Sprite sprite) {
        Sprite purpleBox = sprites.get(0);
        Sprite aquaBox = sprites.get(1);
        Sprite orangeSquareBox = sprites.get(2);
        Sprite blueSquareBox = sprites.get(3);
        Sprite greenSquareBox = sprites.get(4);
        Sprite bluePortraitBox = sprites.get(5);
        Sprite orangePortraitBox = sprites.get(6);
        Sprite blueLandscapeBox = sprites.get(7);
//        HitBox hb = aquaBox.getHitBox();
        Rectangle2D purpleRect = new Rectangle2D(purpleBox.getX(), purpleBox.getY(),purpleBox.getWidth(),purpleBox.getHeight());
        Rectangle2D aquaRect = new Rectangle2D(aquaBox.getX(), aquaBox.getY(),aquaBox.getWidth(),aquaBox.getHeight());
        Rectangle2D orangeSquareRect = new Rectangle2D(orangeSquareBox.getX(), orangeSquareBox.getY(),orangeSquareBox.getWidth(),orangeSquareBox.getHeight());
        Rectangle2D blueSquareRect = new Rectangle2D(blueSquareBox.getX(), blueSquareBox.getY(),blueSquareBox.getWidth(),blueSquareBox.getHeight());
        Rectangle2D greenSquareRect = new Rectangle2D(greenSquareBox.getX(), greenSquareBox.getY(),greenSquareBox.getWidth(),greenSquareBox.getHeight());
        Rectangle2D bluePortraitRect = new Rectangle2D(bluePortraitBox.getX(), bluePortraitBox.getY(),bluePortraitBox.getWidth(),bluePortraitBox.getHeight());
        Rectangle2D orangePortraitRect = new Rectangle2D(orangePortraitBox.getX(), orangePortraitBox.getY(),orangePortraitBox.getWidth(),orangePortraitBox.getHeight());
        Rectangle2D blueLandscapeRect = new Rectangle2D(blueLandscapeBox.getX(), blueLandscapeBox.getY(),blueLandscapeBox.getWidth(),blueLandscapeBox.getHeight());
        if((purpleRect.contains(aquaRect) && purpleRect.contains(greenSquareRect)) &&
        	(aquaRect.contains(orangeSquareRect) && aquaRect.contains(blueSquareRect)) &&
        	(greenSquareRect.contains(bluePortraitRect) && greenSquareRect.contains(orangePortraitRect) && greenSquareRect.contains(blueLandscapeRect))) {
        	System.out.println("Puzzle Completed");
        }
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
