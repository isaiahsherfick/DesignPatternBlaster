package group1.factories;

import java.util.ArrayList;

import group1.model.level.Level;
import group1.model.sprite.Sprite;

public class LevelFactory 
{
	private LevelFactory() {}
	public static Level demoLevel1()
	{
		
		Sprite player = SpriteFactory.player();
		Sprite floor = SpriteFactory.demo_floor();

		Sprite enemy1 = SpriteFactory.demo_enemy_1();  
		Sprite enemy2 = SpriteFactory.demo_enemy_2();
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(player);
		sprites.add(floor);
		sprites.add(enemy1);
		sprites.add(enemy2);
		
		Level level1=new Level(1, sprites);
		
		return level1;
	}
	
	public static Level demoLevel2()
	{
		Level level2 = null;
		return level2;
	}
}
