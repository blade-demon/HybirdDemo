package com.example.xuziwei416.hybirddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);
        MyAndroidWebViewClient webViewClient = new MyAndroidWebViewClient();

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        /**
         * Native调用H5方法
         *
         * 方法一：loadUrl
         *
         * 形式1：webView.loadUrl("javascript: alert('Android调用了callJS方法');");
         * 形式2：在loadUrl一个URL之后，在MyAndroidWebViewClient类中待pageFinished事件结束后再调用webView.loadUrl("javascript: callJS();");
         *
         * webView.loadUrl("https://oj14p4nz.codesandbox.io/");
         *
         *
         * 方法二：evaluatingJavaScript，注入JavaScript代码
         *
         * 这个⽅方法能够直接在⼀一次执⾏行行的时候获取到 JS 返回的结果。
         * 如果是使⽤用 loadUrl() 的⽅方式的话，执⾏行行完后对 客户端来说这句话就结束了了，
         * 如果想要拿到返回的结果的话另外需要 JS 调⽤用客户端的⽅方法返回。
         *
         * 下面的测试需要在MyAndroidWebViewClient类中待pageFinished事件结束后执行下面的代码，否则会返回null
         *         webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
         *                @Override
         *                   public void onReceiveValue(String value) {
         *                       // 这里回调的value是之前js代码之前的面返回的“Android调用了callJS方法”字符串
         *                       Log.d(TAG, "onReceiveValue: " + value);
         *                   }
         *               });
         */


        /**
         * H5调用Native
         * 方法之一：JS上下文注入
         *
         * addJavascriptInterface进行映射
         * 直接将⼀一个native对象(or函数)注⼊入到JS⾥里里⾯面，
         * 可以由web的js代码直接调⽤用，直接操作，
         * 可以在loadUrl之 前提前准备⼀一个对象，
         * 通过这个接⼝口注⼊入给JS上下⽂文，从⽽而让JS能够操作
         *
         *  //定义好 Java 接⼝口对象
         *  public class Bridge {
         *       @JavascriptInterface
         *      public void calllNative(String msg) {
         *      ...... }
         *  }
         *
         *  //注⼊入给JS上下⽂文
         *  mWebview.addJavascriptInterface(new Bridge(), "bridge"); //加载⻚页⾯面
         *  mWebview.loadUrl("www.test.com")
         *
         *  在⻚页⾯面加载之后,H5⻚页⾯面触发到
         *  window.bridge.calllNative("test");
         *
         */
        webView.loadUrl("https://oj14p4nz.codesandbox.io/");
        // WebAppInterface 作为接口供Web中的脚本调用
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        /**
         * H5调用Native方法之二：跳转请求拦截
         *
         * shouldOverrideUrlLoading
         */

        /**
         * H5调用Native方法之三：弹窗拦截
         */


    }
}
