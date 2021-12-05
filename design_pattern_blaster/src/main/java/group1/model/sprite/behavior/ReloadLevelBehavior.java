package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ReloadLevelBehavior implements Behavior {


    public ReloadLevelBehavior() {

    }

    @Override
    public void performBehavior(Sprite sprite) {
        App.model.getKeyInputManager().releaseAll();
        Stage mainStage = App.model.getMainStage();
        if (mainStage != null) {
           // App.model.setWaiting(true);

			App.model.clearSprites();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(mainStage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setPadding(new Insets(30,30,30,30));
			dialogVbox.setAlignment(Pos.CENTER);

            Label label = new Label("Oh no! You lost!\nClose out of this window to try again");
            label.setAlignment(Pos.CENTER);
            label.setTextAlignment(TextAlignment.CENTER);
            dialogVbox.getChildren().add(label);

			Button closeButton = new Button("Try Again");
			closeButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");

            closeButton.setOnAction(e->{
                dialog.close();
                App.model.reloadLevel();
            });
			dialogVbox.getChildren().add(closeButton);
			Scene dialogScene = new Scene(dialogVbox);
			dialog.setScene(dialogScene);

			dialog.show();

            dialog.setOnCloseRequest(e -> {
                App.model.getKeyInputManager().releaseAll();
                App.model.reloadLevel();

            });


        }


    }


    public Behavior copy() {
        return new ReloadLevelBehavior();
    }

}
