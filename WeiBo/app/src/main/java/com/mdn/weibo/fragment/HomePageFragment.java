package com.mdn.weibo.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mdn.weibo.R;
import com.mdn.weibo.adapter.TabFragmentAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by pengleiShen on 2017/10/25.
 */

public class HomePageFragment extends Fragment {

    private View view;
    private TabLayout tablayout;
    private ViewPager viewpager;
    private List<String> list_title;
    private List<Fragment> list_fragment;
    private FollowFragment mflFragment;
    private HotTopicFragment mHotFragment;
    private int[] mTabImages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        initControls();
        fragmentChange();
        return view;

    }

    private void fragmentChange() {

        list_fragment = new ArrayList<>();
        mflFragment = new FollowFragment();
        mHotFragment = new HotTopicFragment();
        list_fragment.add(mflFragment);
        list_fragment.add(mHotFragment);

        list_title = new ArrayList<>();
        list_title.add("关注");
        list_title.add("热门");

        for (int i=0;i<list_title.size();i++) {
            tablayout.addTab(tablayout.newTab().setText(list_title.get(i)));
//            Drawable dImage = getResources().getDrawable(mTabImages[i]);
////        dImage.setBounds(0, 0, dImage.getIntrinsicWidth(), dImage.getIntrinsicHeight());
//            dImage.setBounds(0, 0, 30, 30);
//            //这里前面加的空格就是为图片显示
////        SpannableString sp = new SpannableString("  "+ list_title.get(position));
//            SpannableString sp = new SpannableString(list_title.get(i) + "   ");
//            ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BOTTOM);
////        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BASELINE);
//            sp.setSpan(imageSpan, list_title.size(), list_title.size() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            tablayout.addTab(tablayout.newTab().setText(sp));
        }

        TabFragmentAdapter mFragmentAdapter = new TabFragmentAdapter(getContext(), getChildFragmentManager(), list_fragment, list_title, mTabImages);
//        TabViewAdapter tabViewAdapter = new TabViewAdapter(getContext(), list_fragment, list_title, mTabImages );
        viewpager.setAdapter(mFragmentAdapter);

        //将tabLayout与viewpager连起来
        tablayout.setupWithViewPager(viewpager);


    }

    private void initControls() {

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_homepage,null);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewpager = (ViewPager) view.findViewById(R.id.view_pager);

        //为tabLayout上的图标赋值
        mTabImages = new int[]{R.drawable.arrow_down,R.drawable.arrow_down};

    }

    @Override
    public void onStart() {
        super.onStart();
        tablayout.post(new Runnable() {
            @Override
            public void run() {
//                setIndicator(tablayout,20,20);
            }
        });
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();

        }

    }


}
