package api.payload;

import java.util.HashMap;

import org.json.JSONObject;

public class Headers {
	
	static HashMap<String,String> hm=new HashMap<String,String>();
	JSONObject jo = new JSONObject(hm);
	public static void main(String[] args)
	{
		
		hm.put("Content-Type","application/json");

	}
	
}
