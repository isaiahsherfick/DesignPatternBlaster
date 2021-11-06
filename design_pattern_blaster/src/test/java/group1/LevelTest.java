package group1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import group1.model.level.Level;
import group1.model.level.Level1;
import group1.model.level.Level2;
import group1.model.sprite.Sprite;

public class LevelTest {
	
	@Test
	public void getLevelTest() {
		Level level1 = new Level1();
		assertTrue(level1.getLevel() instanceof Level);
		
		Level level2 = new Level2();
		assertTrue(level2.getLevel() instanceof Level);
	}
	
	@Test
	public void setLevelTest() {
		Level level = new Level2();
		level.setLevelNumber(2);
		assertEquals(level.getLevelNumber(),2);
	}
	
	@Test
	public void addSpriteToListTest() {
		Level level = new Level1(1);
		assertEquals(Arrays.asList(),level.getAllSpritesOnLevel());
		Sprite s = new Sprite();
		level.addSpriteToList(s);
		assertNotNull(level.getAllSpritesOnLevel());
	}
	
	@Test
	public void removeSpriteFromList() {
		Level level = new Level1(1);
		Sprite s = new Sprite();
		level.addSpriteToList(s);
		assertNotNull(level.getAllSpritesOnLevel());
		level.removeSpriteFromList(s);
		assertEquals(Collections.emptyList(), level.getAllSpritesOnLevel());
	}
	
	@Test
	public void setAllSpritesOnLevelTest() {
		Level level1 = new Level1(1);
		Sprite s1 = new Sprite();
		assertEquals(Collections.emptyList(), level1.getAllSpritesOnLevel());
		ArrayList<Sprite> list = new ArrayList<>();
		list.add(s1);
		level1.setAllSpritesOnLevel(list);
		assertNotNull(level1.getAllSpritesOnLevel());
	}
	
	

}
