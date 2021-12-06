package group1.model.sprite.behavior;

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.Map.Entry;
import group1.App;

import group1.model.sprite.Sprite;

public class StackFactoryBehavior implements Behavior
{
	private Stack<Sprite> spriteStack;
	
	public StackFactoryBehavior(Stack<Sprite> spriteStack)
	{
		this.spriteStack = spriteStack;
	}
	
	
	@Override
	public void performBehavior(Sprite sprite) 
	{
		try
		{
			App.model.addSprite(spriteStack.pop());
		}
		catch(EmptyStackException e)
		{
			
		}
	}
	@Override
	public Behavior copy() 
	{
		return new StackFactoryBehavior(spriteStack);
	}
}
