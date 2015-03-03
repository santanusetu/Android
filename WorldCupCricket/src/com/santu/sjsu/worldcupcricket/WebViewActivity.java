package com.santu.sjsu.worldcupcricket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

	private WebView webView;
	String link;

	// private AdView mAdView;

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_activity);

		link = getIntent().getExtras().getString("url");
		webView = (WebView) findViewById(R.id.webView1);
		startWebView(link);

	}

	@SuppressLint("SetJavaScriptEnabled")
	private void startWebView(String url)
	{

		// Create new webview Client to show progress dialog
		// When opening a url or click on link

		webView.setWebViewClient(new WebViewClient()
		{
			ProgressDialog progressDialog;

			// If you will not use this method url links are opeen in new brower
			// not in webview
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			// Show loader on url load
			public void onLoadResource(WebView view, String url) {
				if (progressDialog == null) {
					// in standard case YourActivity.this
					progressDialog = new ProgressDialog(WebViewActivity.this);
					progressDialog.setMessage("Content Loading...");
					progressDialog.show();
				}
			}

			public void onPageFinished(WebView view, String url) {
				try {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
						progressDialog = null;
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

		});

		// Javascript inabled on webview
		webView.getSettings().setJavaScriptEnabled(true);

		// Load url in webview
		webView.loadUrl(url);

	}

	// Open previous opened link from history on webview when back button
	// pressed

	@Override
	// Detect when the back button is pressed
	public void onBackPressed() {
		if (webView.canGoBack()) {
			webView.goBack();
			webView.clearCache(true);
			this.deleteDatabase("webview.db");
			this.deleteDatabase("webviewCache.db");
			CookieSyncManager.createInstance(this);
			CookieManager cookieManager = CookieManager.getInstance();
			cookieManager.removeAllCookie();
		} else {
			// Let the system handle the back button
			super.onBackPressed();

		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}