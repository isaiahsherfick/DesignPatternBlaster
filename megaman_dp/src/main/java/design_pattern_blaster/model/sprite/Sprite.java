package design_pattern_blaster.model.sprite;

import org.json.simple.JSONObject;

import design_pattern_blaster.interfaces.Saveable;



public class Sprite implements Saveable
{
	private double x,y,xVelocity,yVelocity,width,height;
	private int layer;

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	private Animation animation;
	private int health;
	private boolean enabled;
	private Behavior spriteBehavior;

	public Sprite(int id){
		this.id = id;
		x=0; y=0; xVelocity = 0; yVelocity =0;
		width = 0; height =0;
		layer = 0;
	}


	private int id;

	public int getId() {
		return id;
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}


	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Behavior getSpriteBehavior() {
		return spriteBehavior;
	}

	public void setSpriteBehavior(Behavior spriteBehavior) {
		this.spriteBehavior = spriteBehavior;
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
}
