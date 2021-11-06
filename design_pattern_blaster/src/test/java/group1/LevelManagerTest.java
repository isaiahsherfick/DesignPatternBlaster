package group1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import group1.model.level.Level;
import group1.model.level.Level1;
import group1.model.level.LevelManager;
import group1.model.sprite.Sprite;

public class LevelManagerTest {
	
	@Test
	public void getLevelTest() {
		LevelManager levelManager = new LevelManager();
		assertEquals(Collections.emptyList(), levelManager.getCompletedLevelsList());
//		assertEquals(Collections.emptyList(), levelManager.getUnfinishedLevelsList());
		Level level = new Level1(1);
		Sprite s = new Sprite();
		level.addSpriteToList(s);
		levelManager.addLevelToUnfinishedLevelList(level);
		assertNotNull(levelManager.getLevel(1).getLevelNumber());
	}
	
	@Test
	public void getCompletedLevelsListTest() {
		LevelManager levelManager = new LevelManager();
		Level level = new Level1(1);
		Sprite s = new Sprite();
		level.addSpriteToList(s);
		levelManager.addLevelToCompletedLevelList(level);
		assertNotNull(levelManager.getCompletedLevelsList());
	}
	
	@Test
	public void getUnfinishedLevelsListTest() {
		LevelManager levelManager = new LevelManager();
//		Level level = new Level1(1);
//		Sprite s = new Sprite();
//		level.addSpriteToList(s);
//		levelManager.addLevelToUnfinishedLevelList(level);
		assertNotNull(levelManager.getUnfinishedLevelsList());
	}
	
	@Test
	public void addLevelToCompletedLevelListTest() {
		LevelManager levelManager = new LevelManager();
		Level level = new Level1(1);
		Sprite s = new Sprite();
		level.addSpriteToList(s);
//		levelManager.addLevelToUnfinishedLevelList(level);
		levelManager.addLevelToCompletedLevelList(level);
//		assertEquals(Collections.emptyList(), levelManager.getUnfinishedLevelsList());
		assertNotNull(levelManager.getCompletedLevelsList());
	}
	
	@Test
	public void addLevelToUnfinishedLevelListTest() {
		LevelManager levelManager = new LevelManager();
//		assertEquals(Collections.emptyList(), levelManager.getUnfinishedLevelsList());
//		Level level = new Level1(1);
//		Sprite s = new Sprite();
//		level.addSpriteToList(s);
//		levelManager.addLevelToUnfinishedLevelList(level);
//		levelManager.addLevelToUnfinishedLevelList(level);
		assertNotNull(levelManager.getUnfinishedLevelsList());
	}
	
	@Test
	public void removeLevelFromCompletedLevelListTest() {
		LevelManager levelManager = new LevelManager();
		assertEquals(Collections.emptyList(), levelManager.getCompletedLevelsList());
//		assertEquals(Collections.emptyList(), levelManager.getUnfinishedLevelsList());
		Level level = new Level1(1);
		Sprite s = new Sprite();
		level.addSpriteToList(s);
		levelManager.addLevelToCompletedLevelList(level);
		assertNotNull(levelManager.getCompletedLevelsList());
		levelManager.removeLevelFromCompletedLevelList(level);
		assertNotNull(levelManager.getUnfinishedLevelsList());
		assertEquals(Collections.emptyList(), levelManager.getCompletedLevelsList());
	}
	
	

}
