package kr.co.bluebright.www.myexperiment.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Handling exception of <b>java.lang.IllegalArgumentException: pointerIndex out of range</b>
 *
 * @see IllegalArgumentException
 * @see <a href="https://github.com/chrisbanes/PhotoView/issues/31#issuecomment-19803926">Github issue</a>
 */
public class ViewPagerFixed extends ViewPager {

    public ViewPagerFixed(Context context) {
        super(context);
    }

    public ViewPagerFixed(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            //ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            //ex.printStackTrace();
        }
        return false;
    }
}
