package servAction;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
	Map map=defaultMap();
	
	public Action create(String action_name){
		Class cl=(Class) map.get(action_name);
		if (cl == null)
            throw new RuntimeException();
       Action actionInstance = null;
       try {
            actionInstance = (Action)cl.newInstance();
       } catch (Exception e) {
            e.printStackTrace();
       }

		return actionInstance;
	}
	protected Map defaultMap() {
		Map map=new HashMap();
		map.put("addpub", AddPub.class);
		map.put("addsub", AddSub.class);
		map.put("adduser", AddUser.class);
		map.put("deletepub", DeletePub.class);
		map.put("deletesub", DeleteSub.class);
		map.put("login", LoginUser.class);
		map.put("logout", LogOut.class);
		map.put("paysub", PaySub.class);
		return map;
	}
}
