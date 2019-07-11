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

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        initializeViews();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initializeViews() {
        WebView alcWebsiteView = findViewById(R.id.webviewAlc);
        alcWebsiteView.getSettings().setJavaScriptEnabled(true);
        alcWebsiteView.setWebViewClient(new ALCwebviewClient());
        alcWebsiteView.loadUrl(getResources().getString(R.string.andela_website));
        alcWebsiteView.requestFocus();
    }

    private class ALCwebviewClient extends WebViewClient {

        private ProgressDialog dialog;

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            dialog = ProgressDialog.show(ActivityTwo.this, "",
                    getResources().getString(R.string.loading), true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            dialog.dismiss();
        }
    }
}
