package com.xinlan.aidldemo.localservice;

import java.util.HashMap;
import java.util.Map;

import com.xinlan.aidldemo.IStudentQuery;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;

public class LocalService extends Service{
	private static final Map<String,String> maps = new HashMap<String,String>();
	
	@Override
	public void onCreate() {
		super.onCreate();
		maps.put("1", "苍井空");
		maps.put("2", "毛利兰");
		maps.put("3", "灰原哀");
		maps.put("4", "真宫寺樱");
	}
	
	private String query(String id){
		String name = maps.get(id);
		return TextUtils.isEmpty(name)?"查无此人":name;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new Query();
	}
	
	private final class Query extends Binder implements IStudentQuery{
		@Override
		public String queryById(String id) {
			return query(id);
		}
	}//end inner class
}//end class
