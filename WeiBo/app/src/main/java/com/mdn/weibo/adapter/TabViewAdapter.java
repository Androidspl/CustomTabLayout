package com.mdn.weibo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by pengleiShen on 2017/11/3.
 */

public class TabViewAdapter extends PagerAdapter {

    private List<Fragment> list_fragment;
    private Context context;
    private List<String> list_title;
    private int[] mTabImages;

    public TabViewAdapter(Context context, List<Fragment> list_fragment, List<String> list_title, int[] mTabImages ) {
        this.context = context;
        this.list_fragment = list_fragment;
        this.list_title = list_title;
        this.mTabImages = mTabImages;
    }


    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ((ViewPager) container).addView(list_fragment.get(position), 0);
        return list_fragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //这段被注的代码，是只显示文字，不显示图标
        //return  list_Title.get(position % list_Title.size());

        Drawable dImage = context.getResources().getDrawable(mTabImages[position]);
//        dImage.setBounds(0, 0, dImage.getIntrinsicWidth(), dImage.getIntrinsicHeight());
        dImage.setBounds(0,0,30,30);
//        dImage.setBounds(30,30,0,0);
//        dImage.setBounds(30,0,0,30);
        //这里前面加的空格就是为图片显示
//        SpannableString sp = new SpannableString("  "+ list_title.get(position));
        SpannableString sp = new SpannableString(list_title.get(position) + "   ");
//        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BOTTOM);
        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BASELINE);
        sp.setSpan(imageSpan, 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return  sp;
    }
}
