/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors: Isaiah Sherfick
 * @EditedDate: 10/28/2021
 **/

package design_pattern_blaster.model.sprite.behavior;

import design_pattern_blaster.interfaces.Saveable;
import design_pattern_blaster.model.sprite.Sprite;

//Behaviors will be linked to events to prevent an explosion of classes
public interface Behavior extends Saveable
{
	//Executes the behavior on the sprite
    public void performBehavior(Sprite sprite);
}
