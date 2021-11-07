package group1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import group1.model.GameCamera;
import group1.model.sprite.Sprite;

class GameCameraTest {

	@Test
	public void moveCameraTest() {
		Sprite playerSprite = new Sprite();
		GameCamera cam = new GameCamera(0,0);
		cam.setFocusSprite(playerSprite);
		cam.setMoving(true);
		double xEndPos = -500;
		while(cam.getXPos() > xEndPos) {
			playerSprite.setX(playerSprite.getX() + 5);
			cam.moveCamera();
		}
		assertEquals(cam.getXPos(),  xEndPos);		
	}
	
	@Test
	public void focusSpriteTest() {	
		GameCamera cam = new GameCamera(0,0);
		Sprite playerSprite = new Sprite();
		cam.setFocusSprite(playerSprite);
		Sprite testSprite = cam.getFocusSprite();
		assertEquals(testSprite, playerSprite);
	}
	
	@Test
	public void isMovingTest() {
		
		Sprite playerSprite = new Sprite();
		GameCamera cam = new GameCamera(0,0);
		cam.setFocusSprite(playerSprite);
		cam.setMoving(true);
		
		double xEndPos = -500;
		while(true) {
			playerSprite.setX(playerSprite.getX() + 5);
			cam.moveCamera();
			if(cam.getXPos() == xEndPos) {
				cam.setMoving(false);
				break;
			}
		}
		assertEquals(cam.isMoving(), false);	
	}
}
