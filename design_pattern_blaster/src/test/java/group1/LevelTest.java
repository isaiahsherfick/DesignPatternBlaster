package group1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import group1.model.level.Level;
import group1.model.level.Level;
import group1.model.level.Level;
import group1.model.sprite.Sprite;

public class LevelTest 
{
	
	@Test
	public void setLevelTest() 
	{
		Level level = new Level();
		level.setLevelNumber(2);
		assertEquals(level.getLevelNumber(),2);
	}
	
	@Test
	public void addSpriteToListTest() 
	{
		Level level = new Level(1);
		assertEquals(Arrays.asList(),level.getAllSpritesOnLevel());
		Sprite s = new Sprite();
		level.addSprite(s);
		assertNotNull(level.getAllSpritesOnLevel());
	}
	
	@Test
	public void removeSpriteFromList() 
	{
		Level level = new Level(1);
		Sprite s = new Sprite();
		level.addSprite(s);
		assertNotNull(level.getAllSpritesOnLevel());
		level.removeSprite(s);
		assertEquals(Collections.emptyList(), level.getAllSpritesOnLevel());
	}
}
