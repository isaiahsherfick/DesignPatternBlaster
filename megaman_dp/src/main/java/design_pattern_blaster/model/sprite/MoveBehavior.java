/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package design_pattern_blaster.model.sprite;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;

public class MoveBehavior extends Behavior {


    public MoveBehavior(Sprite behaviorOwner) {
        super(behaviorOwner);
    }

    @Override
    public void onTick(){


        InputManagerUtility.respondToInput(KeyEvent.KEY_PRESSED, e->{
                if(((KeyEvent) e).getCode() == KeyCode.D){
                    //Factor in deltaTime
                    sprite.setX(sprite.getX() + sprite.getxVelocity());
                    sprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);
                }else if(((KeyEvent) e).getCode() == KeyCode.A){
                    sprite.setX(sprite.getX() - sprite.getxVelocity());
                    sprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);
                }
        });

    }
    @Override
    public void onCollision(Sprite other){

    }


}
