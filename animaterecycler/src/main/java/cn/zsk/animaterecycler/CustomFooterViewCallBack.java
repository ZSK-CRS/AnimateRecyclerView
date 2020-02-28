package cn.zsk.animaterecycler;

import android.view.View;

/**
 * Author : ZSK
 * Date : 2020/2/26
 * Description :
 */
public interface CustomFooterViewCallBack {

    void onLoadingMore(View yourFooterView);
    void onLoadMoreComplete(View yourFooterView);
    void onSetNoMore(View yourFooterView,boolean noMore);

}
