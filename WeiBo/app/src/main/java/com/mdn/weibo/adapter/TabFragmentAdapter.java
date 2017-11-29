package com.mdn.weibo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.List;

/**
 * Created by pengleiShen on 2017/11/3.
 */

public class TabFragmentAdapter extends FragmentPagerAdapter{

    private Context context;
    private List<Fragment> list_fragment;
    private List<String> list_title;

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabFragmentAdapter(Context context, FragmentManager fm, List<Fragment> list_fragment,
                              List<String> list_title ) {
        super(fm);
        this.context = context;
        this.list_fragment = list_fragment;
        this.list_title = list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        //这段被注的代码，是只显示文字，不显示图标
        return  list_title.get(position % list_title.size());

    }

}
