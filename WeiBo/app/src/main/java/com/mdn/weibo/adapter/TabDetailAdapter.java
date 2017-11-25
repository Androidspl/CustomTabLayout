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

public class TabDetailAdapter extends FragmentPagerAdapter{

    private int[] mTabImages;
    private Context context;
    private List<String> list_title;

    public TabDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabDetailAdapter(Context context, FragmentManager fm, List<String> list_title, int[] mTabImages ) {
        super(fm);
        this.context = context;
        this.list_title = list_title;
        this.mTabImages = mTabImages;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//这段被注的代码，是只显示文字，不显示图标
        //return  list_Title.get(position % list_Title.size());

        Drawable dImage = context.getResources().getDrawable(mTabImages[position]);
//        dImage.setBounds(0, 0, dImage.getIntrinsicWidth(), dImage.getIntrinsicHeight());
        dImage.setBounds(0,0,30,30);
        //这里前面加的空格就是为图片显示
//        SpannableString sp = new SpannableString("  "+ list_title.get(position));
        SpannableString sp = new SpannableString(list_title.get(position) + "   ");
        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BOTTOM);
//        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BASELINE);
        sp.setSpan(imageSpan, 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return  sp;
    }

}
