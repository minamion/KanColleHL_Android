package com.minamion.kancollehl_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by Minamion on 2015/10/19.
 */
public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        UpdateManager manager = new UpdateManager(Welcome.this);
        // 檢查軟件更新
        manager.checkUpdate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(Welcome.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
