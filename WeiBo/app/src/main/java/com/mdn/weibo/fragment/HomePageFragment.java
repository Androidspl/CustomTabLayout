package com.mdn.weibo.fragment;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import com.mdn.weibo.custom.MaterialTabLayout;
import com.mdn.weibo.utils.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by pengleiShen on 2017/10/25.
 */

public class HomePageFragment extends Fragment {

  
//MySecondGit提交第一次提交远程。  
private View view;
    private MaterialTabLayout tablayout;
    private ViewPager viewpager;
    private List<String> list_title;
    private List<Fragment> list_fragment;
    private FollowFragment mflFragment;
    private HotTopicFragment mHotFragment;

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

        HotTopicFragment HotFragment1 = new HotTopicFragment();
        HotTopicFragment mHotFragment2 = new  HotTopicFragment();
        HotTopicFragment mHotFragment3 = new HotTopicFragment();
        HotTopicFragment mHotFragment4 = new HotTopicFragment();
        HotTopicFragment mHotFragment5 = new HotTopicFragment();
        HotTopicFragment mHotFragment6 = new HotTopicFragment();
        HotTopicFragment mHotFragment7 = new HotTopicFragment();
        list_fragment.add(mflFragment);
        list_fragment.add(mHotFragment);

        list_fragment.add(HotFragment1);
        list_fragment.add(mHotFragment2);
        list_fragment.add(mHotFragment3);
        list_fragment.add(mHotFragment4);
        list_fragment.add(mHotFragment5);
        list_fragment.add(mHotFragment6);
        list_fragment.add(mHotFragment7);

        list_title = new ArrayList<>();
        list_title.add("关注");
        list_title.add("热门");
        list_title.add("学校");
        list_title.add("美食");
        list_title.add("游戏");
        list_title.add("动漫");
        list_title.add("运动");
        list_title.add("旅游");
        list_title.add("娱乐");

        for (int i=0;i<list_title.size();i++) {
            tablayout.addTab(tablayout.newTab().setText(list_title.get(i)));
        }

        TabFragmentAdapter mFragmentAdapter = new TabFragmentAdapter(getContext(), getChildFragmentManager(), list_fragment, list_title);
        viewpager.setAdapter(mFragmentAdapter);

        //将tabLayout与viewpager连起来
        tablayout.setTabMargin(18);
        tablayout.setupWithViewPager(viewpager);

    }

    private void initControls() {

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_homepage,null);
        tablayout = (MaterialTabLayout) view.findViewById(R.id.tablayout);
        viewpager = (ViewPager) view.findViewById(R.id.view_pager);

    }

    @Override
    public void onStart() {
        super.onStart();
        tablayout.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void run() {
//                setIndicator(tablayout,20,20);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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

//        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
//        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//            params.leftMargin = left;
//            params.rightMargin = right;
//            params.setMarginStart(Utils.dip2px(getContext(), 60));
//            params.setMarginEnd(Utils.dip2px(getContext(), 45));
            params.setMarginStart(Utils.dip2px(getContext(), 0));
            params.setMarginEnd(Utils.dip2px(getContext(), 45));
            child.setLayoutParams(params);
            child.invalidate();

        }

    }


}
