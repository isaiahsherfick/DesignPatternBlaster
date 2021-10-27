package design_pattern_blaster.interfaces;

import org.json.simple.JSONObject;

public interface Saveable 
{
	public JSONObject save();
	public void load(JSONObject json);
}
