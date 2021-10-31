package group1.model.sprite;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

import group1.constants.Constants;
import group1.interfaces.Drawable;
import group1.interfaces.Loadable;
import group1.model.collision.HitBox;
import javafx.scene.canvas.GraphicsContext;



public class Sprite implements Loadable, Drawable
{
	private double x,y,xVelocity,yVelocity,width,height;
	private int layer;
	private Animation animation;
	private int health;
	private boolean enabled;
	private HitBox hitBox;

	//List of the sprite's behaviors including ontick behaviors, onDeath, onCollision, onKeyPress, onTick, etc.
	//This will be iterated through and we will perform a check to see if the Behavior is of a specific subclass
	//Before calling the appropriate method call to cause the sprite to do what we want it to
	private List<EventBehavior> eventBehaviors;

	public Animation getAnimation() 
	{
		return animation;
	}

	public void setAnimation(Animation animation) 
	{
		this.animation = animation;
	}
	

	public Sprite()
	{
		eventBehaviors = new LinkedList<EventBehavior>();
		id = Constants.DEFAULT_SPRITE_ID;
		x=0; y=0; xVelocity = 0; yVelocity =0;
		width = 0; height =0;
		layer = 0;
		enabled = false;
	}
	
	//This is the sprite's "do" method
	//Pass it a GameEvent and it will perform the appropriate behaviors if it has any
	//which correspond to that event
	public void respondToEvent(GameEvent g)
	
	{
		Iterator<EventBehavior> eventIterator = eventBehaviors.iterator();
		while(eventIterator.hasNext())
		
		{
			eventIterator.next().executeIfEventMatches(g, this);
		}
	}


	private int id;

	public int getId() 
	{
		return id;
	}
	public double getX() 
	{
		return x;
	}

	public void setX(double x) 
	{
		this.x = x;
	}

	public double getY() 
	{
		return y;
	}

	public void setY(double y) 
	{
		this.y = y;
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
	}

	public double getHeight() 
	{
		return height;
	}

	public void setHeight(double height) 
	{
		this.height = height;
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

	public void setHitBox(HitBox hitBox) {
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

	public void setId(int newId) 
	{
		id = newId;
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}
}
