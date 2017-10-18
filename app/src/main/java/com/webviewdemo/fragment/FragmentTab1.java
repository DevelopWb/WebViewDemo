package com.webviewdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.webviewdemo.R;
import com.webviewdemo.interfaces.FinishActivityInterface;

/**
 * Created by ${王sir} on 2017/10/18.
 * application
 */

public class FragmentTab1 extends Fragment {


    private View view;
    private WebView mWebviewTab1;
    private  FinishActivityInterface finishActivityInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mWebviewTab1 = (WebView) view.findViewById(R.id.webview_tab1);
        mWebviewTab1.loadUrl("http://byu2605480001.my3w.com/index.php/Mobile/Index/index.html ");


        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebviewTab1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    public void setFinishActivity( FinishActivityInterface finishActivityInterface) {
       this.finishActivityInterface = finishActivityInterface;
    }
    //点击返回上一页面而不是退出浏览器
    public void onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebviewTab1.canGoBack()) {
            mWebviewTab1.goBack();
        }else{
            if (finishActivityInterface!=null) {
                finishActivityInterface.finishActivity();
            }
        }

    }

    //销毁Webview
    @Override
    public void onDestroy() {
        if (mWebviewTab1 != null) {
            mWebviewTab1.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebviewTab1.clearHistory();

            ((ViewGroup) mWebviewTab1.getParent()).removeView(mWebviewTab1);
            mWebviewTab1.destroy();
            mWebviewTab1 = null;
        }
        super.onDestroy();
    }
}
