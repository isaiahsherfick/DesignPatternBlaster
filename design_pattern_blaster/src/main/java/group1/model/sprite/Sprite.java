package group1.model.sprite;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

import group1.constants.Constants;
import group1.interfaces.Drawable;
import group1.interfaces.Loadable;
import group1.model.collision.HitBox;
import group1.model.sprite.behavior.Behavior;
import group1.model.sprite.behavior.DoNothingBehavior;
import group1.model.sprite.behavior.MoveBehavior;
import group1.model.sprite.behavior.ObservableBehavior;
import group1.model.sprite.behavior.ObserverBehavior;
import group1.model.sprite.behavior.ObserverBehaviorI;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class Sprite implements Loadable
{
	protected double x,y,xVelocity,yVelocity,width,height;

	protected int direction;

	//spriteId is a POSITIVE constant assigned at runtime by the spriteManager or hard coded before inserting the sprite into the manager
	private int spriteId;

	//SpriteClassId is a NEGATIVE constant defined in SpriteClassIdConstants.java
	//They encapsulate what kind of thing the sprite represents, like a bullet or an enemy
	//Used to generalize the collision behaviors
	protected int spriteClassId;

	//Layer is an integer assigned at runtime to distinguish what order sprites are drawn in and what other sprites the sprite can collide with
	protected int layer;

	//Animation contains a list of frames with specific IDs that Behaviors can cause to be displayed
	protected Animation animation;

	//Health is the amount of health the sprite has.
	protected int health;

	//Sprite will only be drawn / collidable if enabled is true. False by default, enabled by camera when the camera detects the sprite in the frame
	protected boolean enabled;

	//Hitbox object for collision
	protected HitBox hitBox;

	//HashMap for collisions. Custom collisions can be added for classes of sprites (negative values) or specific sprites (positive values).
	protected CustomCollisionMap customCollisionMap;

	//List of the sprite's behaviors including ontick behaviors, onDeath, onCollision, onKeyPress, onTick, etc.
	//This will be iterated through and we will perform a check to see if the Behavior is of a specific subclass
	//Before calling the appropriate method call to cause the sprite to do what we want it to
	protected List<EventBehavior> eventBehaviors;

	//Color - only used when the sprite is drawn using a primitive JFX shape, will eventually be deprecated
	protected Color color;
	
	protected boolean alive;
	
	protected boolean visible;

	public Animation getAnimation()
	{
		return animation;
	}

	public void setAnimation(Animation animation)
	{
		this.animation = animation;
	}
	
	public boolean containsObserverBehavior()
	{
		for (EventBehavior e : eventBehaviors)
		{
			if (e.getBehavior() instanceof ObserverBehaviorI)
				return true;
		}
		return false;
	}
	
	public boolean containsObservableBehavior()
	{
		for (EventBehavior e : eventBehaviors)
		{
			if (e.getBehavior() instanceof ObservableBehavior)
				return true;
		}
		return false;
	}
	
	public Behavior getObserverBehavior()
	{
		for (EventBehavior e : eventBehaviors)
		{
			if (e.getBehavior() instanceof ObserverBehaviorI)
				return e.getBehavior();
		}
		return new DoNothingBehavior();
	}
	
	public Behavior getObservableBehavior()
	{
		for (EventBehavior e : eventBehaviors)
		{
			if (e.getBehavior() instanceof ObservableBehavior)
				return e.getBehavior();
		}
		return new DoNothingBehavior();
	}


	public Sprite()
	{
		animation = new Animation();
		eventBehaviors = new LinkedList<EventBehavior>();
		customCollisionMap = new CustomCollisionMap();
		spriteId = Constants.DEFAULT_SPRITE_ID;
		hitBox = new HitBox(this); //TODO this is that circular thing we don't want, refactor
		spriteClassId = SpriteClassIdConstants.DEFAULT_SPRITE_CLASS;
		x=Constants.DEFAULT_SPRITE_X; y=Constants.DEFAULT_SPRITE_Y; xVelocity = Constants.DEFAULT_SPRITE_DX; yVelocity =Constants.DEFAULT_SPRITE_DY;
		width = Constants.DEFAULT_SPRITE_WIDTH; height =Constants.DEFAULT_SPRITE_HEIGHT;
		layer = 0;
		direction = Constants.LEFT;
		enabled = true;
		alive = false;
		visible = false;
		color = Color.RED;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public int getDirection()
	{
		return direction;
	}

	public void setDirection(int newDirection)
	{
		direction = newDirection;
	}

	//This is the sprite's "do" method
	//Pass it a GameEvent and it will perform the appropriate behaviors if it has any
	//which correspond to that event
	public void respondToEvent(GameEvent g)

	{
        if (enabled)
        {
            Iterator<EventBehavior> eventIterator = eventBehaviors.iterator();
            while(eventIterator.hasNext())
            {
                eventIterator.next().executeIfEventMatches(g, this);
            }
        }
	}

	public int getSpriteClassId()
	{
		return spriteClassId;
	}

	public void setSpriteClassId(int id)
	{
		spriteClassId = id;
	}

	public int getSpriteId()
	{
		return spriteId;
	}
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
		hitBox.setX(x);
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
		hitBox.setY(y);
	}

	public double getVelocityX()
	{
		return xVelocity;
	}

	public void setVelocityX(double xVelocity)
	{
		this.xVelocity = xVelocity;
	}

	public double getVelocityY()
	{
		return yVelocity;
	}

	public void setVelocityY(double yVelocity)
	{
		this.yVelocity = yVelocity;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
		hitBox.setWidth(width);
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
		hitBox.setHeight(height);
	}

	public int getLayer()
	{
		return layer;
	}

	public void setLayer(int layer)
	{
		this.layer = layer;
	}


	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public boolean getEnabled()
	{
		return enabled;
	}

	public void enable()
	{
		setEnabled(true);
	}

	public void disable()
	{
		setEnabled(false);
	}

	public List<EventBehavior> getEventBehaviors()
	{
		return eventBehaviors;
	}

	public void addEventBehavior(EventBehavior newEventBehavior)

	{
		eventBehaviors.add(newEventBehavior);
	}

	public HitBox getHitBox() {
		return hitBox;
	}

	public void setHitBox(HitBox hitBox)
	{
		this.hitBox = hitBox;
	}

	@Override
	public JSONObject save()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(JSONObject json)
	{
		// TODO Auto-generated method stub
	}

	public void setSpriteId(int newId)
	{
		spriteId = newId;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void draw(GraphicsContext g)
	{
		if (enabled)
		{
			g.setFill(color);
			animation.draw(g, this);
		}
	}

	public void collideWith(Sprite spriteToCheck)
	{
		customCollisionMap.handleCollision(this,spriteToCheck);
	}

	public void setDefaultCollisionBehavior(Behavior newDefault)
	{
		customCollisionMap.setDefaultCollisionBehavior(newDefault);
	}

	public Sprite copy()
	{
		Sprite copy = new Sprite();
		copy.setX(x);
		copy.setY(y);
		copy.setVelocityX(xVelocity);
		copy.setVelocityY(yVelocity);
		copy.setWidth(width);
		copy.setHeight(height);
		copy.setDirection(direction);
		copy.setSpriteClassId(spriteClassId);
		copy.setLayer(layer);
		copy.setAnimation(animation.copy());
		copy.setHealth(health);
		copy.setEnabled(enabled);
		copy.setHitBox(hitBox.copy());
		copy.setCustomCollisionMap(customCollisionMap.copy());
		for (EventBehavior eb : eventBehaviors)
		{
			copy.addEventBehavior(eb.copy());
		}
		copy.setColor(color);
		return copy;
	}

	protected void setCustomCollisionMap(CustomCollisionMap ccm)
	{
		customCollisionMap = ccm;
	}

	public CustomCollisionMap getCustomCollisionMap() {
		return customCollisionMap;
	}

	public void turnTowards(Sprite otherSprite)
	{
			//Turn towards the other sprite
			if (otherSprite.getX() > x)
            {
				setDirection(Constants.RIGHT);
            }
			else
            {
				setDirection(Constants.LEFT);
            }
	}

	public void addCustomCollision(int id, Behavior behavior)
	{
		customCollisionMap.addCustomCollision(id, behavior);
	}
}
