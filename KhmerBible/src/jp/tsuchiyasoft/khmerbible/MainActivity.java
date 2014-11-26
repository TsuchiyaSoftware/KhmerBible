package jp.tsuchiyasoft.khmerbible;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;


public class MainActivity extends ActionBarActivity {

	WebView webView;
	final int REQUEST_CODE_PREFERENCES = 0;
	
    @SuppressLint("SetJavaScriptEnabled") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webViewMain);
        //固定フォントサイズを指定する
        webView.getSettings().setDefaultFontSize(20);
        // JavaScriptを有効にする
        webView.getSettings().setJavaScriptEnabled(true);
        // 画面一杯に表示
        webView.getSettings().setLoadWithOverviewMode(true);  
        webView.getSettings().setUseWideViewPort(true);
        // スクロールバーを内側に表示させる
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // 読み込んだWebページをWebView上で拡大・縮小（ピンチイン・アウト）可能に
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(1);
        webView.loadUrl("file:///android_asset/index.html");

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);      
            startActivityForResult(intent, REQUEST_CODE_PREFERENCES);
            return true;
        }
        if (id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);      
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PREFERENCES) {
     
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            
            int fontSize = pref.getInt(SettingsActivity.KEY_FONT_SIZE, 20);
            alert("fontSize =" + String.valueOf(fontSize));
        }
    }
    
    protected void alert(String message) {
    	AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
    	 
        // ダイアログの設定
        alertDialog.setTitle("title");          //タイトル
        alertDialog.setMessage(message);      //内容
        alertDialog.setIcon(R.drawable.ic_launcher);   //アイコン設定
     
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
     
            public void onClick(DialogInterface dialog, int which) {
                // TODO 自動生成されたメソッド・スタブ
                Log.d("AlertDialog", "Positive which :" + which);
            }
        });
     
        // ダイアログの作成と表示
        alertDialog.create();
        alertDialog.show();    	
    }

    // backキーで終了ではなく前の画面に戻る
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
