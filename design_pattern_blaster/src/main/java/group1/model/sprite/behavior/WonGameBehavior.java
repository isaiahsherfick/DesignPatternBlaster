package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.level.Level;
import group1.model.sprite.Sprite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;


public class WonGameBehavior implements Behavior {


    public WonGameBehavior() {

    }

    @Override
    public void performBehavior(Sprite sprite) {

        Stage mainStage = App.model.getMainStage();
        if (mainStage != null) {
			App.model.clearSprites();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(mainStage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setPadding(new Insets(30,30,30,30));
			dialogVbox.setAlignment(Pos.CENTER);

            Label label = new Label("You beat the game!! These were your scores!");
            label.setAlignment(Pos.CENTER);
            label.setTextAlignment(TextAlignment.CENTER);
            dialogVbox.getChildren().add(label);
            List<String> stageNames = List.of("Observer","Strategy","Command","Composite","Singleton","Factory");
            int stageNamesIndex = 0;
            for(Level l: App.model.getLevelManager().getCompletedLevelsList()){
                Label score = new Label(stageNames.get(stageNamesIndex) + ": " + l.getLevelScore());
                score.setAlignment(Pos.CENTER);
                score.setTextAlignment(TextAlignment.CENTER);
                dialogVbox.getChildren().add(score);
                stageNamesIndex++;

            }

			Scene dialogScene = new Scene(dialogVbox);
			dialog.setScene(dialogScene);

			dialog.show();


        }


    }


    public Behavior copy() {
        return new WonGameBehavior();
    }

}
