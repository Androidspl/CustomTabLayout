package com.mdn.weibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdn.weibo.R;

/**
 * Created by pengleiShen on 2017/10/25.
 */

public class HotTopicFragment extends Fragment {

    private TextView hottopic_tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hottopic,null);
        hottopic_tv = (TextView) view.findViewById(R.id.hottopic_tv);
        hottopic_tv.setText("热门");

        return view;
    }

}
