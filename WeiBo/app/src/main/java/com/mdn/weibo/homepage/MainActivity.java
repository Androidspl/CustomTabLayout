package com.mdn.weibo.homepage;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mdn.weibo.R;
import com.mdn.weibo.custom.CustomTabView;
import com.mdn.weibo.custom.CustomView;
import com.mdn.weibo.fragment.AddFragment;
import com.mdn.weibo.fragment.DisCoverFragment;
import com.mdn.weibo.fragment.HomePageFragment;
import com.mdn.weibo.fragment.MessAgeFragment;
import com.mdn.weibo.fragment.MyFragment;
import com.mdn.weibo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @date  创建时间：2017/10/19
 *
 * @author pengleiShen
 *
 * @Description
 *
 */
public class MainActivity extends AppCompatActivity implements CustomTabView.OnTabSelectListener{

    private CustomTabView custom_tab_view;
//    private Fragment[] mFragments;
    private List<Fragment> mFragments;
    private CustomView mCustomView;
    private ImageView add_iv;
    private ImageView halfcircle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控件
        custom_tab_view = (CustomTabView) findViewById(R.id.custom_tab_view);
//        custom_tab_view.setBackgroundColor(Color.GRAY);

        //初始化底部tab
        initTabView();
        //初始化添加的Fragment
        initFragment();
        // 初始化底部凸起导航菜单
//        initAddTabView();
    }

    /**
     * @Description: 实现底部凸起导航菜单
     *
     * @param
     *
     * @return
     *
     */
    private void initAddTabView() {

        add_iv = (ImageView) findViewById(R.id.add_iv);
        add_iv.setVisibility(View.VISIBLE);
        halfcircle = (ImageView) findViewById(R.id.halfcircle);
        halfcircle.setVisibility(View.VISIBLE);


//        mCustomView = (CustomView) findViewById(R.id.custom_view);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        mCustomView.setPadding(0,0,0,custom_tab_view.getHeight()*4);
//        mCustomView.setPadding(0,0,0,400);
//        mCustomView.setLayoutParams(params);

    }


    /**
     * @Description:初始化TabView
     *
     * @param
     *
     * @return
     *
     */
    private void initTabView() {

        // 首页
        CustomTabView.Tab tabHome = new CustomTabView.Tab()
                .setmText("首页")
                .setmIconNormalResId(R.drawable.home)
                .setmIconPressResId(R.drawable.home_select)
                .setmNormalColor(R.color.mTabTv)
                .setmSelectColor(Color.BLACK)
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 23))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 23));
        custom_tab_view.addTab(tabHome);


        // 发现
        CustomTabView.Tab tabDis = new CustomTabView.Tab()
                .setmText("发现")
                .setmIconNormalResId(R.drawable.dis)
                .setmIconPressResId(R.drawable.dis_select)
                .setmNormalColor(R.color.mTabTv)
                .setmSelectColor(Color.BLACK)
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 23))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 23));
        custom_tab_view.addTab(tabDis);

        // 凸出的加号
        CustomTabView.Tab tabAdd = new CustomTabView.Tab()
//                .setmText("")
                .setmIconNormalResId(R.drawable.add_normal)
                .setmIconPressResId(R.drawable.add_select)
                .setmNormalColor(R.color.mTabTv)
                .setmSelectColor(Color.BLACK)
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 48))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 48));
        custom_tab_view.addTab(tabAdd);

        // 消息
        CustomTabView.Tab tabMes = new CustomTabView.Tab()
                .setmText("消息")
                .setmIconNormalResId(R.drawable.msg)
                .setmIconPressResId(R.drawable.msg_select)
                .setmNormalColor(R.color.mTabTv)
                .setmSelectColor(Color.BLACK)
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 23))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 23));
        custom_tab_view.addTab(tabMes);

        // 我的
        CustomTabView.Tab tabMy = new CustomTabView.Tab()
                .setmText("我的")
                .setmIconNormalResId(R.drawable.my)
                .setmIconPressResId(R.drawable.my_select)
                .setmNormalColor(R.color.mTabTv)
                .setmSelectColor(Color.BLACK)
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 23))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 23));
        custom_tab_view.addTab(tabMy);

        //设置默认选中首页
        custom_tab_view.setCurrentItem(0);

        custom_tab_view.setOnTabSelectListener(this);
    }

    private void initFragment() {
        HomePageFragment homepage = new HomePageFragment();
        DisCoverFragment dis = new DisCoverFragment();
        AddFragment add = new AddFragment();
        MessAgeFragment mes = new MessAgeFragment();
        MyFragment my = new MyFragment();
        mFragments = new  ArrayList<>();
        mFragments.add(homepage);
        mFragments.add(dis);
        mFragments.add(add);
        mFragments.add(mes);
        mFragments.add(my);
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,mFragments.get(0)).commit();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.home_container,mFragments.get(0))
                .commit();
    }

    @Override
    public void onTabSelected(int position, View view) {
        Fragment fragment = null;
        fragment = mFragments.get(position);
        // 如果有实现底部凸起导航菜单需求，这里控制点击切换图标选中的状态
//        switch (position){
////            fragment = mFragments.get(position);
//            case 0:
//                add_iv.setImageResource(R.drawable.add_normal);
//                break;
//            case 1:
//                add_iv.setImageResource(R.drawable.add_normal);
//                break;
//            case 2:
//                add_iv.setImageResource(R.drawable.add_select);
//                break;
//            case 3:
//                add_iv.setImageResource(R.drawable.add_normal);
//                break;
//            case 4:
//                add_iv.setImageResource(R.drawable.add_normal);
//                break;
//        }
//        fragment_tv.setText("This is " + position +"Fragment");
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }

}