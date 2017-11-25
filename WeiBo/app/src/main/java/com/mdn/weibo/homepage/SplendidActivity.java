package com.mdn.weibo.homepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Window;

import com.mdn.weibo.R;
import com.mdn.weibo.custom.CustomView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by pengleiShen on 2017/10/16.
 */

public class SplendidActivity extends Activity{

    private CustomView mCustomView;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Intent intent = new Intent(SplendidActivity.this,MainActivity.class);
                    SplendidActivity.this.startActivity(intent);
                    finish();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splendid);

        Message msg = new Message();
        //msg.obtain();
        msg.what = 1;
        msg.arg1 = 123;
        //第一种方式：sendMessage
        //Utils.msg_Delayed(handler,msg,3000);

        //第二种方式延时器
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplendidActivity.this,MainActivity.class);
                SplendidActivity.this.startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        //timer.schedule(timerTask,3000);

        //第三种 android消息处理
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplendidActivity.this,MainActivity.class);
                SplendidActivity.this.startActivity(intent);
                finish();
            }
        }, 0);

    }

}
