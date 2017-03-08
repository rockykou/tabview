package com.rockykou;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.rockykou.entity.TabEntity;
import com.rockykou.tabview.R;
import com.rockykou.views.TabView;

import java.util.ArrayList;
import java.util.List;
/**
 * 作者：rockykou
 * 邮箱: kouzhen121@163.com
 * 版权: 供学习交流使用
 */
public class MainActivity extends AppCompatActivity {

    private TabView mTabView1;
    private TabView mTabView2;
    private TabView mTabView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabView1();
        initTabView2();
        initTabView3();
    }


    private void initTabView1() {
        mTabView1 = (TabView) findViewById(R.id.tabview1);
        List<String> textlist = new ArrayList<String>();
        textlist.add("消息");
        textlist.add("圈子");
        mTabView1.setTextSize(13)
                .setColor(Color.GRAY, getResources().getColor(R.color.colorPrimary))
                .setTextColor(Color.BLACK, Color.WHITE)
                .setStrokeColor(Color.GREEN, Color.RED)
                .setCornerRadius(10)
                .setStrokeWidth(1)
                .setDashWidth(0)
                .setDashGap(0)
                .setTexts(textlist)
                .setSelected(1)
                .show();
        mTabView1.setOnTabItemClickListener(new TabView.OnTabItemClickListener() {

            @Override
            public void onTabItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, "position：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initTabView2() {
        mTabView2 = (TabView) findViewById(R.id.tabview2);
        List<TabEntity> textlist = new ArrayList<TabEntity>();
        TabEntity tab1 = new TabEntity();
        tab1.tabName = "未开始";
        TabEntity tab2 = new TabEntity();
        tab2.tabName = "进行中";
        TabEntity tab3 = new TabEntity();
        tab3.tabName = "已结束";
        textlist.add(tab1);
        textlist.add(tab2);
        textlist.add(tab3);
        mTabView2.setTextSize(14)
                .setColor(Color.GRAY, Color.RED)
                .setTextColor(Color.BLACK, Color.WHITE)
                .setStrokeColor(Color.GREEN, Color.RED)
                .setCornerRadius(10)
                .setStrokeWidth(1)
                .setDashWidth(0)
                .setDashGap(0)
                .setEntitys(textlist)
                .setSelected(2)
                .show();
        mTabView2.setOnTabItemClickListener(new TabView.OnTabItemClickListener() {

            @Override
            public void onTabItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, "position：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTabView3() {
        mTabView3 = (TabView) findViewById(R.id.tabview3);
        List<String> textlist = new ArrayList<String>();
        textlist.add("全部");
        textlist.add("已提交");
        textlist.add("待处理");
        textlist.add("处理中");
        textlist.add("已完成");
        mTabView3.setTextSize(15)
                .setColor(Color.GRAY, Color.RED)
                .setTextColor(Color.BLACK, Color.WHITE)
                .setStrokeColor(Color.RED, Color.RED)
                .setCornerRadius(10)
                .setStrokeWidth(3)
                .setDashWidth(4)
                .setDashGap(5)
                .setTexts(textlist)
                .setSelected(2)
                .show();
        mTabView3.setOnTabItemClickListener(new TabView.OnTabItemClickListener() {

            @Override
            public void onTabItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, "position：" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
