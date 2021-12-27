package com.first_java_app.esp32ipcam;
//https://stackoverflow.com/questions/11430182/android-see-images-of-ip-camera-with-webview-on-android-2-2
//https://stackoverflow.com/questions/3099344/can-androids-webview-automatically-resize-huge-images
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    String html;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        String ipCamPath = "192.168.1.7";   // Camera ip here!!!
        String imgHtml = "<img src=\"http://"+ipCamPath+"/\">\n";
        html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                imgHtml+
                "</body>\n" +
                "</html>";
        webView.getSettings().setJavaScriptEnabled(true);
        openWebView();
    }
    @SuppressLint("NewApi")
    private void openWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        webView.loadDataWithBaseURL("file:///android_asset/", getHtmlData(html), "text/html", "utf-8", null);
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}