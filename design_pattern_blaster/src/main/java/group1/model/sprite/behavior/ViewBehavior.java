package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.factories.SpriteFactory;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
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
        if(timeRemaining <=0){
            sprite.disable();
        }
        if (counter > secondsBetweenShots) {
            shootBehavior = new ShootSpriteBehavior(60,getRandomNumber(50,Constants.WINDOW_HEIGHT-50),randomRequest());
            shootBehavior.performBehavior(sprite);
            counter = 0.0;
        }
    }

    @Override
    public Behavior copy() {
        return new ViewBehavior();
    }
}


