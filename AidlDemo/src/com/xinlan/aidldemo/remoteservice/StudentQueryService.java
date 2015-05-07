package com.xinlan.aidldemo.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class StudentQueryService extends Service {
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}//end class
