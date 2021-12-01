package group1.model.sprite.behavior;

import java.util.ArrayList;
import java.util.List;

import group1.App;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.input.KeyCode;
import group1.model.level.Level;


public class CompositePuzzleBehavior implements Behavior {

	private ArrayList<Sprite> boxes;
	private int selectedBox;
	private Sprite messageFromHQ;
	private boolean enabled;

	public CompositePuzzleBehavior(ArrayList<Sprite> boxes, Sprite messageFromHQ) {
		this.boxes = boxes;
		this.enabled = false;
		this.messageFromHQ = messageFromHQ;
		if(boxes.size()>0) {
			selectedBox = 0;
//			Level currentLevel = App.model.getCurrentLevel();
//			currentLevel.setFocusSprite(null);
		}
	}
	@Override
	public void performBehavior(Sprite sprite) {
		System.out.println("Puzzle behavior");
		if(enabled) {
			if(App.model.getKeyInputManager().isPressed(KeyCode.J)) {
				selectedBox=++selectedBox%boxes.size();
			} else if(App.model.getKeyInputManager().isPressed(KeyCode.K)) {
				selectedBox=((selectedBox+boxes.size())-1 )%boxes.size();
			}
			if(App.model.getKeyInputManager().isPressed(KeyCode.W)) {
				Sprite box = boxes.get(selectedBox);
				box.setY(box.getY()-10);
			}
			if(App.model.getKeyInputManager().isPressed(KeyCode.A)) {
				Sprite box = boxes.get(selectedBox);
				box.setX(box.getX()-10);
			}
			if(App.model.getKeyInputManager().isPressed(KeyCode.S)) {
				Sprite box = boxes.get(selectedBox);
				box.setY(box.getY()+10);
			}
			if(App.model.getKeyInputManager().isPressed(KeyCode.D)) {
				Sprite box = boxes.get(selectedBox);
				box.setX(box.getX()+10);
			}
		}
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}
	public void disableDialog() {
		this.enabled = false;
		for(Sprite box: boxes) {
			box.disable();
		}
		messageFromHQ.disable();
	}

	public void enableDialog() {
		this.enabled = true;
		for(Sprite box: boxes) {
			box.enable();
		}
		messageFromHQ.enable();

	}
	public boolean getEnabled() {
		return this.enabled;
	}

}
