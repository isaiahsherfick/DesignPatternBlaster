/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package design_pattern_blaster.model.sprite;

public abstract class Behavior {

    protected Sprite sprite;
    public Behavior(Sprite behaviorOwner){
        this.sprite = behaviorOwner;
    }


    public void onTick(){

    }
    public void onCollision(Sprite other){

    }




}
