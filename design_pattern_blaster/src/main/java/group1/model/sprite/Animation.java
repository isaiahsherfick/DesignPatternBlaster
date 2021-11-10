/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package group1.model.sprite;


import group1.App;
import group1.interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Animation will contain arrays of Frames(Images) associated with state.
 * AnimationState RIGHT_MOVEMENT would be associated with a collection of sprites, each one representing
 * a frame in a walking animation
 */
public class Animation implements Drawable {

    public AnimationState animationState = AnimationState.IDLE;
    public HashMap<AnimationState, ArrayList<Image>> stateToAnimationLoop = new HashMap<>();
    private int frame;
    private double nextTimeToChangeFrames;
    private double loopPeriodSeconds = .25;

    public Animation() {
        stateToAnimationLoop = new HashMap<>();
        frame = 0;
    }

    public void setState(AnimationState animationState) {
        this.animationState = animationState;
    }

    public void setAnimationLoopForState(AnimationState animationState, ArrayList<Image> animationLoop) {
        stateToAnimationLoop.put(animationState, animationLoop);
    }

    public ArrayList<Image> getAnimationLoopForState(AnimationState animationState) {
        return stateToAnimationLoop.get(animationState);
    }

    public void setLoopPeriodSeconds(double loopPeriodSeconds) {
        this.loopPeriodSeconds = loopPeriodSeconds;
    }


//
//    /**
//     * Extracts all sprites in a given row from this Animation's spritesheet
//     * Used for extracting a series of "frames" that, when looped, serve as animation for some
//     * state like "IDLE, RIGHT_MOVEMENT, ETC..."
//     * Assumes all sprites in a sprite sheet are uniformly sized
//     * @param row the row to extract
//     * @param spriteWidth the width of a single tile (or single frame)
//     * @param spriteHeight the height of a single tile (or single frame)
//     * @return an array of spriteSheet.getWidth() / spriteWidth images, each of size spriteWidth * spriteHeight
//     */
//    private ArrayList<Image> getSpritesInRow(int row, int spriteWidth, int spriteHeight) {
//
//        ArrayList<Image> images = new ArrayList<>();
//
//        //represents how many pixels down from the top of image should we start extracting
//        final int ROW_PIXEL_COORD = spriteWidth * row;
//
//        //loop through row, extracting spriteSheet.getWidth() / spriteWidth images
//        int columnPixelCoord = 0;
//        while (columnPixelCoord<spriteSheetImage.getWidth()) {
//
//            Image sprite = new WritableImage(spriteSheetImage.getPixelReader(), columnPixelCoord,ROW_PIXEL_COORD,
//                    spriteWidth,spriteHeight );
//            images.add(sprite);
//
//            //next tile will begin extraction at spriteWidth pixels to the right
//            columnPixelCoord += spriteWidth;
//        }
//
//        return images;
//
//
//    }

    @Override
    public void draw(GraphicsContext g, Sprite sprite) {
        //This method "Draw" is being called every tick
        //we need to restrict that so that rather than a frame change every tick
        // the frame changes every x'th of a second
        // time delta represents the time since last frame
        // divinding it by
        if (stateToAnimationLoop.size() > 0) {
            ArrayList<Image> frames = stateToAnimationLoop.get(this.animationState);
            Image currentFrame = frames.get(frame % frames.size());
            if (frame == frames.size()) {
                frame = 0;
            } else {
                //restrict this increase to occur every loopPeriodSeconds by marking
                //relevant times to execute
                float timeElapsed = App.model.getTimeElapsed();
                if (timeElapsed > nextTimeToChangeFrames) {
                    nextTimeToChangeFrames = timeElapsed + loopPeriodSeconds;
                    frame++;
                }

            }
            g.drawImage(currentFrame, sprite.getX(), sprite.getY(), currentFrame.getWidth(), currentFrame.getHeight());
        } else {
            g.fillRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        }
    }

    //TODO this doesn't do the full copy yet - needs to copy over the map thing too
    public Animation copy() {
        Animation copy = new Animation();
        copy.setState(animationState);
        return copy;
    }

    @Override
    public void draw(GraphicsContext g) {
        // TODO Auto-generated method stub

    }
}


