package com.smile.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    ImageButton homeBtn, searchBtn;
    EditText addressInput;
    WebView webView;
    ProgressBar progressBar;
    ImageButton back, forward, stop, refbtn, home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBtn = findViewById(R.id.btnmic);
        addressInput = findViewById(R.id.address_input);
        searchBtn = findViewById(R.id.search_btn);
        webView = findViewById(R.id.webView);
        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);
        stop = (ImageButton) findViewById(R.id.stop);
        refbtn = (ImageButton) findViewById(R.id.refbtn);
        home = (ImageButton) findViewById(R.id.home);


        progressBar = (ProgressBar) findViewById(R.id.progress_Bar);
        progressBar.setMax(100);
        progressBar.setVisibility(View.VISIBLE);

        webView.loadUrl("https://google.com");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setSupportZoom(true);

        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged (WebView view, int newProgress){
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);

                if (newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                if (newProgress == 100) {
                    progressBar.setProgress(progressBar.GONE);
                } else {
                    progressBar.setVisibility(ProgressBar.VISIBLE);

                }

            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!addressInput.getText().toString().isEmpty()) {
                    String address = addressInput.getText().toString();
                    if (address.contains("http") || address.contains("https")) {
                        if (address.contains(".com") || address.contains(".net") || address.contains(".in")) {
                            webView.loadUrl(address);
                        } else {
                            webView.loadUrl("https://www.google.com/search?q=" + address.replace("http", "").replace("https", ""));
                        }
                    } else {
                        if (address.contains(".com") || address.contains(".net") || address.contains(".in")) {
                            webView.loadUrl("http://" + address);
                        } else {
                            webView.loadUrl("https://www.google.com/search?q=" + address.replace("http", "").replace("https", ""));
                        }
                    }
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()){
                    webView.goBack();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoForward()){
                    webView.goForward();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.stopLoading();
            }
        });

        refbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                webView.reload();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                webView.loadUrl("https://google.com");
            }
        });

        progressBar = findViewById(R.id.progress_Bar);

            final Timer t = new Timer();
            final TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    int counter = 2;
                    progressBar.setProgress(counter);
                    if(counter==100){
                        t.cancel();
                    }
                }
            };
            t.schedule(tt,0,120);

        }

    }


