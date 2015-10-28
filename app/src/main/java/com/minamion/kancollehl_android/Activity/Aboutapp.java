package com.minamion.kancollehl_android.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.minamion.kancollehl_android.R;
import com.minamion.kancollehl_android.Update.UpdateManager;

/**
 * Created by Minamion on 2015/10/27.
 */
public class Aboutapp extends AppCompatActivity {
    String localver;
    String serverver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutapp);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "联系作者？", Snackbar.LENGTH_LONG)
                        .setAction("点我~", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent data=new Intent(Intent.ACTION_SENDTO);
                                data.setData(Uri.parse("mailto:minamion@outlook.com"));
                                data.putExtra(Intent.EXTRA_SUBJECT, "这里是标题~");
                                data.putExtra(Intent.EXTRA_TEXT, "这里是内容~");
                                startActivity(data);
                            }}).show();
            }
        });






        UpdateManager manager = new UpdateManager(Aboutapp.this);
        // 检查更新
        localver = manager.checkver();
        fillTextView(R.id.local_v, "当前版本号:" + localver);
        Log.e("获取", "++++++版本号++++++ " + manager.checkver() + "++++++");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final CircularProgressButton circularButton03 = (CircularProgressButton) findViewById(R.id.btnWithText);
        circularButton03.setText("检查更新");
        circularButton03.setIndeterminateProgressMode(true);
        circularButton03.setProgress(0);
        circularButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final CircularProgressButton btn = (CircularProgressButton) v;
                int progress = btn.getProgress();
                UpdateManager manager = new UpdateManager(Aboutapp.this);
                // 检查更新
                final int a = manager.checkUpdate_onbtn();
                if (progress == 0) { // 初始时progress = 0
                    btn.setProgress(50); // 点击后开始不精准进度，不精准进度的进度值一直为50
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            UpdateManager manager = new UpdateManager(Aboutapp.this);
                            // 检查更新
                            serverver = manager.checkserver_ver();
                            fillTextView(R.id.server_v,"最新版本号:" + serverver);
                            Log.e("获取", "++++++版本号++++++ " + manager.checkserver_ver() + "++++++");
                            if (a == 0) {
                                btn.setProgress(100); // 如果不是初始状态，那么就回到初始状态
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        UpdateManager manager = new UpdateManager(Aboutapp.this);
                                        // 检查更新;
                                        Log.e("获取", "++++++版本号++++++ " + manager.checkserver_ver() + "++++++");
                                        if (a == 0) {
                                            btn.setProgress(100); // 如果不是初始状态，那么就回到初始状态
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                }
                                            }, 10);
                                        } else {
                                            btn.setProgress(-1); // 如果不是初始状态，那么就回到初始状态
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                }
                                            }, 10);
                                            Snackbar.make(v, "有可用更新，重启软件后将下载更新", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }
                                    }
                                }, 1000);//3秒 加载动画
                            } else {

                                btn.setProgress(-1); // 如果不是初始状态，那么就回到初始状态
                                Snackbar.make(v, "有可用更新，重启软件后将下载更新", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }
                        }
                    }, 1000);//3秒 加载动画
                } else if (progress == 100) { // 如果当前进度为100，即完成状态，那么重新回到未完成的状态
                    Snackbar.make(v, "已是最新版本", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (progress == 100) { // 如果当前进度为100，即完成状态，那么重新回到未完成的状态
                    btn.setProgress(-1); // 如果不是初始状态，那么就回到初始状态
                    Snackbar.make(v, "有可用更新，重启软件后将下载更新", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillTextView (int id, String text) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text); // tv is null
    }
}
