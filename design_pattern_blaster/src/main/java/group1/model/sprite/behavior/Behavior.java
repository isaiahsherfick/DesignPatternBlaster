/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors: Isaiah Sherfick
 * @EditedDate: 10/28/2021
 **/

package group1.model.sprite.behavior;
import group1.model.sprite.Sprite;

//Behaviors will be linked to events to prevent an explosion of classes
public interface Behavior 
{
	//Executes the behavior on the sprite
    public void performBehavior(Sprite sprite);
    
    //Clone method for behaviors
    public Behavior copy();
}
