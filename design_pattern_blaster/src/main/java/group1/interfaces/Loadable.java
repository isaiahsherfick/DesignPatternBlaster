package group1.interfaces;

import org.json.simple.JSONObject;

public interface Loadable extends Saveable
{
	public void load(JSONObject json);
}
