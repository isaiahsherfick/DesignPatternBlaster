package design_pattern_blaster.interfaces;

import org.json.simple.JSONObject;

public interface Loadable extends Saveable
{
	public void load(JSONObject json);
}
