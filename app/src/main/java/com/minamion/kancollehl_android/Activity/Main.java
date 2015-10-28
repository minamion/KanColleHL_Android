package com.minamion.kancollehl_android.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.minamion.kancollehl_android.R;
import com.minamion.kancollehl_android.Update.UpdateManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Main extends AppCompatActivity {
    final ArrayList<HashMap<String, Object>> listItem_g = new ArrayList<HashMap<String, Object>>();
    final ArrayList<HashMap<String, Object>> listItem_b = new ArrayList<HashMap<String, Object>>();
    String[] tools = {"Chrome玩舰娘", "IE玩舰娘", "Firefox玩舰娘", "Opera玩舰娘", "平板玩舰娘", "手机玩舰娘"};
    String[] directions = {"北方", "东北方", "东方", "东南方", "南方", "西南方", "西方", "西北方", "天上", "地板"};
    String[] drinks = {"驱逐舰", "轻巡洋舰", "重巡洋舰", "战列巡洋舰", "战列舰", "轻空母", "航空母舰", "潜水艇", "扬陆舰", "重雷装巡洋舰", "航空巡洋舰", "航空战列舰", "水上机母舰", "装甲空母", "潜水空母", "训练巡洋舰", "工作舰"};
    String[][] activities = {
            {"大型舰建造", "欧洲大舰一发入魂！骚年你不来一发么？", "资源…我的资源到哪里去了…_(:3」∠)_"},
            {"普通舰建造", "岛风雪风全入后宫！！", "那卡酱哒呦！"},
            {"开发46炮", "46连发。你，值得拥有！(๑•̀ㅂ•́)و✧", "最近机铳好和企鹅像有点多呢…"},
            {"开发电探", "32、14连发。你，值得拥有！(๑•̀ㅂ•́)و✧", "最近机铳好和企鹅像有点多呢…"},
            {"开发飞机", "烈风流星改一二甲来袭。你，值得拥有！(๑•̀ㅂ•́)و✧", "吃我大零式水侦啦！"},
            {"开发反潜", "三式反潜套大爆发。你，值得拥有！(๑•̀ㅂ•́)و✧", "为什么连九三套都没有_(:3」∠)_"},
            {"做西南任务", "BOSS五连发，开心愉快！", "咦…怎么撸号都完成了西南还连50%都没有！"},
            {"爆肝远征", "资源蹭蹭的涨啊！", "为什么我又忘了远征补给_(:3」∠)_"},
            {"推新图", "BOSS一发成功！Wow！Congratulations！", "Shit！长门陆奥又大破了！"},
            {"使用Chrome玩舰娘", "运气将有如神助！", "Flash怎么又挂了！[才不是黑Chrome呢！]"},
            {"氪金", "该出手时就出手！", "由于网络故障您的资金将会延时到帐_(:3」∠)_"},
            {"抢新号", "抢号一次成功！", "又记错抢号时间了…"},
            {"刷稀有舰娘", "大鲸三隈快到碗里来！", "那卡酱哒呦！"},
            {"收舰娘本子", "今天找到的本子简直实用！", "千万不要去布卡漫画搜索“浴室”！！"},
            {"晒欧洲大建货", "你会成为非提的偶像！", "火把，汽油，准备就绪！全炮门！Fire！"},
            {"晒稀有驱逐", "小学生什么的最棒了(๑•̀ㅂ•́)و✧", "宪兵队！就是这个变态！"},
            {"和舰娘结婚", "舰娘好感度↑↑↑", "因重婚罪被宪兵队抓走_(:3」∠)_"},
            {"舰娘练级", "今天练级升级好快！", "为什么1-1大和也能大破………"},
            {"刷战果", "前500名手到擒来，100不再遥远！", "猫吊来啦，大家快跑啊！"},
            {"半夜上线", "半夜是提督精神最好的时候(๑•̀ㅂ•́)و✧", "白天已经筋疲力尽了……"},
            {"戳秘书舰", "啊啊…好幸福(｢･////･)｢﻿", "秘书舰好感度下降了！"},
            {"推5-5", "莱爷今天心情很好，窝改状态不佳", "桶…资源…都………………"},
            {"批量拆船", "哐哐哐，2411到手！", "舰娘权益维护组织发起抗议！"},
            {"上G+舰娘社群", "今天的社群消息不能错过！", "今天的舰娘社群闪瞎眼球…"},
            {"改修装备", "赞，一口气上5星，不费劲！", "改修失败……"},};
    String versionName = "";
    Calendar c = Calendar.getInstance();
    String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
    String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
    String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
    String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
    int iday = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
    private static final int check= 1;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case check:
                    UpdateManager manager = new UpdateManager(Main.this);
                    // 检查更新
                    manager.checkUpdate();
                    break;
                default:
                    break;
            }
        };
    };
    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        change_date();
        set_text();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so lon
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Jump_to_about();
//        } else {
//            if (id == R.id.action_update) {
//                UpdateManager manager = new UpdateManager(Main.this);
//                // 檢查軟件更新
//                manager.checkUpdate();
//                return true;
//            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void Jump_to_about() {
//        Intent intent = new Intent(this, Aboutapp.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
        Intent intent = new Intent();
        intent.setClass(Main.this, Aboutapp.class);
        startActivity(intent);
        Log.e("跳转器", "++++++++启动++++++++");
    }

    public void change_date() {

        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        TextView text_date = (TextView) findViewById(R.id.date);
        text_date.setText("今天是" + mYear + "年" + mMonth + "月" + mDay + "日" + "星期" + mWay);
    }

    int random(int dayseed, int indexseed) //根据当前时间“天 ”产生伪随机数。
    {
        int i, n;
        n = dayseed % 11117;
        for (i = 0; i < 100 + indexseed; i++) {
            n = n * n;
            n = n % 11117;   // 11117 是个质数
        }
        return n;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    private String CVrecipe(int num) {
        Calendar c = Calendar.getInstance();
        String result1;
        int r1;
        int r2;
        int r3;
        int r4;
        switch (num) {
            case 0:
                r1 = 4000;
                r2 = 2000;
                r3 = 5000;
                r4 = 6500;
                break;
            case 1:
                r1 = 3500;
                r2 = 3500;
                r3 = 6000;
                r4 = 6000;
                break;
            case 2:
                r1 = 4000;
                r2 = 2000;
                r3 = 5000;
                r4 = 5200;
                break;
            default:
                r1 = 4000;
                r2 = 2000;
                r3 = 5000;
                r4 = 6500;
                break;
        }
        int zc = random(iday, 11) % 10 + 1;
        int zc2;
        if (zc <= 4 && zc > 0) {
            zc2 = 1;
        } else if (zc <= 8 && zc > 4) {
            zc2 = 20;
        } else {
            zc2 = 100;
        }
        r1 = r1 + random(iday, 31) % 49 * 10;
        r2 = r2 + random(iday, 37) % 49 * 10;
        r3 = r3 + random(iday, 91) % 49 * 10;
        r4 = r4 + random(iday, 101) % 49 * 10;
        result1 = r1 + "/" + r2 + "/" + r3 + "/" + r4 + "  开发资材:" + zc2 + "个";
        return result1;
    }

    private String BBrecipe(int num) {
        Calendar c = Calendar.getInstance();
        int iday = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
        String result1;
        int r1;
        int r2;
        int r3;
        int r4;
        switch (num) {
            case 0:
                r1 = 4000;
                r2 = 6000;
                r3 = 6000;
                r4 = 3000;
                break;
            case 1:
                r1 = 4000;
                r2 = 6000;
                r3 = 6000;
                r4 = 2000;
                break;
            case 2:
                r1 = 6000;
                r2 = 5000;
                r3 = 6000;
                r4 = 2000;
                break;
            default:
                r1 = 4000;
                r2 = 6000;
                r3 = 6000;
                r4 = 3000;
                break;
        }
        int zc = random(iday, 7) % 10 + 1;
        int zc2 = 1;
        if ((zc <= 4) && (zc > 0)) {
            zc2 = 1;
        } else if (zc <= 8 && zc > 4) {
            zc2 = 20;
        } else {
            zc2 = 100;
        }
        r1 = r1 + random(iday, 73) % 49 * 10;
        r2 = r2 + random(iday, 61) % 49 * 10;
        r3 = r3 + random(iday, 59) % 49 * 10;
        r4 = r4 + random(iday, 89) % 49 * 10;
        result1 = r1 + "/" + r2 + "/" + r3 + "/" + r4 + "  开发资材:" + zc2 + "个";
        return result1;
    }

    // 从数组中随机挑选 size 个
    void pickRandom(String[][] array, int size_g, ArrayList<HashMap<String, Object>> listItem_G ,int size_b, ArrayList<HashMap<String, Object>> listItem_B) {

        ArrayList<HashMap<String, Object>> listItem1;
         listItem1 = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", (array[i][0]));
            map.put("ItemText_G", (array[i][1]));
            map.put("ItemText_B", (array[i][2]));
            map.put("ID", i);
            listItem1.add(map);


        }
        Log.e("舰娘老黄历", "+调试信息-表+" +listItem1);
//
        for (int j = 0;listItem1.size() > 10; j++) {
            int index = random(iday, j) % (listItem1.size());
            Log.e("MainActivity", "+调试信息-随机数+" + "[" + j + "]" + index);
            listItem1.remove(index);
            Log.e("LIST", "\n\n\n+调试信息-随机完的表+" + listItem1);
//            Log.e("MainActivity", "+调试信息-删除" + index + "后的表+" + "[" + j + "]  " + listItem1);
        }

        listItem_G.clear();
        for(int i=0;i<size_g;i++)
        {listItem_G.add(listItem1.get(i));}
        for(int i=size_g;i<(size_g+size_b);i++)
        {listItem_B.add(listItem1.get(i));}

    }

    public void set_text() {
        int numGood = random(iday, 98) % 3 + 2;
        int numBad = random(iday, 87) % 3 + 2;
        pickRandom(activities, numGood, listItem_g,numBad, listItem_b);
        int randNum = random(iday, 2);//产生伪随机数
        String a1 = "";
        String[] b1 = new String[17];
        String b2 = "";
        String c1;
        for (int i = 0; i <= ((random(iday, 6) % 5 + 1)); i++) {
            a1 = a1 + "★";
        }
        //ARRAY=17 SIZE=2
        for (int i = 0; i < 17; i++) {
            b1[i] = (drinks[i]);
        }
        for (int j = 0; j < 2; j++) {
            int index = random(iday, j) % 17 - 2;
            if (index < 0)
                index = -index;
            b1[index] = "";
        }
        for (int i = 0; i < 2; i++) {
            b2 = b2 + b1[i] + " ";
        }
        c1 = directions[randNum % 8];
        TextView text_date1 = (TextView) findViewById(R.id.textView1);
        TextView text_date2 = (TextView) findViewById(R.id.textView2);
        TextView text_date3 = (TextView) findViewById(R.id.textView3);
        TextView text_date4 = (TextView) findViewById(R.id.textView4);
        text_date1.setText("座位朝向：面向 " + c1 + " 赌船赌装备，脸最好。");
        text_date2.setText("推荐旗舰：" + b2);
        text_date3.setText("推图指数：" + a1);
        String result1 = BBrecipe(random(iday, 11) % 4);
        String result2 = CVrecipe(random(iday, 13) % 4);
        text_date4.setText("玄学公式：" + "\n战舰:" + result1 + "\n空母:" + result2);
        ListView list_good = (ListView) findViewById(R.id.list_good);
        ListView list_bad = (ListView) findViewById(R.id.list_bad);
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter_g = new SimpleAdapter(Main.this, listItem_g,//数据源
                R.layout.listview,//ListItem的XML实现
                //动态数组
                new String[]{"ItemTitle", "ItemText_G"},
                //ImageItem的XML文件里面的两个TextView ID
                new int[]{R.id.text1, R.id.text2}
        );
        SimpleAdapter listItemAdapter_b = new SimpleAdapter(Main.this, listItem_b,//数据源
                R.layout.listview2,//ListItem的XML实现
                //动态数组
                new String[]{"ItemTitle", "ItemText_B"},
                //ImageItem的XML文件里面的两个TextView ID
                new int[]{R.id.text1, R.id.text2}
        );
        //添加并且显示
        list_good.setAdapter(listItemAdapter_g);
        list_bad.setAdapter(listItemAdapter_b);
    }
}