package com.halturin.dmitry.rssreader.view.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.halturin.dmitry.rssreader.view.FloatingLayout;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com> on 01.08.17 17:27.
 */

public class RssUrlLayout extends LinearLayout implements FloatingLayout {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private static final int duration = 300;

    private boolean ready = true;
    private boolean visible = true;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public RssUrlLayout(Context context){
        super(context);
    }

    public RssUrlLayout(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public void setVisibility(int visibility){
        super.setVisibility(visibility);

        visible = (visibility == VISIBLE);
    }

//==================================================================================================
//    Class Implementation FloatingLayout
//==================================================================================================


    @Override
    public void setVisible(){
        if(ready){
            ready = false;

            setVisibility(VISIBLE);
            setAlpha(0f);

            int height = getHeight();

            setTop(-height);

            animate()
                .translationY(0)
                .alpha(1f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation){
                        visible = true;
                        ready = true;
                    }
                });
        }
    }

    @Override
    public void setInvisible(){
        if(ready){
            ready = false;

            int height = getHeight();

            animate()
                .translationY(-height)
                .alpha(0)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation){
                        setVisibility(GONE);
                        visible = false;
                        ready = true;
                    }
                });
        }
    }

    @Override
    public void changeVisibility(){
        if(visible){
            setInvisible();
        }else{
            setVisible();
        }
    }

}
