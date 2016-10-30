package it.furtmeier.openwawiandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;

public class Options {
	public static String url = "http://www.furtmeier.it/";
	public static int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
	
	public static void load(OpenWaWiAndroid openWaWiAndroid) {
		SharedPreferences pref = openWaWiAndroid.getPreferences(Activity.MODE_PRIVATE);
		url = pref.getString("url", url);
		orientation = pref.getInt("orientation", orientation);
	}

	public static void save(OpenWaWiAndroid openWaWiAndroid) {
		SharedPreferences pref = openWaWiAndroid.getPreferences(Activity.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString("url", url);
		editor.putInt("orientation", orientation);
		editor.commit();
	}
}
