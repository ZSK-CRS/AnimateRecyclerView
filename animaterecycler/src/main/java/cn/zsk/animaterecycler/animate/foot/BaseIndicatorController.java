package cn.zsk.animaterecycler.animate.foot;

import android.animation.Animator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.List;

/**
 * Created by Jack on 2015/10/15.
 */
public abstract class BaseIndicatorController {


    private View mTarget;

    private List<Animator> mAnimators;

    public void destroy(){
        mTarget = null;
        releaseAnimations();
        mAnimators = null;
    }

    public void setTarget(View target){
        this.mTarget=target;
    }

    public View getTarget(){
        return mTarget;
    }


    public int getWidth(){
        if(mTarget == null){
            return 0;
        }
        return mTarget.getWidth();
    }

    public int getHeight(){
        if(mTarget == null){
            return 0;
        }
        return mTarget.getHeight();
    }

    public void postInvalidate(){
        if(mTarget != null){
            mTarget.postInvalidate();
        }
    }

    /**
     * draw indicator
     * @param canvas
     * @param paint
     */
    public abstract void draw(Canvas canvas, Paint paint);

    /**
     * create animation or animations
     */
    public abstract List<Animator> createAnimation();

    public void initAnimation(){
        mAnimators=createAnimation();
    }

    // add by lgh
    private void releaseAnimations(){
        if (mAnimators==null){
            return;
        }
        int count=mAnimators.size();
        for (int i = 0; i < count; i++) {
            Animator animator = mAnimators.get(i);
            animator.cancel();
            animator.removeAllListeners();
        }
        mAnimators.clear();
        mAnimators = null;
    }

    /**
     * make animation to start or end when target
     * view was be Visible or Gone or Invisible.
     * make animation to cancel when target view
     * be onDetachedFromWindow.
     * @param animStatus
     */
    public void setAnimationStatus(AnimStatus animStatus){
        if (mAnimators==null){
            return;
        }
        int count=mAnimators.size();
        for (int i = 0; i < count; i++) {
            Animator animator=mAnimators.get(i);
            boolean isRunning=animator.isRunning();
            switch (animStatus){
                case START:
                    if (!isRunning){
                        animator.start();
                    }
                    break;
                case END:
                    if (isRunning){
                        animator.end();
                    }
                    break;
                case CANCEL:
                    if (isRunning){
                        animator.cancel();
                    }
                    break;
            }
        }
    }


    public enum AnimStatus{
        START,END,CANCEL
    }



}
