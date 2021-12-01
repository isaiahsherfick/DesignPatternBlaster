package group1.model.sprite.behavior;

import java.util.ArrayList;
import java.util.List;

import group1.App;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.input.KeyCode;
import group1.model.level.Level;


public class CompositePuzzlePopupBehavior implements Behavior {

	private ArrayList<Sprite> boxes;
	private int selectedBox;
	private Sprite messageFromHQ;
	private CompositePuzzleBehavior cpb;

	public CompositePuzzlePopupBehavior(ArrayList<Sprite> boxes, Sprite messageFromHQ) {
		this.boxes = boxes;
		this.messageFromHQ = messageFromHQ;
		if(boxes.size()>0) {
			selectedBox = 0;
//			Level currentLevel = App.model.getCurrentLevel();
//			currentLevel.setFocusSprite(null);
		}
	}
	@Override
	public void performBehavior(Sprite sprite) {

		System.out.println("Puzzle Popup Behaivor");

		if(cpb == null) {
			cpb = new CompositePuzzleBehavior(boxes, messageFromHQ);
			sprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), cpb));
		}
		if(cpb.getEnabled()) {
			if(App.model.getKeyInputManager().isPressed(KeyCode.X)) {
				cpb.disableDialog();
			}
		} else if(!cpb.getEnabled()) {
			if(App.model.getKeyInputManager().isPressed(KeyCode.E)) {
				cpb.enableDialog();
			}
		}
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
