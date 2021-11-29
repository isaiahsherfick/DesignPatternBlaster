package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.factories.SpriteFactory;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.viewcontroller.ViewController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ViewBehavior implements Behavior {

    private final double secondsBetweenShots;

    private double timeRemaining = 30;
    private final double TIME_TO_SURVIVE = 60;

    private final static int BULLET_SPEED = -5;

    private double counter = 0.0;

    private Behavior shootBehavior;

    public ViewBehavior() {
        secondsBetweenShots = 1;

    }

    private Sprite restoreTimeRequest(){
        Sprite bullet = SpriteFactory.enemyBullet();
        bullet.setVelocityX(BULLET_SPEED);
        bullet.setVelocityY(0);
        bullet.setWidth(30);
        bullet.setColor(Color.DARKRED);
        Behavior restoreTimeBehavior = new Behavior() {
            @Override
            public void performBehavior(Sprite sprite) {
                timeRemaining = TIME_TO_SURVIVE;
                sprite.disable();
                //System.out.println(timeRemaining);
            }

            @Override
            public Behavior copy() {
                return null;
            }
        };
        bullet.addCustomCollision(SpriteClassIdConstants.WALL, restoreTimeBehavior);

        return bullet;

    }
    private Sprite increaseTimeRequest(){
        Sprite bullet = SpriteFactory.enemyBullet();
        bullet.setVelocityX(BULLET_SPEED * 1.25);
        bullet.setVelocityY(0);
        bullet.setWidth(30);
        bullet.setColor(Color.ORANGE);
        Behavior restoreTimeBehavior = new Behavior() {
            @Override
            public void performBehavior(Sprite sprite) {
                timeRemaining += 10;
                sprite.disable();
                //System.out.println(timeRemaining);
            }

            @Override
            public Behavior copy() {
                return null;
            }
        };
        bullet.addCustomCollision(SpriteClassIdConstants.WALL, restoreTimeBehavior);


        return bullet;

    }

    private Sprite decreaseTimeRequest(){
        Sprite bullet = SpriteFactory.enemyBullet();
        bullet.setVelocityX(BULLET_SPEED * .75);
        bullet.setVelocityY(0);
        bullet.setWidth(30);
        bullet.setColor(Color.LIME);
        /**
        Image avatar = new Image(Paths.get("src/main/resources/assets/avatar/0.2x/walk_left_frame2_0.2x.png").toUri().toString());
        ArrayList<Image> avatarAppearance = new ArrayList<>();
        avatarAppearance.add(avatar);
        bullet.getAnimation().setAnimationLoopForState(AnimationState.IDLE, avatarAppearance);
        bullet.getAnimation().setState(AnimationState.IDLE);
        System.out.println(bullet.getAnimation().getAnimationLoopForState(AnimationState.IDLE));
         **/
        Behavior restoreTimeBehavior = new Behavior() {
            @Override
            public void performBehavior(Sprite sprite) {
                timeRemaining -= 10;
                sprite.disable();
            }

            @Override
            public Behavior copy() {
                return null;
            }
        };
        bullet.addCustomCollision(SpriteClassIdConstants.WALL, restoreTimeBehavior);

        return bullet;
    }

    private Sprite randomRequest() {
        double randomNum = Math.random();

        //equal 1/3 chance for now
        if(randomNum<.333)
            return restoreTimeRequest();
        if(randomNum <= .6666667)
            return increaseTimeRequest();

        return decreaseTimeRequest();


    }


    public void setShootSpriteBehavior(Behavior ssb) {
      //  shootBehavior = new ShootSpriteBehavior(-60,100,increaseTimeRequest());
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    @Override
    public void performBehavior(Sprite sprite) {

        counter += App.model.getTimeDelta();
        timeRemaining -= App.model.getTimeDelta();
        double rounded = getRounded(timeRemaining);
        sprite.getAnimation().primeAnimationForStringDisplay("Time Left: " + rounded, 0, 50);
        if(timeRemaining <=0){
            sprite.disable();
        }
        if (counter > secondsBetweenShots) {
            shootBehavior = new ShootSpriteBehavior(60,getRandomNumber(50,Constants.WINDOW_HEIGHT-50),randomRequest());
            shootBehavior.performBehavior(sprite);
            counter = 0.0;
        }
    }

    private double getRounded(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double rounded = bd.doubleValue();
        return rounded;
    }

    @Override
    public Behavior copy() {
        return new ViewBehavior();
    }
}


