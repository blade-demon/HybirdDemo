package com.example.xuziwei416.hybirddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.webView);

        webView.setWebViewClient(new MyAndroidWebViewClient());

        webView.loadUrl("https://6n2jqv0pwz.codesandbox.io/");
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
}
