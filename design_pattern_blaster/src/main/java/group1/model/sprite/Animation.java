/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package group1.model.sprite;


import group1.App;
import group1.constants.Constants;
import group1.interfaces.Drawable;
import group1.model.GameCamera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Animation will contain arrays of Frames(Images) associated with state.
 * AnimationState RIGHT_MOVEMENT would be associated with a collection of sprites, each one representing
 * a frame in a walking animation
 */
public class Animation implements Drawable {

    public AnimationState animationState = AnimationState.IDLE;
    public AnimationState previousAnimationState = AnimationState.IDLE;
    public HashMap<AnimationState, ArrayList<Image>> stateToAnimationLoop = new HashMap<>();
    private int frame;
    private double nextTimeToChangeFrames;
    private double loopPeriodSeconds = .25;
    private Font HUDFont;

    public Animation() {
        stateToAnimationLoop = new HashMap<>();
        frame = 0;

        try {
            HUDFont = Font.loadFont(new FileInputStream("src/main/resources/group1/retro.ttf"), 72);
        } catch (FileNotFoundException e) {
            HUDFont = Font.getDefault();
        }
    }

    public void setState(AnimationState animationState) {
    	if(this.animationState!=AnimationState.IDLE) {
    		previousAnimationState = this.animationState;
    	}
        this.animationState = animationState;
    }

    public AnimationState getState()
    {
        return this.animationState;
    }

    public void setPreviousState(AnimationState previousAnimationState) {
    	this.previousAnimationState = previousAnimationState;
    }

    public void setAnimationLoopForState(AnimationState animationState, ArrayList<Image> animationLoop) {
        stateToAnimationLoop.put(animationState, animationLoop);
    }

    public ArrayList<Image> getAnimationLoopForState(AnimationState animationState) {
    	if(stateToAnimationLoop.containsKey(animationState)) {
    		return stateToAnimationLoop.get(animationState);
    	}
    	if(animationState == AnimationState.IDLE) {
    		ArrayList<Image> prevLoop = stateToAnimationLoop.get(previousAnimationState);
    		ArrayList<Image> idleStateLoop = new ArrayList<Image>();
    		idleStateLoop.add(prevLoop.get(0));
    		return idleStateLoop;
    	}
    	return new ArrayList<Image>();
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

    public void drawHUD(GraphicsContext g, Sprite player){
        g.setFont(HUDFont);
        g.setFill(Color.RED);
        double xPos = -App.model.getGameCamera().getXPos();
//        double xPos = player.getX() - Constants.WINDOW_WIDTH/2 + 10;
        g.fillText("HEALTH: " + player.getHealth(), xPos, 50);
        g.fillText("Level: " + App.model.getCurrentLevel().getLevelNumber(), xPos,100);

    }

    @Override
    public void draw(GraphicsContext g, Sprite sprite) {
        //This method "Draw" is being called every tick
        //we need to restrict that so that rather than a frame change every tick
        // the frame changes every x'th of a second
        // time delta represents the time since last frame
        // divinding it by

        if (App.model.getCurrentLevel().getFocusSprite().getSpriteId() == sprite.getSpriteId()){
            drawHUD(g,sprite);
        }

        if(stateToAnimationLoop.size() > 0) {
        	 ArrayList<Image> frames = getAnimationLoopForState(this.animationState);
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


