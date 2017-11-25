package com.mdn.weibo.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdn.weibo.R;
import com.mdn.weibo.activity.DetailActivity;
import com.mdn.weibo.activity.DetailsActivity;
import com.mdn.weibo.custom.ObservableScrollView;

/**
 * Created by pengleiShen on 2017/10/25.
 */

public class FollowFragment extends Fragment {

    private TextView follow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_follow,null);
        follow = (TextView) view.findViewById(R.id.follow);
        follow.setText("关注");
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
