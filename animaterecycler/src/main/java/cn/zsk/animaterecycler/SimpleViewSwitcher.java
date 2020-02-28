package cn.zsk.animaterecycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author : ZSK
 * Date : 2020/2/25
 * Description :
 */
public class SimpleViewSwitcher extends ViewGroup {

    public SimpleViewSwitcher(Context context) {
        super(context, null);
    }

    public SimpleViewSwitcher(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleViewSwitcher(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = this.getChildCount();
        int maxHeight = 0;
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            maxWidth += child.getMeasuredWidth();
            maxHeight += child.getMeasuredHeight();
        }
        maxHeight = resolveSize(maxHeight, heightMeasureSpec);
        maxWidth = resolveSize(maxWidth, widthMeasureSpec);
        setMeasuredDimension(maxWidth, maxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(0, 0, r - l, b - t);
            }
        }
    }

    public void setView(View view) {
        if (getChildCount() != 0) {
            removeViewAt(0);
        }
        addView(view, 0);
    }
}
