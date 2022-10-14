package com.example.administrator.egapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView tvDangan,tvShenghuo,tvGeren;
    private List<Fragment> fragmentList=new ArrayList<Fragment>();
    private SlidingPaneLayout sliding_menu;
    private ListView listViewMenu;
//    需要解析给listView解析用
    private String[]data={"档案管理","生活服务","个人中心"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        FragmentList添加到一个官网提供的一个管理器
        viewPager=(ViewPager)findViewById(R.id.view_page);
        tvDangan=(TextView)findViewById(R.id.tv_dangan);
        tvShenghuo=(TextView)findViewById(R.id.tv_chenghuo);
        tvGeren=(TextView)findViewById(R.id.tv_grren);
        sliding_menu=(SlidingPaneLayout)findViewById(R.id.sliding_menu);
        listViewMenu=(ListView)findViewById(R.id.listview_menu);
//        定义listview的数据，使用adapter
        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        listViewMenu.setAdapter(listAdapter);
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sliding_menu.closePane();
                viewPager.setCurrentItem(position);
            }
        });
//统一管理
        fragmentList.add(new Mainitem1());
        fragmentList.add(new Mainitem2());
        fragmentList.add(new Mainitem3());
        FragmentManager fm=this.getSupportFragmentManager();
        MainfragmentAdapter adapter=new MainfragmentAdapter(fm,fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                页面发生变换选中的时候
                tvDangan.setBackgroundResource(R.color.noactive);
                tvShenghuo.setBackgroundResource(R.color.noactive);
                tvGeren.setBackgroundResource(R.color.noactive);
                switch (position){
                    case 0:tvDangan.setBackgroundResource(R.color.active);break;
                    case 1:tvShenghuo.setBackgroundResource(R.color.active);break;
                    case 2:tvGeren.setBackgroundResource(R.color.active);break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tvDangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        tvShenghuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        tvGeren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
    }
}
