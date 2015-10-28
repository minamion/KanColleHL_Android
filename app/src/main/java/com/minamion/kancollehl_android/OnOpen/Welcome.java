package com.minamion.kancollehl_android.OnOpen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.minamion.kancollehl_android.Activity.Main;
import com.minamion.kancollehl_android.R;
import com.minamion.kancollehl_android.Update.UpdateManager;

import pl.tajchert.sample.DotsTextView;

/**
 * Created by Minamion on 2015/10/19.
 */
public class Welcome extends AppCompatActivity {
    private DotsTextView dots;
    private static final int check= 1;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case check:
                    UpdateManager manager = new UpdateManager(Welcome.this);
                    // 检查更新
                    manager.checkUpdate_onstart();
                    break;
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.minamion.kancollehl_android.R.layout.welcome);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dots = (DotsTextView) findViewById(R.id.dots);
        dots.start();
        dots.showAndPlay();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(check);
                Intent intent = new Intent(Welcome.this, Main.class);
                startActivity(intent);

                dots.hideAndStop();
                finish();
            }
        }, 3000);//3秒 欢迎界面
    }
}
