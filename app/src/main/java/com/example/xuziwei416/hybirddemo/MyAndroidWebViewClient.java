package com.example.xuziwei416.hybirddemo;

import android.graphics.Bitmap;
import android.nfc.Tag;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        Log.d(TAG, "shouldOverrideUrlLoading" + request.getUrl());
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
        Log.d(TAG, "onPageFinished: ");
    }

}
