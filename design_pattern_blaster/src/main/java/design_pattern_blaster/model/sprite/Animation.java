/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package design_pattern_blaster.model.sprite;


import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Animation will contain arrays of Frames(Images) associated with state.
 * AnimationState RIGHT_MOVEMENT would be associated with a collection of sprites, each one representing
 * a frame in a walking animation
 */
public class Animation 
{

    public AnimationState animationState = AnimationState.IDLE;
    public Image spriteSheetImage;
    public HashMap<AnimationState, ArrayList<Image>> stateToAnimationLoop = new HashMap<>();

    public Animation(File spriteSheet){

        spriteSheetImage = new Image(spriteSheet.toURI().toString());
        stateToAnimationLoop.put(AnimationState.IDLE, getSpritesInRow(0));

    }

    public Animation() 
    {

	}

	public void setState(AnimationState animationState) {
        this.animationState = animationState;
    }

    public ArrayList<Image> getSpritesInRow(int row){

        ArrayList<Image> images = new ArrayList<>();

        int col =1;
        while(col*32 <= spriteSheetImage.getWidth()){
            Image sprite = new WritableImage(spriteSheetImage.getPixelReader(),row,col, 32,32 );
            images.add(sprite);
            col+=1;
        }

        return images;


    }
}
