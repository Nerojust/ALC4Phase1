package com.example.alc4phase1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Handles the webview and refresh functionality.
 */
public class ActivityTwo extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        initializeViews();
    }

    /**
     * initializing all views
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initializeViews() {
        final WebView alcWebsiteView = findViewById(R.id.webviewAlc);
        alcWebsiteView.getSettings().setJavaScriptEnabled(true);
        alcWebsiteView.setWebViewClient(new ALCwebviewClient());
        alcWebsiteView.loadUrl(getResources().getString(R.string.andela_website));
        alcWebsiteView.canGoBack();
        alcWebsiteView.requestFocus();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                alcWebsiteView.reload();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    /**
     * Creating a Webview Client class for implementation.
     */
    private class ALCwebviewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //in case there is ssl restriction by webview.
            handler.proceed();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //show loading dialog
            dialog = ProgressDialog.show(ActivityTwo.this, "",
                    getResources().getString(R.string.loading), true);
            //display refreshing at the top of screen
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //remove refreshing icon at top of screen
            swipeRefreshLayout.setRefreshing(false);
            //dismiss loading dialog
            dialog.dismiss();
        }
    }
}
