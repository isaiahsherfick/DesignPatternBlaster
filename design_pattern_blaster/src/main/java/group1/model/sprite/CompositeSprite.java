package group1.model.sprite;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class CompositeSprite extends Sprite
{
	CompositeSprite left;
	CompositeSprite right;

	public CompositeSprite()
	{
		super();
	}

	@Override
	public void draw(GraphicsContext g)
	{
		if(left!=null)
			left.draw(g);
		if(right!=null)
			right.draw(g);
		if (isEnabled())
		{
			g.setFill(getColor());
			getAnimation().draw(g, this);
		}
	}

	public void setLeft(CompositeSprite left)
	{
		this.left = left;
	}

	public void setRight(CompositeSprite right)
	{
		this.right = right;
	}

	@Override
	public void disable()
	{
		if(left != null)
			left.disable();
		if(right != null)
			right.disable();
		setEnabled(false);
	}

	public CompositeSprite copy()
	{
		CompositeSprite copy = new CompositeSprite();
		copy.setX(getX());
		copy.setY(getY());
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

	public ArrayList<Sprite> getChildren() {
		ArrayList<Sprite> csc = new ArrayList<>();
		csc.add(this);
		if(left!=null)
		{
			csc.addAll(left.getChildren());
		}
		if(right!=null)
			csc.addAll(right.getChildren());
		return csc;

 	}

	public int getDepth() {
		if(!isEnabled())
			return 0;
		// Case for incomplete tree is not handled because the tree is hardcoded
		if((left==null && right == null)||(!left.isEnabled() && !right.isEnabled()))
			return 1;
		return Math.max(left.getDepth(), right.getDepth());
 	}
}
