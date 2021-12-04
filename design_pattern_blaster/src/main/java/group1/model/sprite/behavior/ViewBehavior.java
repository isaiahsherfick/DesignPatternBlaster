package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.factories.SpriteFactory;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ViewBehavior implements Behavior {

    private final double secondsBetweenShots;

    private double health = 100;
    private final double MAX_HEALTH = 100;

    private final static int BULLET_SPEED = -5;
    private HashMap<Integer,ArrayList<Image>> healthToSpriteMap;

    private double counter = 0.0;
    private double timeRemaining = 30;

    private Behavior shootBehavior;



    public ViewBehavior() {
        secondsBetweenShots = 1;
        healthToSpriteMap = new HashMap<>();
        for (int i = 0; i<=100; i+=10){
            String filePath = "src/main/resources/assets/MVC/MVCRequestSender " + i + ".png";
            Image avatar = new Image(Paths.get(filePath).toUri().toString());
            ArrayList<Image> avatarAppearance = new ArrayList<>(List.of(avatar));
            healthToSpriteMap.put(i,avatarAppearance);
        }


    }

    private Sprite restoreHealthRequest(){
        Sprite bullet = SpriteFactory.enemyBullet();
        bullet.setVelocityX(BULLET_SPEED);
        bullet.setVelocityY(0);
        bullet.setWidth(30);
        bullet.setColor(Color.DARKRED);
        bullet.setDefaultCollisionBehavior(new DoNothingBehavior());

        Behavior restoreTimeBehavior = new Behavior() {
            @Override
            public void performBehavior(Sprite sprite) {
                health = MAX_HEALTH;
                sprite.disable();
                //System.out.println(timeRemaining);
            }

            @Override
            public Behavior copy() {
                return null;
            }
        };
        bullet.addCustomCollision(SpriteClassIdConstants.WALL, restoreTimeBehavior);
        bullet.addCustomCollision(SpriteClassIdConstants.PLAYER, new DisableBehavior());

        return bullet;

    }
    private Sprite increaseHealthRequest(){
        Sprite bullet = SpriteFactory.enemyBullet();
        bullet.setVelocityX(BULLET_SPEED * 1.25);
        bullet.setVelocityY(0);
        bullet.setWidth(30);
        bullet.setColor(Color.ORANGE);
        bullet.setDefaultCollisionBehavior(new DoNothingBehavior());

        Behavior restoreTimeBehavior = new Behavior() {
            @Override
            public void performBehavior(Sprite sprite) {
                if(health < 100)
                    health+=100;
                sprite.disable();
                //System.out.println(timeRemaining);
            }

            @Override
            public Behavior copy() {
                return null;
            }
        };
        bullet.addCustomCollision(SpriteClassIdConstants.WALL, restoreTimeBehavior);
        bullet.addCustomCollision(SpriteClassIdConstants.PLAYER, new DisableBehavior());


        return bullet;

    }

    private Sprite decreaseHealthRequest(){
        Sprite bullet = SpriteFactory.enemyBullet();
        bullet.setVelocityX(BULLET_SPEED * .75);
        bullet.setVelocityY(0);
        bullet.setWidth(30);
        bullet.setColor(Color.LIME);
        bullet.setDefaultCollisionBehavior(new DoNothingBehavior());
        bullet.addCustomCollision(SpriteClassIdConstants.PLAYER, new DisableBehavior());

        Image avatar = new Image(Paths.get("src/main/resources/assets/avatar/0.2x/walk_left_frame2_0.2x.png").toUri().toString());
        ArrayList<Image> avatarAppearance = new ArrayList<>();
        avatarAppearance.add(avatar);
        bullet.getAnimation().setAnimationLoopForState(AnimationState.IDLE, avatarAppearance);
        bullet.getAnimation().setState(AnimationState.IDLE);
        System.out.println(bullet.getAnimation().getAnimationLoopForState(AnimationState.IDLE));

        Behavior restoreTimeBehavior = new Behavior() {
            @Override
            public void performBehavior(Sprite sprite) {
                health -= 10;
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
            return restoreHealthRequest();
        if(randomNum <= .6666667)
            return increaseHealthRequest();

        return decreaseHealthRequest();


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

        ArrayList<Image> avatarAppearance = healthToSpriteMap.get((int) Math.ceil(health));
        sprite.getAnimation().setAnimationLoopForState(AnimationState.IDLE, avatarAppearance);
        sprite.getAnimation().setState(AnimationState.IDLE);


        if(health <=0){
            sprite.disable();
        }
        if (timeRemaining <=0){
            new ReloadLevelBehavior().performBehavior(sprite);
        }
        if (counter > secondsBetweenShots) {
            shootBehavior = new ShootSpriteBehavior(60,getRandomNumber(50,Constants.WINDOW_HEIGHT-100),randomRequest());
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


