package com.example.xuziwei416.hybirddemo;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by xuziwei416 on 2019/5/6.
 */

public class MyAndroidWebViewClient extends WebViewClient {
    public MyAndroidWebViewClient() {
        super();
    }

    /**
     * Give the host application a chance to take over the control when a new
     * url is about to be loaded in the current WebView. If WebViewClient is not
     * provided, by default WebView will ask Activity Manager to choose the
     * proper handler for the url. If WebViewClient is provided, return true
     * means the host application handles the url, while return false means the
     * current WebView handles the url.
     * <p>
     * <p>Notes:
     * <ul>
     * <li>This method is not called for requests using the POST &quot;method&quot;.</li>
     * <li>This method is also called for subframes with non-http schemes, thus it is
     * strongly disadvised to unconditionally call {@link WebView#loadUrl(String)}
     * with the request's url from inside the method and then return true,
     * as this will make WebView to attempt loading a non-http url, and thus fail.</li>
     * </ul>
     * </p>
     *
     * @param view    The WebView that is initiating the callback.
     * @param request Object containing the details of the request.
     * @return True if the host application wants to leave the current WebView
     * and handle the url itself, otherwise return false.
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        // 拦截判断是否允许跳转链接，域名中是否包含lu.com
        if(!request.getUrl().getHost().contains("lu.com")) {
            // 不允许，不跳转
            Log.d(TAG, "shouldOverrideUrlLoading should failed!");
            Toast.makeText(view.getContext(), "不允许跳转到该链接", Toast.LENGTH_SHORT).show();
            return true;
        }
        // 允许，跳转链接
        Log.d(TAG, "shouldOverrideUrlLoading should success!");
        return super.shouldOverrideUrlLoading(view, request);
    }

    /**
     * Notify the host application that a page has started loading. This method
     * is called once for each main frame load so a page with iframes or
     * framesets will call onPageStarted one time for the main frame. This also
     * means that onPageStarted will not be called when the contents of an
     * embedded frame changes, i.e. clicking a link whose target is an iframe,
     * it will also not be called for fragment navigations (navigations to
     * #fragment_id).
     *
     * @param view    The WebView that is initiating the callback.
     * @param url     The url to be loaded.
     * @param favicon The favicon for this page if it already exists in the
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.d(TAG, "onPageStarted: ");
    }

    /**
     * Notify the host application that a page has finished loading. This method
     * is called only for main frame. When onPageFinished() is called, the
     * rendering picture may not be updated yet. To get the notification for the
     * new Picture, use {@link WebView.PictureListener#onNewPicture}.
     *
     * @param view The WebView that is initiating the callback.
     * @param url  The url of the page.
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        view.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                // 这里回调的value是之前js代码之前的面返回的“Android调用了callJS方法”字符串
                Log.d(TAG, "onReceiveValue: " + value);
            }
        });
        Log.d(TAG, "onPageFinished: ");
    }

}
