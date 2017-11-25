package com.mdn.weibo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.mdn.weibo.R;
import com.mdn.weibo.fragment.DetailsFragment;

/**
 * Created by pengleiShen on 2017/11/6.
 */
public class DetailsActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        DetailsFragment fragment = new DetailsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,fragment).commit();

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        super.onTouchEvent(event);
//        return false;
//    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
////        super.dispatchTouchEvent(ev);
//        return true;
//    }
}
