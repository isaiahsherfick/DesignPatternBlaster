package group1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import group1.model.level.Level;
import group1.model.level.LevelManager;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;

class LoadLevelTest 
{
	@Test
	void LoadLevelTest() 
	{
		App.resetModel();
		SpriteManager sm = App.model.getSpriteManager();
		LevelManager lm = App.model.getLevelManager();
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		for (int i =0 ;i < 25; i++)
		{
			sprites.add(new Sprite());
		}
		
		Level level1 = new Level(1, sprites);

		for (int i =0 ;i < 25; i++)
		{
			sprites.add(new Sprite());
		}

		Level level2 = new Level(2, sprites);
		
		lm.addLevel(level1);
		lm.addLevel(level2);
		
		
		assertEquals(0, sm.getNumberOfSprites());
		
		lm.loadNextLevel();
		assertEquals(25, sm.getNumberOfSprites());
		
		lm.loadNextLevel();
		assertEquals(50, sm.getNumberOfSprites());
	}
}
