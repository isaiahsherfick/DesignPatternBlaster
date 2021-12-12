package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;


public class PlayVideoBehavior implements Behavior {

    private String filepath;
    private boolean isPlaying = false;

    public PlayVideoBehavior(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void performBehavior(Sprite sprite) {

        if (!isPlaying) {
            isPlaying = true;

            Media media = new Media(new File(filepath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaPlayer.setAutoPlay(true);


            DoubleProperty mvw = mediaView.fitWidthProperty();
            DoubleProperty mvh = mediaView.fitHeightProperty();
            mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            mediaView.setPreserveRatio(true);
            App.model.getKeyInputManager().releaseAll();
            Stage mainStage = App.model.getMainStage();

            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(mainStage);
            Group root = new Group();
            root.getChildren().add(mediaView);

            Scene dialogScene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            dialog.setScene(dialogScene);

            dialog.show();


            dialog.setOnCloseRequest(e -> {
                App.model.getKeyInputManager().releaseAll();
                App.model.loadNextLevel();
                mediaPlayer.stop();

            });
        }

    }


    public Behavior copy() {
        return new PlayVideoBehavior(filepath);
    }

}
