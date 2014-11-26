package jp.tsuchiyasoft.khmerbible;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class HelpActivity extends Activity {

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
        webView = (WebView)findViewById(R.id.webViewMain);
        //webView.getSettings().setJavaScriptEnabled(true);
        // 画面一杯に表示
        webView .getSettings().setLoadWithOverviewMode(true);  
        webView .getSettings().setUseWideViewPort(true);
        // スクロールバーを内側に表示させる
        // webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // 読み込んだWebページをWebView上で拡大・縮小（ピンチイン・アウト）可能に
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(1);
        webView.loadUrl("file:///android_asset/copyright.html");
		
	}
}
