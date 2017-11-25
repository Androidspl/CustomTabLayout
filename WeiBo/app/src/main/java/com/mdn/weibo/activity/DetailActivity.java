package com.mdn.weibo.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mdn.weibo.R;
import com.mdn.weibo.custom.ObservableScrollView;

import java.util.ArrayList;

/**
 * Created by pengleiShen on 2017/11/6.
 */
public class DetailActivity extends AppCompatActivity implements ObservableScrollView.OnObservableScrollViewScrollChanged{

    private ObservableScrollView ob_contentView;
    //用来记录内层固定布局到屏幕顶部的距离
    private int mHeight;
    private FrameLayout rl_topView;
    private FrameLayout ll_fixedView;
    private TabLayout tablayout;
    private ArrayList<String> list_title;
    private int[] mTabImages;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        view = LayoutInflater.from(this).inflate(R.layout.activity_detail, null);
        ob_contentView = (ObservableScrollView) findViewById(R.id.ob_contentView);
        rl_topView = (FrameLayout) findViewById(R.id.ll_topView);
//        tv_topView = (TextView) findViewById(R.id.tv_topView);
        ll_fixedView = (FrameLayout) findViewById(R.id.ll_fixedView);

        initTabLayout();
        tabLayoutChange();

        ob_contentView.setOnObservableScrollViewScrollChanged(this);


//        ViewTreeObserver viewTreeObserver = ll_topView.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    ll_topView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }
//                mHeight=ll_topView.getTop();
//            }
//        });
    }

    private void tabLayoutChange() {

        list_title = new ArrayList<>();
        list_title.add("全部");
        list_title.add("附近");
//        list_title.add("智能排序");
//        list_title.add("筛选");

        for (int i=0;i<list_title.size();i++) {
//            tablayout.addTab(tablayout.newTab().setText(list_title.get(i)));
            Drawable dImage = getResources().getDrawable(mTabImages[i]);
//        dImage.setBounds(0, 0, dImage.getIntrinsicWidth(), dImage.getIntrinsicHeight());
            dImage.setBounds(0, 0, 30, 30);
            //这里前面加的空格就是为图片显示
//        SpannableString sp = new SpannableString("  "+ list_title.get(position));
            SpannableString sp = new SpannableString(list_title.get(i) + "   ");
            ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BOTTOM);
//        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BASELINE);
            sp.setSpan(imageSpan, list_title.size(), list_title.size() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            tablayout.addTab(tablayout.newTab().setText(sp));
            tablayout.addTab(tablayout.newTab().setText(list_title.get(i)));
        }

    }

    private void initTabLayout() {

        tablayout = (TabLayout) findViewById(R.id.tl_topView);
        //为tabLayout上的图标赋值
//        mTabImages = new int[]{R.drawable.arrow_down,R.drawable.arrow_down,R.drawable.arrow_down,R.drawable.arrow_down};
        mTabImages = new int[]{R.drawable.arrow_down,R.drawable.arrow_down};

    }

    @Override
    public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt) {
        if(t>=mHeight){
            if(tablayout.getParent()!=ll_fixedView){
                rl_topView.removeView(tablayout);
                ll_fixedView.addView(tablayout);
                ll_fixedView.setVisibility(View.VISIBLE);
            }
        }else{
            if(tablayout.getParent()!=rl_topView){
                ll_fixedView.removeView(tablayout);
                rl_topView.addView(tablayout);
                ll_fixedView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            //获取HeaderView的高度，当滑动大于等于这个高度的时候，需要把topView移除当前布局，放入到外层布局
            mHeight=rl_topView.getTop();
        }
    }
}
