package it.furtmeier.openwawiandroid;

import jim.h.common.android.lib.zxing.CaptureActivity;
import jim.h.common.android.lib.zxing.integrator.IntentIntegrator;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class JavaScriptHook {
	private OpenWaWiAndroid openWaWiAndroid;
	
	public JavaScriptHook(OpenWaWiAndroid openWaWiAndroid) {
		this.openWaWiAndroid = openWaWiAndroid;
	}
	
	@JavascriptInterface
	public void scan() {
		try {
	        Intent intent = new Intent(openWaWiAndroid, CaptureActivity.class);
	        openWaWiAndroid.startActivityForResult(intent, IntentIntegrator.REQUEST_CODE);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
