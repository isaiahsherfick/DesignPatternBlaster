package group1.model;

import java.util.HashMap;
import java.util.Map;

import group1.App;
import javafx.scene.input.KeyCode;

public class KeyInputManager
{
	private Map<KeyCode, Boolean> keyPressedMap;
	
	public KeyInputManager()
	{
		keyPressedMap = new HashMap<>();
	}
	
	public void onKeyPress(KeyCode key)
	{

		keyPressedMap.put(key,true);
	}
	
	public void onKeyRelease(KeyCode key)
	{
		keyPressedMap.put(key,false);
	}
	
	public boolean isPressed(KeyCode key)
	{
		if (keyPressedMap.get(key) != null)
		{
			return keyPressedMap.get(key);
		}
		return false;
	}

	public void releaseAll(){
		keyPressedMap.clear();
	}
}