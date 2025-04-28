package com.aliqin.mytel.auth;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.sean.rao.ali_auth.R;
import com.mobile.auth.gatewayauth.Constant;

/**
 * @ProjectName: NumberAuthSDK_Standard_Android
 * @Package: com.aliqin.mytel.auth
 * @ClassName: CustomWebView
 * @Description: 自定义协议展示页
 * @Author: liuqi
 * @CreateDate: 2021/3/25 4:04 PM
 * @Version: 1.0
 */
public class CustomWebViewActivity extends Activity {
    private WebView mWebView;
//    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_web);
        String mUrl = getIntent().getStringExtra(Constant.PROTOCOL_WEB_VIEW_URL);
        String mName = getIntent().getStringExtra(Constant.PROTOCOL_WEB_VIEW_NAME);
        setRequestedOrientation(
                getIntent().getIntExtra(Constant.PROTOCOL_WEB_VIEW_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
        mWebView = findViewById(R.id.webView);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initWebView();
        mWebView.loadUrl(mUrl);

        Window window = getWindow();
        View decorView = window.getDecorView();
        // 获取当前的系统UI可见性标志
        int systemUiVisibility = decorView.getSystemUiVisibility();

        // 清除所有状态栏相关标志
        systemUiVisibility &= ~(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //设置状态栏颜色为白色
        window.setStatusBarColor(Color.WHITE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11 (API 30) 及以上版本，使用 WindowInsetsController
            WindowInsetsController controller = window.getInsetsController();
            if (controller != null) {
                controller.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android 6.0 (API 23) - Android 10 (API 29)，使用 SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            // 添加 View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 标志
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(systemUiVisibility);
        }

    }

    private void initWebView() {
        WebSettings wvSettings = mWebView.getSettings();
        // 是否阻止网络图像
        wvSettings.setBlockNetworkImage(false);
        // 是否阻止网络请求
        wvSettings.setBlockNetworkLoads(false);
        // 是否加载JS
        wvSettings.setJavaScriptEnabled(true);
        wvSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //覆盖方式启动缓存
        wvSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 使用广泛视窗
        wvSettings.setUseWideViewPort(true);
        wvSettings.setLoadWithOverviewMode(true);
        wvSettings.setDomStorageEnabled(true);
        //是否支持缩放
        wvSettings.setBuiltInZoomControls(false);
        wvSettings.setSupportZoom(false);
        //不显示缩放按钮
        wvSettings.setDisplayZoomControls(false);
        wvSettings.setAllowFileAccess(true);
        wvSettings.setDatabaseEnabled(true);
        mWebView.setVerticalScrollbarOverlay(false); //不出现指定的垂直滚动条有叠加样式
        wvSettings.setUseWideViewPort(true);//设定支持viewport
        wvSettings.setBuiltInZoomControls(true);//设置出现缩放工具
        wvSettings.setDisplayZoomControls(false);//设置缩放工具隐藏
        wvSettings.setSupportZoom(true);//设定支持缩放
        //缓存相关
        // wvSettings.setAppCacheEnabled(true);
        wvSettings.setDomStorageEnabled(true);
        wvSettings.setDatabaseEnabled(true);
    }
}
