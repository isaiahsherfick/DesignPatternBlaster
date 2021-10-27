package group1.megaman_dp.interfaces;

import org.json.simple.JSONObject;

public interface Saveable 
{
	public JSONObject save();
	public void load(JSONObject json);
}
