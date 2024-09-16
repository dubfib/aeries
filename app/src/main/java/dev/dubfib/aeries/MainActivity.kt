package dev.dubfib.aeries

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        val webView: WebView = findViewById(R.id.webview);

        onBackPressedDispatcher.addCallback {
            if (webView.canGoBack()) {
                webView.goBack();
            };
        };

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url ?: return false);
                return true;
            };

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url);

                val loading = readAsset("Loading.js");
                webView.evaluateJavascript("javascript:(function () {\n" +
                        "$loading\n" +
                        "})();"
                ) {};
            };
        };

        CookieManager.getInstance().setAcceptCookie(true);

        webView.settings.apply {
            loadWithOverviewMode = true;
            useWideViewPort = true;
            domStorageEnabled = true;
            javaScriptEnabled = true;
            userAgentString = "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36(KHTML, like Gecko) Chrome/128.0.6613.88 Mobile Safari/537.36"
        };

        webView.loadUrl("https://elcaminoreal.asp.aeries.net/student/Dashboard.aspx")
    };

    fun readAsset(filename: String): String {
        val string = StringBuilder();

        try {
            val stream = assets.open(filename);
            val buffer = BufferedReader(InputStreamReader(stream));
            var line: String? = buffer.readLine();

            while (line != null) {
                string.append(line).append("\n");
                line = buffer.readLine();
            };

            buffer.close();
        } catch (e: Exception) {
            e.printStackTrace();
        }

        return string.toString();
    };
};