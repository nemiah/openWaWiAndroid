package it.furtmeier.openwawiandroid;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PKWebView extends WebView {
	
	public PKWebView(OpenWaWiAndroid openWaWiAndroid) {
		super(openWaWiAndroid);
		final PKWebView view = this;
		final OpenWaWiAndroid activity = openWaWiAndroid;
		
		setKeepScreenOn(true);
		setWebViewClient(new PKViewClient());
		WebSettings webSettings = getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		webSettings.setDatabaseEnabled(true);
		webSettings.setDatabasePath("/data/data/"+getContext().getPackageName()+"/databases/");
		
		webSettings.setUserAgentString(webSettings.getUserAgentString() + " openWaWi Android/" + openWaWiAndroid.getVersionName());
		
		addJavascriptInterface(new JavaScriptHook(openWaWiAndroid), "android");
	    setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener(){
			public void onSystemUiVisibilityChange(int visibility){
				if(visibility == 0)
					activity.setMenuButtonVisibility(true);
				
				if (visibility != 0)
					return;
			
				Runnable rehideRunnable = new Runnable() {
					public void run() {
						activity.setMenuButtonVisibility(false);
						view.setSystemUiVisibility(SYSTEM_UI_FLAG_LOW_PROFILE);
					}
				};
				Handler rehideHandler = new Handler();
				rehideHandler.postDelayed(rehideRunnable, 2000);
			}
		});
	    
		setWebChromeClient(new WebChromeClient() {
			public boolean onConsoleMessage(ConsoleMessage cm) {
				Log.d("openWaWiWebView", cm.message() + " -- From line "+ cm.lineNumber() + " of "+ cm.sourceId() );
				return true;
			}
		});
	    
		setSystemUiVisibility(SYSTEM_UI_FLAG_LOW_PROFILE);
	}
}
