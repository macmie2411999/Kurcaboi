package com.example.nreaderv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity4 extends AppCompatActivity {

    // khai báo biến
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // mapping
        webView = (WebView) findViewById(R.id.webView);

        // lấy link tử MainActivity3
        Intent intent = getIntent();
        String link = intent.getStringExtra("linkNews");

        // Use WebView (Featured function to load html), and set client defaultly for opening Link in App without use another browser
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}