/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package group1.model.sprite;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import group1.interfaces.Drawable;

/**
 * Animation will contain arrays of Frames(Images) associated with state.
 * AnimationState RIGHT_MOVEMENT would be associated with a collection of sprites, each one representing
 * a frame in a walking animation
 */
public class Animation implements Drawable
{

    public AnimationState animationState = AnimationState.IDLE;
    public Image spriteSheetImage;
    public HashMap<AnimationState, ArrayList<Image>> stateToAnimationLoop = new HashMap<>();

    public Animation(File spriteSheet){

        spriteSheetImage = new Image(spriteSheet.toURI().toString());
        stateToAnimationLoop.put(AnimationState.IDLE, getSpritesInRow(0,32,32));

    }

    public Animation(){

    }

	public void setState(AnimationState animationState) {
        this.animationState = animationState;
    }


    /**
     * Extracts all sprites in a given row from this Animation's spritesheet
     * Used for extracting a series of "frames" that, when looped, serve as animation for some
     * state like "IDLE, RIGHT_MOVEMENT, ETC..."
     * Assumes all sprites in a sprite sheet are uniformly sized
     * @param row the row to extract
     * @param spriteWidth the width of a single tile (or single frame)
     * @param spriteHeight the height of a single tile (or single frame)
     * @return an array of spriteSheet.getWidth() / spriteWidth images, each of size spriteWidth * spriteHeight
     */
    private ArrayList<Image> getSpritesInRow(int row, int spriteWidth, int spriteHeight) {

        ArrayList<Image> images = new ArrayList<>();

        //represents how many pixels down from the top of image should we start extracting
        final int ROW_PIXEL_COORD = spriteWidth * row;

        //loop through row, extracting spriteSheet.getWidth() / spriteWidth images
        int columnPixelCoord = 0;
        while (columnPixelCoord<spriteSheetImage.getWidth()) {

            Image sprite = new WritableImage(spriteSheetImage.getPixelReader(), columnPixelCoord,ROW_PIXEL_COORD,
                    spriteWidth,spriteHeight );
            images.add(sprite);

            //next tile will begin extraction at spriteWidth pixels to the right
            columnPixelCoord += spriteWidth;
        }

        return images;


    }

	@Override
	public void draw(GraphicsContext g) 
	{
		// TODO Auto-generated method stub
	}
}


