package utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import db.OpenHelper;

public class Contents extends Service {
	public static String app_version="1.0.0";
	 public static OpenHelper DBHelper;
	 public static OpenHelper getDBHelper()
	 {
		 return DBHelper;
	 }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	 

}
