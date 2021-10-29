package tests;

import de.saxsys.javafx.test.JfxRunner;
import design_pattern_blaster.model.sprite.Animation;
import design_pattern_blaster.model.sprite.AnimationState;
import javafx.scene.image.Image;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class AnimationTest {

    @Test
    public void imageLoading(){
        File spriteSheetFile = new File("src/main/resources/design_pattern_blaster/sample spritesheet.png");

        Animation animation = new Animation(spriteSheetFile);
        HashMap<AnimationState, ArrayList<Image>> stateToAnimationLoop = animation.stateToAnimationLoop;
        stateToAnimationLoop.size();



    }

}