package com.mdn.weibo.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mdn.weibo.R;
import com.mdn.weibo.adapter.PopWindowListViewAdapter;
import com.mdn.weibo.custom.ObservableScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;

/**
 * Created by pengleiShen on 2017/10/25.
 */

public class DetailsFragment extends Fragment implements ObservableScrollView.OnObservableScrollViewScrollChanged {

    private TabLayout tablayout;
    private List<String> list_title;
    private int[] mTabImages;
    private View view;
    private View  tab_bottom_line;
    //用来记录内层固定布局到屏幕顶部的距离
    private int mHeight;
    private ObservableScrollView ob_contentView;
    private FrameLayout ll_topView;
    private FrameLayout ll_fixedView;
    private SpannableString mSpNormal;
    private SpannableString mSpSelected;
    private PopupWindow mPopupWindow;
    private ListView custom_lv;
    List<List<String>> mPopLists;
    private PopWindowListViewAdapter mAdapter;
    private ImageView tv_headerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        initControls();
        fragmentChange();
        initDate();
        
        ViewTreeObserver viewTreeObserver = ll_topView.getViewTreeObserver();

        ob_contentView.setOnObservableScrollViewScrollChanged(this);

        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ll_topView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                System.out.println("在addOnGlobalLayoutListener中的HeightTop：" +  tv_headerView.getTop());
                System.out.println("在addOnGlobalLayoutListener中的HeightBottom：" +  tv_headerView.getBottom());
                System.out.println("在addOnGlobalLayoutListener中的Height：" +  tv_headerView.getHeight());

                mHeight = ll_topView.getTop();
                System.out.println("当前mHeightTop:" + mHeight + "");
                System.out.println("当前mHeightBottom:" + ll_topView.getBottom() + "");
                System.out.println("当前Height:" + ll_topView.getHeight() + "");
            }
        });

        tablayout.addOnTabSelectedListener(mOnTabSelectedListener);

        return view;
    }

    private byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte imgdata[] = bytestream.toByteArray();
        bytestream.close();
        return imgdata;
    }

    private void initDate() {
        try {
            mPopLists = new ArrayList<>();

//            FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\json.txt");
//            FileInputStream fis = new FileInputStream("file:///assets/json.txt");
//            InputStream abpath = getClass().getResourceAsStream("/assets/json");
//            String path = new String(InputStreamToByte(abpath ));

//            FileInputStream fis = new FileInputStream(path);
//            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
//            BufferedReader br = new BufferedReader(isr);

//            String input;
//            StringBuffer sb = new StringBuffer();
//            while ((input = br.readLine()) != null) {
//                sb.append(input);
//            }
//            String data = sb.toString();
//            JSONObject jsonObject = new JSONObject(date);

            InputStream abpath  = getContext().getClass().getClassLoader().getResourceAsStream("assets/json.txt");
            String path = new String(InputStreamToByte(abpath));
            JSONObject jsonObject = new JSONObject(path);
            String status = jsonObject.getString("status");

            JSONArray jsonArrays = jsonObject.getJSONArray("data");

            for (int i=0;i<jsonArrays.length();i++) {
                JSONArray jsonArray = (JSONArray) jsonArrays.get(i);
//                String context = jsonArray.getString("context");
                List<String> mPopList = new ArrayList<>();
                for (int j=0;j<jsonArray.length();j++) {
                    JSONObject obj = (JSONObject) jsonArray.get(j);
                    mPopList.add(obj.getString("context"));
                }
                mPopLists.add(mPopList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initControls() {

        view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_detail, null);
        tablayout = (TabLayout) view.findViewById(R.id.tl_topView);
        ob_contentView = (ObservableScrollView) view.findViewById(R.id.ob_contentView);
        ll_topView = (FrameLayout) view.findViewById(R.id.ll_topView);
        ll_fixedView = (FrameLayout) view.findViewById(R.id.ll_fixedView);
        tab_bottom_line =  view.findViewById(R.id.tab_bottom_line);
        // 图片
        tv_headerView = (ImageView) view.findViewById(R.id.tv_headerView);

        //为tabLayout上的图标赋值
        mTabImages = new int[]{R.drawable.arrow_down, R.drawable.arrow_up};

        list_title = new ArrayList<>();
        list_title.add("全部");
        list_title.add("附近");
        list_title.add("智能排序");
        list_title.add("筛选");

        View mPopView = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_listview, null);
        custom_lv = (ListView) mPopView.findViewById(R.id.custom_lv);
        mPopupWindow = new PopupWindow(mPopView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

}

    private void fragmentChange() {

        for (int i = 0; i < list_title.size(); i++) {
            tablayout.addTab(tablayout.newTab().setTag(false));
            View mTabView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tab_custom, null);
            TextView tab_text = (TextView) mTabView.findViewById(R.id.tab_text);
            ImageView tab_img = (ImageView) mTabView.findViewById(R.id.tab_img);
            tab_text.setText(list_title.get(i));
            tab_img.setImageResource(R.drawable.arrow_down);
            TabLayout.Tab tab = tablayout.getTabAt(i);
            if (tab != null) {
                mTabView.setTag(i);
                tab.setCustomView(mTabView);
//                if (tab.getCustomView() != null) {
//                    View mTabViews = (View) tab.getCustomView().getParent();
//                }
            }
//            setArrowDown(i);
//            tablayout.addTab(tablayout.newTab().setText(mSpNormal));
        }
    }

    @Override
    public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt) {

        if (t >= mHeight) {
            if (tablayout.getParent() != ll_fixedView) {
                ll_topView.removeView(tablayout);
                ll_fixedView.addView(tablayout);
                ll_fixedView.setVisibility(View.VISIBLE);
            }
        } else {
            if (tablayout.getParent() != ll_topView) {
                ll_fixedView.removeView(tablayout);
                ll_topView.addView(tablayout);
                ll_fixedView.setVisibility(View.GONE);
            }
        }
    }

    private void setCustomTabView(int position) {
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            View view = tablayout.getTabAt(i).getCustomView();
            TextView tab_text = (TextView) view.findViewById(R.id.tab_text);
            ImageView tab_img = (ImageView) view.findViewById(R.id.tab_img);
            if (i == position) {
                tab_img.setImageResource(R.drawable.arrow_up);
                tab_text.setTextColor(Color.BLUE);
            } else {
                tab_img.setImageResource(R.drawable.arrow_down);
                tab_text.setTextColor(Color.GRAY);
            }
        }
    }

    TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            // 是否展开
            boolean IsExpansion = (boolean) tab.getTag();
            View tab_view = tab.getCustomView();
            TextView tab_text = (TextView) tab_view.findViewById(R.id.tab_text);
            ImageView tab_img = (ImageView) tab_view.findViewById(R.id.tab_img);

            if (!mPopupWindow.isShowing() && mPopupWindow != null) {
                ob_contentView.scrollTo(0,mHeight);
                mPopupWindow.showAsDropDown(tab_bottom_line);
            }

            int tab_position = (int)tab_view.getTag();
            setListView(mPopLists.get(tab_position));

            tab_img.setImageResource(R.drawable.arrow_up);
            tab_text.setTextColor(Color.BLUE);
            tab.setTag(true);

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            tab.setTag(false);
            View view = tab.getCustomView();
            TextView tab_text = (TextView) view.findViewById(R.id.tab_text);
            ImageView tab_img = (ImageView) view.findViewById(R.id.tab_img);
            tab_img.setImageResource(R.drawable.arrow_down);
            tab_text.setTextColor(Color.GRAY);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

            // 是否展开
            boolean IsExpansion = (boolean) tab.getTag();
            View tab_view = tab.getCustomView();
            TextView tab_text = (TextView) tab_view.findViewById(R.id.tab_text);
            ImageView tab_img = (ImageView) tab_view.findViewById(R.id.tab_img);

            // 如果展开，点击的话，popwindow消失，文字和图片变灰
            if (mPopupWindow.isShowing() && IsExpansion) {
                mPopupWindow.dismiss();
                tab.setTag(false);
                tab_img.setImageResource(R.drawable.arrow_down);
                tab_text.setTextColor(Color.GRAY);
            } else if (!mPopupWindow.isShowing() && !IsExpansion) {
                ob_contentView.scrollTo(0,mHeight);
                setListView(mPopLists.get((int)tab_view.getTag()));
                mPopupWindow.showAsDropDown(tab_bottom_line);
                tab.setTag(true);
                tab_img.setImageResource(R.drawable.arrow_up);
                tab_text.setTextColor(Color.BLUE);
            }

        }
    };

    private void setListView(List<String> list){
        PopWindowListViewAdapter mAdapter = null;
        mAdapter = new PopWindowListViewAdapter(getContext(),list);
//        if (mAdapter == null){
//            mAdapter = new PopWindowListViewAdapter(getContext(),list);
//        }
        custom_lv.setAdapter(mAdapter);
    }

    private void setArrowDown(int position) {
        Drawable dImage = getResources().getDrawable(mTabImages[0]);
//        dImage.setBounds(0, 0, dImage.getIntrinsicWidth(), dImage.getIntrinsicHeight());
        dImage.setBounds(0, 0, 15, 15);
        //这里前面加的空格就是为图片显示
        mSpNormal = new SpannableString(list_title.get(position) + "   ");
//            ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BOTTOM);
        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BASELINE);
        mSpNormal.setSpan(imageSpan, list_title.get(position).length() + 2, list_title.get(position).length() + 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

    private void setArrowUp(int position) {
        Drawable dImage = getResources().getDrawable(mTabImages[1]);
//        dImage.setBounds(0, 0, dImage.getIntrinsicWidth(), dImage.getIntrinsicHeight());
        dImage.setBounds(0, 0, 15, 15);
        //这里前面加的空格就是为图片显示
        mSpSelected = new SpannableString(list_title.get(position) + "   ");
//      ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BOTTOM);
        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BASELINE);
        mSpSelected.setSpan(imageSpan, list_title.get(position).length() + 2, list_title.get(position).length() + 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

}
